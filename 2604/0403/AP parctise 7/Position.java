public class Position
{
	private int r;
	private int c;
    
	public Position(int r, int c)
	{
		this.r = r;
		this.c = c;
	}
	
	public String toString()
	{
		return "("+r+", "+c+")";
	}
}
