//15,35,79,85,90,100
//this is failed test code

public class BaseCtest {
    public static void main(String[] args) {
        System.out.println(baseChange("15", 10, 2));
    }
    
    private static String baseChange(String number, int base ,int baseToConvert) {
        int[] number_array = new int[number.length()];
        int baseTennumber = 0;
        for (int i = 0; i < number_array.length; i++) {
            number_array[i] = Character.getNumericValue(number.charAt(i));
            baseTennumber += number_array[i] * Math.pow(base, number_array.length - 1 - i);
        }
        StringBuilder converted = new StringBuilder();
        int highestDigit = 0;
        for(int digit = 0; digit < baseTennumber; digit++) {
            if(baseTennumber - Math.pow(baseToConvert, digit) < 0) {
                highestDigit = digit - 1;
                break;
            }
            else if(baseTennumber - Math.pow(baseToConvert, digit) == 0) {
                converted.append(1);
                for (int i = 0; i < highestDigit; i++) {
                    converted.append(0);
                }
                return converted.toString();
            }
            converted.insert(0, digit);
        }
        return converted.toString();
    }
}