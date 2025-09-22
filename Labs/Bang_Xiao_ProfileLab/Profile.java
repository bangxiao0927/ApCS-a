

public class Profile {
    private String name;
    private int age;
    private String subject;
    private String hobby;
    private String food;

    public Profile(String name, int age, String subject, String hobby, String food) {
        this.name = name;
        this.age = age;
        this.subject = subject;
        this.hobby = hobby;
        this.food = food;
    }

    public void printInfo() {
        printCareer();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Favorite Subject: " + subject);
        System.out.println("Hobby: " + hobby);
        System.out.println("Favorite Food: " + food);
    }

    public void printCareer() {
        if(subject.equals("Math")) {
            if(hobby.equals("Coding")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: Software Engineer");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: Data Scientist");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
            else if(hobby.equals("Hiking")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: Game Developer");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: 3D Animator");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
            else {
            System.out.println("Career: Unemployed");
            }
        }
        else if(subject.equals("Science")) {
            if(hobby.equals("Coding")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: Biomedical Engineer");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: Environmental Scientist");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
            else if(hobby.equals("Hiking")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: Astronomer");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: Doctor");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
        }
        else if(subject.equals("English")) {
            if(hobby.equals("Coding")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: Fiction Writer");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: Editor");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
            else if(hobby.equals("Hiking")) {
                if(food.equals("Pizza")) {
                    System.out.println("Career: English Teacher");
                }
                else if(food.equals("Sushi")) {
                    System.out.println("Career: Journalist");
                }
                else {
                    System.out.println("Career: Unemployed");
                }
            }
            else {
                System.out.println("Career: Unemployed");
            }
        }
        else {
            System.out.println("Career: Unemployed");
        }
    }

    public void updateProfile(String name, int age, String subject, String hobby, String food) {
        this.name = name;
        this.age = age;
        this.subject = subject;
        this.hobby = hobby;
        this.food = food;
    }
}


