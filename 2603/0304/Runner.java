import java.util.ArrayList;
import java.util.List;


public class Runner
{
	public static void main(String args[])
	{
		MusicDownloads webMusicA = new MusicDownloads();
		webMusicA.printList();
		
		
		//Part A
		System.out.println("\nPart A");
		DownloadInfo di = webMusicA.getDownloadInfo("Aqualung");
		if( di != null )
			di.printInfo();
		else
			System.out.println("null");
		
		di = webMusicA.getDownloadInfo("Happy Birthday");
		if( di != null )
			di.printInfo();
		else
			System.out.println("null");
			
		//Part B
		System.out.println("\nPart B");
		ArrayList<String> list2 = new ArrayList<String>();
		
		list2.add("Lights");
		list2.add("Aqualung");
		list2.add("Soul Sister");
		list2.add("Go Now");
		list2.add("Lights");
		list2.add("Soul Sister");
		
		webMusicA.updateDownloads(list2); 
		webMusicA.printList();
	}
}
