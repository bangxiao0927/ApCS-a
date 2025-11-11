public class ArrayTest {
    public void arrayDemo1() {
        System.out.println("Demo 1");
        int[] arr = new int[5];
        arr[0] = (int)(Math.random()*15+1);
        arr[1] = (int)(Math.random()*15+1);
        arr[2] = (int)(Math.random()*15+1);
        arr[3] = (int)(Math.random()*15+1);
        arr[4] = (int)(Math.random()*15+1);
        
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);
    }

    public void arrayDemo2() {
        System.out.println("Demo 2");
        int[] arr = new int[25];
        for(int i=0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*9+1);
        }
        for(int i=0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void arrayDemo3() {
        System.out.println("Demo 3");
        String[] arr = new String[5];
        arr[0] = "apple";
        arr[1] = "banana";
        arr[2] = "cherry";
        arr[3] = "date";
        arr[4] = "elderberry";
        
        for (String arr1 : arr) {
            System.out.println(arr1);
        }
    }

    public void arrayDemo4() {
        System.out.println("Demo 4");
        Profile[] profiles = new Profile[5];
        profiles[0] = new Profile("Alice");
        profiles[1] = new Profile("Bob");
        profiles[2] = new Profile("Charlie");
        profiles[3] = new Profile("Diana");
        profiles[4] = new Profile("Ethan");
        
        for (Profile profile : profiles) {
            String name = profile.getName();
            System.out.println(name);
        }
    }
}
