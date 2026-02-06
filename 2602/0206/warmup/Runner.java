import java.util.ArrayList;
public class Runner {
    public static void main(String[] args) {
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Cereal", 4.99));
        itemList.add(new Item("Milk", 3.99));
        itemList.add(new Item("Water", 0.99));  
        double total = 0;


        for(Item item : itemList){
            total += item.getPrice();
        }

        System.out.println("Total: " + total);

        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getPrice() == 0.99) { 
                itemList.set(i, new Item(itemList.get(i).getName(), 1.25));
            }
        }
        
        for(Item item : itemList){
            System.out.println(item);
        }
    }
}