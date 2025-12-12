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
        this(x, y, 100, 75);
    }
    
    public Target(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetColor = new Color(255, 0, 0, 150);
        this.centerColor = new Color(255, 255, 255, 200);
    }
    
    public void drawMe(Graphics g){
        // Draw target zone with concentric circles
        
        // Outer circle (red)
        g.setColor(targetColor);
        g.fillOval(x, y, width, height);
        
        // Only draw inner circles if target is large enough
        if(width > 40){
            // Middle circle (white)
            g.setColor(centerColor);
            int middleInset = width / 6;
            g.fillOval(x + middleInset, y + middleInset, width - middleInset*2, height - middleInset*2);
            
            // Inner circle (red)
            g.setColor(targetColor);
            int innerInset = width / 3;
            g.fillOval(x + innerInset, y + innerInset, width - innerInset*2, height - innerInset*2);
        }
        
        // Draw crosshair in center
        g.setColor(Color.WHITE);
        int centerX = x + width/2;
        int centerY = y + height/2;
        int crosshairSize = Math.min(10, width/4);
        g.drawLine(centerX - crosshairSize, centerY, centerX + crosshairSize, centerY);
        g.drawLine(centerX, centerY - crosshairSize, centerX, centerY + crosshairSize);
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