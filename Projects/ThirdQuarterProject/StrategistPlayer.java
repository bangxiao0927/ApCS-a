public class StrategistPlayer extends Player {
	//generaing a player with the strategist ability, which allows them to draw an extra card when they dodge an attack
	public StrategistPlayer(String name, String heroName, int maxHp, Role role) {
		super(name, heroName, maxHp, Ability.NONE, role);
	}

	@Override
	public int getDodgeBonusDraw() {
		return 1;
	}
}
