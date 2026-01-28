public class Song{
	private String artist;
	private String name;
	
	public Song(String artist, String name){
		this.artist = artist;
		this.name = name;
	}
	
	public String toString(){
		return artist + " - " + name;
	}
	
	public String getName(){
		return name;
	}
}
