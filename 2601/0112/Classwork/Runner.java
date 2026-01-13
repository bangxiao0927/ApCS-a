public class Runner {
    public static void main(String[] args)
    {
        Split sp = new Split("john.smith@mvla.net");
        String name = sp.getName();
        System.out.println(name);
        String domain = sp.getDomain();
        System.out.println(domain);
        String lastName = sp.getLastNameMVLA("jane.doe@mvla.net");
        System.out.println(lastName);
        System.out.println(sp);
    }
}