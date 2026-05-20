public class WordGrid
{
    private String[][] grid;


    public WordGrid()
    {
        grid = new String[5][4];
            grid[0][0] = "cat";
            grid[0][1] = "crane";
            grid[0][2] = "people";
            grid[0][3] = "hamster";
            grid[1][0] = "dog";
            grid[1][1] = "slate";
            grid[1][2] = "bat";
            grid[1][3] = "green";
            grid[2][0] = "blue";
            grid[2][1] = "audio";
            grid[2][2] = "adieu";
            grid[2][3] = "snazzy";
            grid[3][0] = "yellow";
            grid[3][1] = "shirt";
            grid[3][2] = "hat";
            grid[3][3] = "store";
            grid[4][0] = "about";
            grid[4][1] = "media";
            grid[4][2] = "stone";
            grid[4][3] = "canoe";
    }


    /**
    * Returns the number of rows in grid that are ordered from
    * shortest string to longest string
    * Preconditions: grid has at least one row and at least two
    * columns.
    * No elements in grid are null.
    * Postcondition: grid is unchanged.
    */
    public int countOrderedRows()
    { /* to be implemented */ 
        int count = 0;
        for (int r = 0; r < grid.length; r++)
        {
            boolean ordered = true;
            for (int c = 0; c < grid[r].length - 1; c++)
            {
                if (grid[r][c].length() > grid[r][c + 1].length())
                {
                    ordered = false;
                    break;
                }
            }
            if (ordered)
            {
                count++;
            }
        }
        return count;
    }


    public String toString()
    {
        String result = "";
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                result += grid[r][c] + "\t";
            }
            result += "\n";
        }
        return result;
    }




    /* There may be instance variables, constructors,
    and methods that are not shown. */
}
