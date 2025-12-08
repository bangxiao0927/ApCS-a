import java.awt.Color;
import java.awt.Graphics;

public class Target{
    private int x;
    private int y;
    
    private int width;
    private int height;
    
    private Color targetColor;
    private Color centerColor;

    public Target(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 75;
        this.targetColor = new Color(255, 0, 0, 150); // Semi-transparent red
        this.centerColor = new Color(255, 255, 255, 200); // Semi-transparent white
    }
    
    public void drawMe(Graphics g){
        // Draw target zone with concentric circles/rectangles
        
        // Outer circle (red)
        g.setColor(targetColor);
        g.fillOval(x, y, width, height);
        
        // Middle circle (white)
        g.setColor(centerColor);
        g.fillOval(x + 15, y + 15, width - 30, height - 30);
        
        // Inner circle (red)
        g.setColor(targetColor);
        g.fillOval(x + 30, y + 30, width - 60, height - 60);
        
        // Draw crosshair in center
        g.setColor(Color.WHITE);
        int centerX = x + width/2;
        int centerY = y + height/2;
        g.drawLine(centerX - 10, centerY, centerX + 10, centerY);
        g.drawLine(centerX, centerY - 10, centerX, centerY + 10);
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}