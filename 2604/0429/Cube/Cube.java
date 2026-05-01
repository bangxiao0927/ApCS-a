public class Cube
{
    private String[] cube;


    /**
     * Constructs a Cube object. If the parameter is
     * equal to "number", the cube will be a number
     * cube. If the parameter is equal to "letter", the
     * cube will be a letter cube. If the parameter is
     * equal to "color", the cube will be a color cube.
     * Precondition: cubeType is equal to "number",
     * "letter", or "color".
     */
    public Cube(String cubeType)
    { 
        cube = new String[6];


        if( cubeType.equals("number") )
        {
            cube[0] = "1";
            cube[1] = "2";
            cube[2] = "3";
            cube[3] = "4";
            cube[4] = "5";
            cube[5] = "6";
        }
        else if( cubeType.equals("letter") )
        {
            cube[0] = "A";
            cube[1] = "B";
            cube[2] = "C";
            cube[3] = "D";
            cube[4] = "E";
            cube[5] = "F";
        }
        else if( cubeType.equals("color") )
        {
            cube[0] = "red";
            cube[1] = "orange";
            cube[2] = "yellow";
            cube[3] = "green";
            cube[4] = "blue";
            cube[5] = "purple";
        }
    }




    /**
     * Returns "1", "2", "3", "4", "5", or "6" if
     * the cube is a number cube, returns "A", "B",
     * "C", "D", "E", or "F" if the cube is a
     * letter cube, and returns "red", "orange",
     * "yellow", "green", "blue", or "purple" if
     * the cube is a color cube
     */
    public String roll()
    { 
        int index = (int)(Math.random() * 6);
        return cube[index];
    }
 }
