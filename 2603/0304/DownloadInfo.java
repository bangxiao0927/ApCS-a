import java.util.ArrayList;
import java.util.List;


public class DownloadInfo 
{ 
 /** Creates a new instance with the given unique title and sets the 
 * number of times downloaded to 1. 
 * @param title the unique title of the downloaded song 
 */ 
 private String title;
 private int downloaded = 1;
 
 public DownloadInfo(String title) 
 {
	this.title = title;
 } 
 
 
 /** @return the title */ 
 public String getTitle() 
 {
	return title;
 } 
 
 
 /** Increment the number times downloaded by 1 */ 
 public void incrementTimesDownloaded() 
 {
	downloaded++;
 } 
 
 public void printInfo()
 {
	System.out.println( getTitle() + " : " + downloaded);
 }
 
 // There may be instance variables, constructors, and methods that are not shown. 
}
