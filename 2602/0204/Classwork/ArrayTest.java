import java.util.ArrayList;
public class Runner {
    public void printList(ArrayList<Card> cardList){
        for(Card card : cardList){
            System.out.println(card);
        }
    }
    public int findTotal(ArrayList<Card> cardList){
        int total = 0;
        for(Card card : cardList){
            total += card.getValue();
        }
        return total;
    }
    public void searchAndDelete(ArrayList<Card> cardList, int value){
        for(int i = cardList.size() - 1; i >= 0; i--){
            if(cardList.get(i).getValue() == value){
                cardList.remove(i);
            }
        }
    }
    public void scramble(ArrayList<Card> cardList){
        ArrayList<Card> tempList = new ArrayList<>(cardList);
        cardList.clear();
        while(!tempList.isEmpty()){
            int index = (int)(Math.random() * tempList.size());
            cardList.add(tempList.remove(index));
        }
    }
    public void searchAndReplace(ArrayList<Card> cardList, int value){
        for(int i = 0; i < cardList.size(); i++){
            if(cardList.get(i).getValue() == value){
                cardList.set(i, new Card(99));
            }
        }
    }
    public void sort(ArrayList<Card> cardList){
        for(int i = 0; i < cardList.size() - 1; i++){
            for(int j = 0; j < cardList.size() - 1 - i; j++){
                if(cardList.get(j).getValue() > cardList.get(j + 1).getValue()){
                    Card temp = cardList.get(j);
                    cardList.set(j, cardList.get(j + 1));
                    cardList.set(j + 1, temp);
                }
            }
        }
    }
}   