public class CubePair {
    private Cube cubeA;
    private Cube cubeB;

    public CubePair(String type) {
        cubeA = new Cube(type);
        cubeB = new Cube(type);
    }  

    public CubePair(String typeA, String typeB) {
        cubeA = new Cube(typeA);
        cubeB = new Cube(typeB);
    }

    public String rollCubes() {
        String result = cubeA.roll() + cubeB.roll();
        return result;
    }
}
