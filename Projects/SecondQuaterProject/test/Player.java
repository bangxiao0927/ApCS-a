import java.awt.Color;
import java.awt.Graphics;

public class Player{
    private double x;
    private double y;
    
    private int width;
    private int height;
    
    private double velocityY;
    private double velocityX;
    
    private static final double GRAVITY = 0.15;
    private static final double MAX_FALL_SPEED = 8.0;
    private static final double AIR_CONTROL = 0.8;
    
    private Color skydiverColor;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 40;
        this.velocityY = 0;
        this.velocityX = 0;
        this.skydiverColor = new Color(255, 140, 0); // Orange color
    }
    
    public void applyGravity(){
        // Increase downward velocity
        velocityY += GRAVITY;
        
        // Cap maximum fall speed
        if(velocityY > MAX_FALL_SPEED){
            velocityY = MAX_FALL_SPEED;
        }
        
        // Update position
        y += velocityY;
        
        // Apply air resistance to horizontal movement
        velocityX *= 0.98;
    }
    
    public void applyWind(double windSpeed){
        // Wind affects horizontal position
        x += windSpeed * 0.3;
        
        // Keep player on screen
        if(x < 0){
            x = 0;
            velocityX = 0;
        }
        if(x > 800 - width){
            x = 800 - width;
            velocityX = 0;
        }
        
        // Apply horizontal velocity
        x += velocityX;
    }
    
    public void drawMe(Graphics g){
        // Draw skydiver as a simple figure
        g.setColor(skydiverColor);
        
        // Body
        g.fillRect((int)x + 10, (int)y + 10, 10, 20);
        
        // Head
        g.fillOval((int)x + 7, (int)y, 16, 16);
        
        // Arms (spread out during freefall)
        g.fillRect((int)x, (int)y + 15, 10, 4);
        g.fillRect((int)x + 20, (int)y + 15, 10, 4);
        
        // Legs
        g.fillRect((int)x + 8, (int)y + 30, 4, 10);
        g.fillRect((int)x + 18, (int)y + 30, 4, 10);
    }
    
    public int getX(){
        return (int)x;
    }
    
    public int getY(){
        return (int)y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public double getVelocityY(){
        return velocityY;
    }

    public void moveLeft(){
        // Apply leftward force during freefall
        velocityX -= AIR_CONTROL;
        
        // Cap horizontal speed
        if(velocityX < -3){
            velocityX = -3;
        }
    }
    
    public void moveRight(){
        // Apply rightward force during freefall
        velocityX += AIR_CONTROL;
        
        // Cap horizontal speed
        if(velocityX > 3){
            velocityX = 3;
        }
    }
}