import java.util.ArrayList;

public class Runner{
	public static void main(String args[]){
		//What do you have to import to use ArrayList?
		
		//What do you think the ? in ArrayList< ? > represents?
		ArrayList<Song> songList1 = new ArrayList<Song>();
		
		//What do you think the method add() does?
		songList1.add(new Song("John", "Happy"));
		songList1.add(new Song("Jose", "Hello"));
		
		//What do you think the method get() does?
		//What method gets called by default when you don't use getName() ?
		System.out.println();
		System.out.println( songList1.get(0).getName() );
		System.out.println( songList1.get(1).getName() );
		System.out.println( songList1.get(0) );
		System.out.println( songList1.get(1) );
		
		//What do you think the method set() does?
		System.out.println();
		songList1.set(1, new Song("Jen", "Happy Days") );
		System.out.println( songList1.get(1) );

        //classworks works:

        ArrayList<Song> songList2 = new ArrayList<Song>();
        songList2.add(new Song("Adele", "Rolling in the Deep"));
        songList2.add(new Song("Ed Sheeran", "Shape of You"));
        songList2.add(new Song("Taylor Swift", "Blank Space"));

        System.out.println();
        System.out.println("First song added: " + songList2.get(0));
        System.out.println("Last song added: " + songList2.get(songList2.size() - 1));
	}
}
