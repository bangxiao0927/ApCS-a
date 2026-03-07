import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

//game logic and state management for the card game
//note
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
	private int turnCount;
	private int drawsTaken;
	private int attacksPlayed;
	private int dodgesPlayed;
	private int healsUsed;
	private int tacticsPlayed;
	private String winnerMessage;
	private String scoreMessage;
	private String roleRevealMessage;
	private String skipNotice;

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
		turnCount = 1;
		drawsTaken = 0;
		attacksPlayed = 0;
		dodgesPlayed = 0;
		healsUsed = 0;
		tacticsPlayed = 0;
		winnerMessage = "";
		scoreMessage = "";
		roleRevealMessage = "";
		skipNotice = null;
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
		deck.add(new Card(1, "Battle Orders", "hearts", CardType.TACTIC, "+2 draw"));
		deck.add(new Card(2, "Battle Orders", "hearts", CardType.TACTIC, "+2 draw"));
		deck.add(new Card(3, "Forced March", "hearts", CardType.TACTIC, "Discard or skip"));
		deck.add(new Card(4, "Forced March", "hearts", CardType.TACTIC, "Discard or skip"));
	}

	private void setupPlayers() {
		players.clear();
		players.add(new Player("Player 1", "Zhao Yun", 4, Player.Ability.DRAGON_CONVERT, Player.Role.LEADER));
		players.add(new StrategistPlayer("Player 2", "Zhuge Liang", 3, Player.Role.REBEL));
		players.add(new Player("Player 3", "Sun Shangxiang", 4, Player.Ability.VENGEFUL_HEAL, Player.Role.LOYALIST));
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
		g.fillRect(0, 0, 1080, 1080);

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
		g.fillRect(0, 0, 1080, 1080);
		g.setColor(Color.ORANGE);
		Font title = new Font("Serif", Font.BOLD, 48);
		g.setFont(title);
		g.drawString("Mini 三国杀", 360, 300);
		Font body = new Font("Arial", Font.PLAIN, 20);
		g.setFont(body);
		g.setColor(Color.white);
		g.drawString("Use the buttons to draw, attack (Sha), dodge (Shan), and heal (Peach).", 140, 420);
		g.drawString("Hidden roles: Leader, Rebel, Loyalist. Defeat the opposing faction.", 140, 450);
		g.drawString("Unique hero abilities:", 140, 490);
		g.drawString("Zhao Yun converts a Dodge into an Attack once per turn.", 160, 530);
		g.drawString("Zhuge Liang draws a bonus card when he dodges.", 160, 560);
		g.drawString("Sun Shangxiang heals 1 HP after dealing damage once per turn.", 160, 590);
		g.drawString("Cheat key: press 'C' to grant the viewing hero a bonus draw + heal.", 140, 630);
	}

	private void drawGameOver(Graphics g) {
		drawPlayers(g);
		g.setColor(new Color(255, 215, 0));
		Font title = new Font("Serif", Font.BOLD, 44);
		g.setFont(title);
		g.drawString("Battle Resolved", 320, 360);
		g.setColor(Color.white);
		Font body = new Font("Arial", Font.PLAIN, 22);
		g.setFont(body);
		g.drawString(winnerMessage, 200, 420);
		g.drawString(scoreMessage, 200, 450);
		g.drawString("Turns: " + turnCount + " | Draws: " + drawsTaken + " | Attacks: " + attacksPlayed
				+ " | Dodges: " + dodgesPlayed + " | Heals: " + healsUsed + " | Tactics: " + tacticsPlayed, 200, 480);
		g.drawString(roleRevealMessage, 200, 510);
		g.drawString("Press Start to begin a new duel.", 260, 560);
	}

	private void drawPlayers(Graphics g) {
		Font infoFont = new Font("Arial", Font.BOLD, 18);
		g.setFont(infoFont);
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			int baseY = 60 + (i * 90);
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
			g.drawString(p.getHeroName() + flag, 40, baseY);
			boolean revealRole = gameState == GameState.GAME_OVER || i == viewingPlayerIndex;
			String roleLabel = revealRole ? p.getRole().getLabel() : "Hidden";
			g.drawString("HP: " + p.getHp() + "/" + p.getMaxHp() + " | Cards: " + p.getHandSize() + " | Role: " + roleLabel, 40, baseY + 26);
			if (gameState == GameState.AWAITING_DODGE && pendingTarget == p) {
				g.drawString("Targeted by Sha!", 40, baseY + 52);
			}
		}

		g.setColor(Color.white);
		g.drawString("Deck: " + deck.size() + " | Discard: " + discardPile.size(), 820, 60);
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
		g.drawString("Viewing " + viewing.getHeroName() + " (" + viewing.getRole().getLabel() + ") hand:", 40, 560);
		Font abilityFont = new Font("Arial", Font.PLAIN, 18);
		g.setFont(abilityFont);
		g.drawString("Ability: " + getAbilityLabel(viewing), 40, 585);
		int x = 40;
		int y = 610;
		for (Card card : viewing.getHand()) {
			card.drawMe(g, x, y);
			x += 180;
			if (x + 160 > 820) {
				x = 40;
				y += 230;
			}
		}
	}

	private String getAbilityLabel(Player player) {
		if (player == null) {
			return "None";
		}
		Player.Ability ability = player.getAbility();
		if (ability == Player.Ability.DRAGON_CONVERT) {
			return "Dragon Convert";
		}
		if (ability == Player.Ability.VENGEFUL_HEAL) {
			return "Vengeful Heal";
		}
		return "None";
	}

	private void drawStatus(Graphics g) {
		g.setColor(Color.white);
		Font statusFont = new Font("Arial", Font.PLAIN, 18);
		g.setFont(statusFont);
		g.drawString(statusMessage, 40, 1040);
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
		drawsTaken++;
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
			statusMessage = attacker.getHeroName() + " uses Dragon Convert to turn Shan into Sha!";
		}
		attacksPlayed++;
		Player target = selectNextTarget();
		if (target == null) {
			discardPile.add(attackCard);
			statusMessage = attacker.getHeroName() + " stands unopposed.";
			return;
		}
		pendingAttackCard = attackCard;
		pendingTarget = target;
		gameState = GameState.AWAITING_DODGE;
		focusViewOnPlayer(target);
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
		dodgesPlayed++;
		discardPendingAttack();
		statusMessage = viewing.getHeroName() + " dodges the attack.";
		int bonusDraws = viewing.getDodgeBonusDraw();
		for (int i = 0; i < bonusDraws; i++) {
			Card bonus = drawCard(viewing);
			if (bonus != null) {
				drawsTaken++;
				statusMessage += " Strategy bonus draws " + bonus.getName() + ".";
			}
		}
		if (bonusDraws > 0) {
			statusMessage += " Ability triggered: Strategy Draw.";
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
		healsUsed++;
		statusMessage = viewing.getHeroName() + " uses a Peach to heal.";
	}

	public void playTacticFromViewingPlayer() {
		if (players.isEmpty()) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing == null || viewing.isEliminated()) {
			return;
		}
		Card tactic = viewing.playCard(CardType.TACTIC);
		if (tactic == null) {
			statusMessage = "No tactic card available.";
			return;
		}
		discardPile.add(tactic);
		tacticsPlayed++;
		if ("Battle Orders".equals(tactic.getName())) {
			applyBattleOrders(viewing);
		} else if ("Forced March".equals(tactic.getName())) {
			applyForcedMarch(viewing);
		} else {
			statusMessage = viewing.getHeroName() + " plays an unknown tactic.";
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
			winnerMessage = "No one left standing.";
			scoreMessage = "Score: 0 total HP remaining";
			roleRevealMessage = buildRoleRevealMessage();
			statusMessage = winnerMessage;
			gameState = GameState.GAME_OVER;
			return;
		}
		turnCount++;
		viewingPlayerIndex = activePlayerIndex;
		statusMessage = getActivePlayer().getHeroName() + " takes the initiative.";
		if (skipNotice != null) {
			statusMessage += " " + skipNotice;
			skipNotice = null;
		}
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

	public void applyCheatForViewingPlayer() {
		if (gameState == GameState.MENU) {
			return;
		}
		if (players.isEmpty()) {
			return;
		}
		Player viewing = getViewingPlayer();
		if (viewing == null) {
			return;
		}
		if (viewing.isEliminated()) {
			statusMessage = "Eliminated heroes cannot accept reinforcements.";
			return;
		}
		Card bonus = drawCard(viewing);
		if (bonus != null) {
			drawsTaken++;
		}
		viewing.heal(1);
		statusMessage = "Cheat invoked: " + viewing.getHeroName() + " gains a bonus draw and 1 HP.";
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

	private Player selectNextTargetFromIndex(int startIndex) {
		if (players.size() <= 1) {
			return null;
		}
		for (int i = 1; i < players.size(); i++) {
			int candidateIndex = (startIndex + i) % players.size();
			Player candidate = players.get(candidateIndex);
			if (!candidate.isEliminated()) {
				return candidate;
			}
		}
		return null;
	}

	private void applyBattleOrders(Player viewing) {
		Card first = drawCard(viewing);
		Card second = drawCard(viewing);
		statusMessage = viewing.getHeroName() + " rallies troops and draws extra cards.";
		if (first != null || second != null) {
			statusMessage += " (";
			if (first != null) {
				drawsTaken++;
				statusMessage += first.getName();
			}
			if (second != null) {
				drawsTaken++;
				if (first != null) {
					statusMessage += ", ";
				}
				statusMessage += second.getName();
			}
			statusMessage += ")";
		}
		if (gameState == GameState.PLAYING && viewing == getActivePlayer()) {
			hasDrawnThisTurn = true; // using tactic counts as draw phase
		}
	}

	private void applyForcedMarch(Player viewing) {
		Player target = selectNextTargetFromIndex(viewingPlayerIndex);
		if (target == null) {
			statusMessage = viewing.getHeroName() + " has no target for Forced March.";
			return;
		}
		Card discarded = target.discardRandomCard();
		if (discarded != null) {
			discardPile.add(discarded);
			statusMessage = viewing.getHeroName() + " orders a Forced March, discarding " + target.getHeroName()
					+ "'s " + discarded.getName() + ".";
			return;
		}
		target.markSkipTurn();
		statusMessage = viewing.getHeroName() + " orders a Forced March. " + target.getHeroName() + " will skip their next turn.";
	}

	private void applyDamage(Player target, int dmg) {
		discardPendingAttack();
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
			statusMessage += " " + attacker.getHeroName() + "'s Vengeful Heal restores 1 HP.";
		}
	}

	private void discardPendingAttack() {
		if (pendingAttackCard != null) {
			discardPile.add(pendingAttackCard);
		}
	}

	private void clearPendingAttack() {
		pendingAttackCard = null;
		pendingTarget = null;
		if (gameState != GameState.GAME_OVER) {
			gameState = GameState.PLAYING;
			Player active = getActivePlayer();
			if (active != null) {
				viewingPlayerIndex = activePlayerIndex;
			}
		}
	}

	private void focusViewOnPlayer(Player player) {
		if (player == null || players.isEmpty()) {
			return;
		}
		int index = players.indexOf(player);
		if (index >= 0) {
			viewingPlayerIndex = index;
		}
	}

	private void checkForVictory() {
		boolean leaderAlive = false;
		int rebelsAlive = 0;
		for (Player player : players) {
			if (player.isEliminated()) {
				continue;
			}
			if (player.getRole() == Player.Role.LEADER) {
				leaderAlive = true;
			} else if (player.getRole() == Player.Role.REBEL) {
				rebelsAlive++;
			}
		}
		if (!leaderAlive) {
			winnerMessage = rebelsAlive > 0 ? "Rebels win - the Leader has fallen." : "All factions collapse.";
			scoreMessage = buildScoreMessage(false);
			roleRevealMessage = buildRoleRevealMessage();
			statusMessage = winnerMessage;
			gameState = GameState.GAME_OVER;
			return;
		}
		if (rebelsAlive == 0) {
			winnerMessage = "Leader and Loyalist win - rebels defeated.";
			scoreMessage = buildScoreMessage(true);
			roleRevealMessage = buildRoleRevealMessage();
			statusMessage = winnerMessage;
			gameState = GameState.GAME_OVER;
		}
	}

	private int findNextAliveIndex(int startIndex) {
		if (players.isEmpty()) {
			return -1;
		}
		skipNotice = null;
		for (int i = 1; i <= players.size(); i++) {
			int idx = (startIndex + i) % players.size();
			Player candidate = players.get(idx);
			if (candidate.isEliminated()) {
				continue;
			}
			if (candidate.shouldSkipTurn()) {
				candidate.consumeSkipTurn();
				skipNotice = candidate.getHeroName() + " skips the turn.";
				continue;
			}
			candidate.resetForNewTurn();
			return idx;
		}
		return -1;
	}

	private String buildRoleRevealMessage() {
		StringBuilder sb = new StringBuilder("Roles: ");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			sb.append(player.getHeroName()).append("=").append(player.getRole().getLabel());
			if (i < players.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	private String buildScoreMessage(boolean leaderFactionWins) {
		int totalHp = 0;
		for (Player player : players) {
			if (player.isEliminated()) {
				continue;
			}
			if (leaderFactionWins && (player.getRole() == Player.Role.LEADER || player.getRole() == Player.Role.LOYALIST)) {
				totalHp += player.getHp();
			} else if (!leaderFactionWins && player.getRole() == Player.Role.REBEL) {
				totalHp += player.getHp();
			}
		}
		return "Score: " + totalHp + " total HP remaining";
	}
}
