import java.awt.Color;
import java.awt.Graphics;

public class Square {
    private int red;
    private int green;
    private int blue;

    public Square(int red, int green, int blue) {
        setColor(red, green, blue);
    }

    public void setColor(int red, int green, int blue) {
        this.red = clamp(red);
        this.green = clamp(green);
        this.blue = clamp(blue);
    }

    public Color getColor() {
        return new Color(red, green, blue);
    }

    public void drawMe(Graphics graphics, int x, int y, int size) {
        graphics.setColor(getColor());
        graphics.fillRect(x, y, size, size);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, size, size);
    }

    private int clamp(int value) {
        if (value < 0) {
            return 0;
        }
        if (value > 255) {
            return 255;
        }
        return value;
    }
}
