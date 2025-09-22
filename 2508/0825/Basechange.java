class Basechange {
    public static void main(String[] args) {
        System.out.println(baseChange("15", 10, 2));
    }

    private static String baseChange(String number, int base, int baseToConvert) {
        int baseTen = Integer.parseInt(number, base);
        return Integer.toString(baseTen, baseToConvert);
    }
}
