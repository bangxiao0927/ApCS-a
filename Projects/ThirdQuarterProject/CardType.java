public enum CardType {
	ATTACK("Sha"),
	DODGE("Shan"),
	HEAL("Peach"),
	TACTIC("Tactic");

	private final String label;

	CardType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
