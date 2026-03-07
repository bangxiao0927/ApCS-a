public class StrategistPlayer extends Player {
	public StrategistPlayer(String name, String heroName, int maxHp, Role role) {
		super(name, heroName, maxHp, Ability.NONE, role);
	}

	@Override
	public int getDodgeBonusDraw() {
		return 1;
	}
}
