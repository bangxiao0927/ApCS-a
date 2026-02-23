import java.util.ArrayList;
import java.util.List;

public class Player {
	public enum Ability {
		DRAGON_CONVERT,
		STRATEGIST_DRAW,
		VENGEFUL_HEAL
	}

	private final String name;
	private final String heroName;
	private final Ability ability;
	private final ArrayList<Card> hand;
	private final int maxHp;
	private int hp;
	private boolean eliminated;
	private boolean convertedThisTurn;
	private boolean healedThisTurn;

	public Player(String name, String heroName, int maxHp, Ability ability) {
		this.name = name;
		this.heroName = heroName;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.ability = ability;
		this.hand = new ArrayList<Card>();
	}

	public String getHeroName() {
		return heroName;
	}

	public String getName() {
		return name;
	}

	public Ability getAbility() {
		return ability;
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void eliminate() {
		eliminated = true;
	}

	public void resetForNewTurn() {
		convertedThisTurn = false;
		healedThisTurn = false;
	}

	public boolean hasCard(CardType type) {
		return findCardIndex(type) != -1;
	}

	public Card playCard(CardType type) {
		int idx = findCardIndex(type);
		if (idx == -1) {
			return null;
		}
		return hand.remove(idx);
	}

	public void addCard(Card card) {
		if (card != null) {
			hand.add(card);
		}
	}

	public Card convertDodgeToAttack() {
		if (ability != Ability.DRAGON_CONVERT || convertedThisTurn) {
			return null;
		}
		int idx = findCardIndex(CardType.DODGE);
		if (idx == -1) {
			return null;
		}
		Card dodge = hand.remove(idx);
		convertedThisTurn = true;
		// Converted attack keeps the same suit/value for flavor.
		return new Card(dodge.getValue(), "Sha*", dodge.getSuit(), CardType.ATTACK, "Dragon Courage strike");
	}

	public boolean canStrategistDraw() {
		return ability == Ability.STRATEGIST_DRAW;
	}

	public boolean canHealAfterDamage() {
		return ability == Ability.VENGEFUL_HEAL && !healedThisTurn;
	}

	public void markHealUsed() {
		healedThisTurn = true;
	}

	public void loseHp(int dmg) {
		hp = Math.max(0, hp - dmg);
		if (hp == 0) {
			eliminated = true;
		}
	}

	public void heal(int amount) {
		if (amount <= 0) {
			return;
		}
		hp = Math.min(maxHp, hp + amount);
		if (hp > 0) {
			eliminated = false;
		}
	}

	public int getHandSize() {
		return hand.size();
	}

	private int findCardIndex(CardType type) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getType() == type) {
				return i;
			}
		}
		return -1;
	}
}
