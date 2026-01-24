public class Phrase 
{ 
    private String currentPhrase; 

    public Phrase(String p) 
    { 
        currentPhrase = p; 
    } 

    public int findNthOccurrence(String str, int n) 
    { 
        int occurrence = 0;
        
        for(int i=0; i<currentPhrase.length(); i++)
        {
            if( i+str.length() < currentPhrase.length() )
            {
                if( currentPhrase.substring(i,i+str.length()).equals(str) )
                {
                    occurrence++;
                    if( occurrence == n )
                        return i;
                }
            }
        }
        return -1;
    }
    


    public void replaceNthOccurrence(String str, int n, String repl) 
    { /* to be implemented in part (a) */ 
        int indexOfNth = findNthOccurrence(str,n);
        if (indexOfNth != -1) {
            currentPhrase = currentPhrase.substring(0,indexOfNth) + repl + 
            currentPhrase.substring(indexOfNth + str.length(), currentPhrase.length());
        }
    }




    public int findLastOccurrence(String str) 
    { /* to be implemented in part (b) */ 
        int maxOccurenceTimes = currentPhrase.length() / str.length();
        for (int i = maxOccurenceTimes; i >= 1; i--) {
            if (findNthOccurrence(str, i) != -1) {
                return findNthOccurrence(str, i);
            }
        }
        return -1;
    }


    public String toString() 
    { 
        return currentPhrase;
    } 


}
