public class One {
    private int length;
    private int width;

    public One() {
        length = 0;
        width = 0;
    }

    public One(int len, int wid){
        length = len;
        width = wid;
    }

    public void printArea() {
        int area = length * width;
        System.out.println("Area: " + area);
    }

    public void printArea(int len, int wid) {
        int area = len * wid;
        System.out.println("Area: " + area);
    }
}   