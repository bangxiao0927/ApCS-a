public class StringTest
{
    private String word;

    public StringTest(String word)
    {
        this.word = word;
    }

    private char getFirstLetter()
    {
        return word.charAt(0);
    }

    private char getLastLetter()
    {
        return word.charAt(word.length() - 1);
    }

    public void printWordGame()
    {
        System.out.print(getFirstLetter() + " ");
        for (int i = 1; i < word.length() - 1; i++)
        {
            System.out.print("* ");
        }
        if (word.length() > 1)
        {
            System.out.print(getLastLetter());
        }
        System.out.println();
    }
}