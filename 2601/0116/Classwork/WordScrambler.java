public class WordScrambler
{
	private String[] scrambledWords;
	
	
	/** @param wordArr an array of String objects
	* Precondition: wordArr.length is even
	*/
	public WordScrambler(String[] wordArr)
	{
		scrambledWords = mixedWords(wordArr);
	}
	
	public String testPartA(String word1, String word2)
	{
		return recombine(word1, word2);
	}
	
	
	/** @param word1 a String of characters
	* @param word2 a String of characters
	* @return a String that contains the first half of word1 and the second half of word2
	*/
	private String recombine(String word1, String word2)
	{ /* to be implemented in part (a) */
        return word1.substring(0, word1.length() / 2) + word2.substring(word2.length() / 2);
	}
	
	public void testPartB()
	{
		System.out.print("(");
		for(int i=0; i < scrambledWords.length; i++)
		{
			System.out.print( scrambledWords[i] );
			if( i < scrambledWords.length-1 )
				System.out.print(", ");
		}
		System.out.println(")");		
	}
	
	
	/** @param words an array of String objects
	* Precondition: words.length is even
	* @return an array of String objects created by recombining pairs of strings in array words
	* Postcondition: the length of the returned array is words.length
	*/
	private String[] mixedWords(String[] words)
	{ /* to be implemented in part (b) */ 
        String[] result = new String[words.length];
        for (int i = 0; i < words.length; i += 2) {
            result[i] = recombine(words[i], words[i + 1]);
            result[i + 1] = recombine(words[i + 1], words[i]);
        }
        return result;
	}
}