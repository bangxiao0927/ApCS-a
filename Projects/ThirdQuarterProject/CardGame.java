import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class CardGame {
	public enum GameState {
		MENU,
		PLAYING,
		AWAITING_DODGE,
		GAME_OVER
	}

	private final ArrayList<Card> deck;
	private final ArrayList<Card> discardPile;
	private final ArrayList<Player> players;
	private GameState gameState;
	private int activePlayerIndex;
	private int viewingPlayerIndex;
	private Player pendingTarget;
	private Card pendingAttackCard;
	private boolean hasDrawnThisTurn;
	private String statusMessage;

	public CardGame() {
		deck = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		players = new ArrayList<Player>();
		gameState = GameState.MENU;
		statusMessage = "点击开始进入三国战局";
	}

	public void startGame() {
		buildDeck();
		shuffleDeck();
		setupPlayers();
		dealOpeningHands();
		gameState = GameState.PLAYING;
		activePlayerIndex = 0;
		viewingPlayerIndex = 0;
		hasDrawnThisTurn = false;
		pendingAttackCard = null;
		pendingTarget = null;
		statusMessage = getActivePlayer().getHeroName() + " draws first blood.";
	}

	private void buildDeck() {
		deck.clear();
		discardPile.clear();
		for (int i = 0; i < 8; i++) {
			deck.add(new Card(6 + i, "Sha", "hearts", CardType.ATTACK, "Slash"));
		}
		for (int i = 0; i < 6; i++) {
			deck.add(new Card(4 + i, "Shan", "hearts", CardType.DODGE, "Dodge"));
		}
		for (int i = 0; i < 4; i++) {
			deck.add(new Card(8 + i, "Peach", "hearts", CardType.HEAL, "Heal"));
		}
		// tactical support cards unique to this build
		deck.add(new Card(1, "Battle Orders", "hearts", CardType.TACTIC, "+1 draw"));
		deck.add(new Card(2, "Battle Orders", "hearts", CardType.TACTIC, "+1 draw"));
	}

	private void setupPlayers() {
		players.clear();
		players.add(new Player("Player 1", "Zhao Yun", 4, Player.Ability.DRAGON_CONVERT));
		players.add(new Player("Player 2", "Zhuge Liang", 3, Player.Ability.STRATEGIST_DRAW));
		players.add(new Player("Player 3", "Sun Shangxiang", 4, Player.Ability.VENGEFUL_HEAL));
	}

	private void dealOpeningHands() {
		for (int i = 0; i < 4; i++) {
			for (Player player : players) {
				drawCard(player);
			}
		}
	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	private Card drawFromDeck() {
		if (deck.isEmpty()) {
			if (!discardPile.isEmpty()) {
				deck.addAll(discardPile);
				discardPile.clear();
				shuffleDeck();
			}
		}
		if (deck.isEmpty()) {
			return null;
		}
		return deck.remove(0);
	}

	private Card drawCard(Player player) {
		Card drawn = drawFromDeck();
		if (drawn != null) {
			player.addCard(drawn);
		}
		return drawn;
	}

	public void drawGame(Graphics g) {
		g.setColor(new Color(4, 92, 35));
		g.fillRect(0, 0, 800, 600);

		if (gameState == GameState.MENU) {
			drawMenu(g);
			return;
		}

		if (gameState == GameState.GAME_OVER) {
			drawGameOver(g);
		} else {
			drawPlayers(g);
		}
		drawViewingHand(g);
		drawStatus(g);
	}

	private void drawMenu(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.ORANGE);
		Font title = new Font("Serif", Font.BOLD, 48);
		g.setFont(title);
		g.drawString("Mini 三国杀", 240, 200);
		Font body = new Font("Arial", Font.PLAIN, 20);
		g.setFont(body);
		g.setColor(Color.white);
		g.drawString("Use the buttons to draw, attack (Sha), dodge (Shan), and heal (Peach).", 120, 280);
		g.drawString("Unique hero abilities:", 120, 320);
		g.drawString("Zhao Yun converts a Dodge into an Attack once per turn.", 140, 350);
		g.drawString("Zhuge Liang draws a bonus card when he dodges.", 140, 380);
		g.drawString("Sun Shangxiang heals 1 HP after dealing damage once per turn.", 140, 410);
	}

	private void drawGameOver(Graphics g) {
		drawPlayers(g);
		g.setColor(new Color(255, 215, 0));
		Font title = new Font("Serif", Font.BOLD, 44);
		g.setFont(title);
		g.drawString("Battle Resolved", 230, 220);
		g.setColor(Color.white);
		Font body = new Font("Arial", Font.PLAIN, 22);
		g.setFont(body);
		g.drawString(statusMessage, 150, 260);
		g.drawString("Press Start to begin a new duel.", 200, 300);
	}

	private void drawPlayers(Graphics g) {
		Font infoFont = new Font("Arial", Font.BOLD, 18);
		g.setFont(infoFont);
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			int baseY = 40 + (i * 70);
			Color labelColor = Color.white;
			if (p.isEliminated()) {
				labelColor = Color.GRAY;
			} else if (i == activePlayerIndex && gameState != GameState.GAME_OVER) {
				labelColor = Color.YELLOW;
			}
			if (i == viewingPlayerIndex) {
				labelColor = Color.CYAN;
			}
			g.setColor(labelColor);
			String flag = "";
			if (i == activePlayerIndex && gameState != GameState.GAME_OVER) {
				flag = " [Active]";
			}
			if (i == viewingPlayerIndex) {
				flag += " [Viewing]";
			}
			g.drawString(p.getHeroName() + flag, 30, baseY);
			g.drawString("HP: " + p.getHp() + "/" + p.getMaxHp() + " | Cards: " + p.getHandSize(), 30, baseY + 20);
			if (gameState == GameState.AWAITING_DODGE && pendingTarget == p) {
				g.drawString("Targeted by Sha!", 30, baseY + 40);
			}
		}

		g.setColor(Color.white);
		g.drawString("Deck: " + deck.size() + " | Discard: " + discardPile.size(), 600, 40);
	}

	private void drawViewingHand(Graphics g) {
		if (players.isEmpty()) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing == null) {
			return;
		}
		g.setColor(Color.white);
		Font header = new Font("Arial", Font.BOLD, 20);
		g.setFont(header);
		g.drawString("Viewing " + viewing.getHeroName() + "'s hand:", 20, 340);
		int x = 20;
		int y = 360;
		for (Card card : viewing.getHand()) {
			card.drawMe(g, x, y);
			x += 130;
		}
	}

	private void drawStatus(Graphics g) {
		g.setColor(Color.white);
		Font statusFont = new Font("Arial", Font.PLAIN, 18);
		g.setFont(statusFont);
		g.drawString(statusMessage, 20, 540);
	}

	public GameState getGameState() {
		return gameState;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public boolean hasDrawnThisTurn() {
		return hasDrawnThisTurn;
	}

	public boolean isAwaitingDodge() {
		return gameState == GameState.AWAITING_DODGE;
	}

	public void drawForActivePlayer() {
		if (gameState != GameState.PLAYING) {
			return;
		}
		if (players.isEmpty()) {
			return;
		}
		if (hasDrawnThisTurn) {
			statusMessage = "Card already drawn this turn.";
			return;
		}
		Player active = getActivePlayer();
		Card card = drawCard(active);
		if (card == null) {
			statusMessage = "The deck is empty.";
			return;
		}
		hasDrawnThisTurn = true;
		statusMessage = active.getHeroName() + " drew " + card.getName() + ".";
	}

	public void playAttack() {
		if (gameState != GameState.PLAYING) {
			return;
		}
		Player attacker = getActivePlayer();
		Card attackCard = attacker.playCard(CardType.ATTACK);
		if (attackCard == null) {
			attackCard = attacker.convertDodgeToAttack();
			if (attackCard == null) {
				statusMessage = attacker.getHeroName() + " has no Sha available.";
				return;
			}
			statusMessage = attacker.getHeroName() + " converts a Shan into Sha!";
		}
		Player target = selectNextTarget();
		if (target == null) {
			statusMessage = attacker.getHeroName() + " stands unopposed.";
			return;
		}
		discardPile.add(attackCard);
		pendingAttackCard = attackCard;
		pendingTarget = target;
		gameState = GameState.AWAITING_DODGE;
		statusMessage = attacker.getHeroName() + " plays Sha targeting " + target.getHeroName() + ".";
	}

	public void playDodgeFromViewingPlayer() {
		if (gameState != GameState.AWAITING_DODGE || pendingTarget == null) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing != pendingTarget) {
			statusMessage = "Switch to the targeted hero to respond.";
			return;
		}
		Card dodge = viewing.playCard(CardType.DODGE);
		if (dodge == null) {
			statusMessage = viewing.getHeroName() + " has no Shan.";
			return;
		}
		discardPile.add(dodge);
		statusMessage = viewing.getHeroName() + " dodges the attack.";
		if (viewing.canStrategistDraw()) {
			Card bonus = drawCard(viewing);
			if (bonus != null) {
				statusMessage += " Strategy bonus draws " + bonus.getName() + ".";
			}
		}
		clearPendingAttack();
	}

	public void resolveAttackWithoutDodge() {
		if (gameState != GameState.AWAITING_DODGE || pendingTarget == null) {
			return;
		}
		applyDamage(pendingTarget, 1);
		clearPendingAttack();
		checkForVictory();
	}

	public void playPeachFromViewingPlayer() {
		if (players.isEmpty()) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing == null || viewing.isEliminated()) {
			statusMessage = "Eliminated heroes cannot use cards.";
			return;
		}
		Card healCard = viewing.playCard(CardType.HEAL);
		if (healCard == null) {
			statusMessage = viewing.getHeroName() + " has no Peach.";
			return;
		}
		discardPile.add(healCard);
		viewing.heal(1);
		statusMessage = viewing.getHeroName() + " uses a Peach to heal.";
	}

	public void playBattleOrders() {
		if (players.isEmpty()) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing == null || viewing.isEliminated()) {
			return;
		}
		Card tactic = viewing.playCard(CardType.TACTIC);
		if (tactic == null) {
			statusMessage = "No Battle Orders card available.";
			return;
		}
		discardPile.add(tactic);
		Card first = drawCard(viewing);
		Card second = drawCard(viewing);
		statusMessage = viewing.getHeroName() + " rallies troops and draws extra cards.";
		if (first != null) {
			statusMessage += " (" + first.getName();
			if (second != null) {
				statusMessage += ", " + second.getName();
			}
			statusMessage += ")";
		}
		if (gameState == GameState.PLAYING && viewing == getActivePlayer()) {
			hasDrawnThisTurn = true; // using tactic counts as draw phase
		}
	}

	public void endTurn() {
		if (gameState != GameState.PLAYING) {
			return;
		}
		Player active = getActivePlayer();
		active.resetForNewTurn();
		hasDrawnThisTurn = false;
		activePlayerIndex = findNextAliveIndex(activePlayerIndex);
		if (activePlayerIndex == -1) {
			statusMessage = "No one left standing.";
			gameState = GameState.GAME_OVER;
			return;
		}
		statusMessage = getActivePlayer().getHeroName() + " takes the initiative.";
	}

	public void cycleView() {
		if (players.isEmpty()) {
			return;
		}
		int original = viewingPlayerIndex;
		for (int i = 0; i < players.size(); i++) {
			viewingPlayerIndex = (viewingPlayerIndex + 1) % players.size();
			if (!players.get(viewingPlayerIndex).isEliminated()) {
				break;
			}
		}
		if (players.get(viewingPlayerIndex).isEliminated()) {
			viewingPlayerIndex = original;
		}
		statusMessage = "Viewing " + players.get(viewingPlayerIndex).getHeroName() + ".";
	}

	public boolean canViewingPlayerDodge() {
		return gameState == GameState.AWAITING_DODGE && pendingTarget != null && getViewingPlayer() == pendingTarget;
	}

	public boolean canViewingPlayerHeal() {
		if (players.isEmpty()) {
			return false;
		}
		Player viewing = getViewingPlayer();
		return viewing != null && !viewing.isEliminated() && viewing.hasCard(CardType.HEAL);
	}

	public boolean isViewingPlayerTargeted() {
		return gameState == GameState.AWAITING_DODGE && pendingTarget != null && getViewingPlayer() == pendingTarget;
	}

	public boolean canViewingPlayTactic() {
		if (players.isEmpty()) {
			return false;
		}
		Player viewing = getViewingPlayer();
		return viewing != null && !viewing.isEliminated() && viewing.hasCard(CardType.TACTIC);
	}

	public boolean canActiveAttack() {
		if (gameState != GameState.PLAYING || players.isEmpty()) {
			return false;
		}
		Player active = getActivePlayer();
		return active.hasCard(CardType.ATTACK) || active.getAbility() == Player.Ability.DRAGON_CONVERT && active.hasCard(CardType.DODGE);
	}

	public boolean canEndTurn() {
		return gameState == GameState.PLAYING;
	}

	private Player getViewingPlayer() {
		if (players.isEmpty()) {
			return null;
		}
		if (viewingPlayerIndex < 0 || viewingPlayerIndex >= players.size()) {
			viewingPlayerIndex = 0;
		}
		return players.get(viewingPlayerIndex);
	}

	private Player getActivePlayer() {
		if (players.isEmpty()) {
			return null;
		}
		if (activePlayerIndex < 0 || activePlayerIndex >= players.size()) {
			activePlayerIndex = 0;
		}
		return players.get(activePlayerIndex);
	}

	private Player selectNextTarget() {
		if (players.size() <= 1) {
			return null;
		}
		int index = activePlayerIndex;
		for (int i = 1; i < players.size(); i++) {
			int candidateIndex = (index + i) % players.size();
			Player candidate = players.get(candidateIndex);
			if (!candidate.isEliminated()) {
				return candidate;
			}
		}
		return null;
	}

	private void applyDamage(Player target, int dmg) {
		if (pendingAttackCard != null) {
			discardPile.add(pendingAttackCard);
		}
		Player attacker = getActivePlayer();
		if (target == null) {
			return;
		}
		target.loseHp(dmg);
		if (target.isEliminated()) {
			statusMessage = target.getHeroName() + " has fallen!";
		} else {
			statusMessage = target.getHeroName() + " loses " + dmg + " HP.";
		}
		if (attacker != null && attacker.canHealAfterDamage()) {
			attacker.heal(1);
			attacker.markHealUsed();
			statusMessage += " " + attacker.getHeroName() + "'s Battle Poise heals 1 HP.";
		}
	}

	private void clearPendingAttack() {
		pendingAttackCard = null;
		pendingTarget = null;
		if (gameState != GameState.GAME_OVER) {
			gameState = GameState.PLAYING;
		}
	}

	private void checkForVictory() {
		int alive = 0;
		Player lastAlive = null;
		for (Player player : players) {
			if (!player.isEliminated()) {
				alive++;
				lastAlive = player;
			}
		}
		if (alive <= 1) {
			if (lastAlive != null) {
				statusMessage = lastAlive.getHeroName() + " wins the duel!";
			} else {
				statusMessage = "All heroes have fallen.";
			}
			gameState = GameState.GAME_OVER;
		}
	}

	private int findNextAliveIndex(int startIndex) {
		if (players.isEmpty()) {
			return -1;
		}
		for (int i = 1; i <= players.size(); i++) {
			int idx = (startIndex + i) % players.size();
			if (!players.get(idx).isEliminated()) {
				players.get(idx).resetForNewTurn();
				return idx;
			}
		}
		return -1;
	}
}
