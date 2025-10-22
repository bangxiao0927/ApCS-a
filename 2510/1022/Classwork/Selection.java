public class Selection {
    public void gradeCalculator(int grade) {
        if (grade <= 100 && grade >= 90) {
            System.out.println("A");
        } else if (grade <= 89 && grade >= 80) {
            System.out.println("B");
        } else if (grade >= 70) {
            System.out.println("C");
        } else if (grade >= 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }

    public String zipCode1(int zipCode) {
        if (zipCode == 94040) {
            return "Mountain View";
        } else if (zipCode == 94115) {
            return "San Francisco";
        } else if (zipCode == 95051) {
            return "Santa Clara";
        } else if (zipCode == 95129) {
            return "San Jose";
        } else if (zipCode == 94607) {
            return "Oakland";
        } else if (zipCode == 95035) {
            return "Milpitas";
        } else {
            return "Not Valid";
        }
    }

    public String zipCode2(int zipCode) {
        switch (zipCode) {
            case 94040:
                return "Mountain View";
            case 94115:
                return "San Francisco";
            case 95051:
                return "Santa Clara";
            case 95129:
                return "San Jose";
            case 94607:
                return "Oakland";
            case 95035:
                return "Milpitas";
            default:
                return "Not Valid";
        }
    }
}
