import java.awt.Color;
import java.awt.Graphics;

public class Player{
    private double x;
    private double y;
    
    private int width;
    private int height;
    
    private double velocityY;
    private double velocityX;
    
    private boolean parachuteOpen;
    private boolean crashed;
    
    private static final double GRAVITY = 0.15;
    private static final double PARACHUTE_GRAVITY = 0.03;
    private static final double MAX_FALL_SPEED = 8.0;
    private static final double MAX_PARACHUTE_SPEED = 2.5;
    private static final double AIR_CONTROL = 0.8;
    private static final double PARACHUTE_CONTROL = 0.5;
    private static final double SAFE_LANDING_SPEED = 3.0;
    
    private Color skydiverColor;
    private Color parachuteColor;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 40;
        this.velocityY = 0;
        this.velocityX = 0;
        this.parachuteOpen = false;
        this.crashed = false;
        this.skydiverColor = new Color(255, 140, 0);
        this.parachuteColor = new Color(255, 50, 50);
    }
    
    public void applyGravity(){
        double currentGravity = parachuteOpen ? PARACHUTE_GRAVITY : GRAVITY;
        double maxSpeed = parachuteOpen ? MAX_PARACHUTE_SPEED : MAX_FALL_SPEED;
        
        velocityY += currentGravity;
        
        if(velocityY > maxSpeed){
            velocityY = maxSpeed;
        }
        
        y += velocityY;
        
        velocityX *= 0.98;
    }
    
    public void applyWind(double windSpeed){
        double windEffect = parachuteOpen ? 0.15 : 0.3;
        
        x += windSpeed * windEffect;
        
        if(x < 0){
            x = 0;
            velocityX = 0;
        }
        if(x > 800 - width){
            x = 800 - width;
            velocityX = 0;
        }
        
        x += velocityX;
    }
    
    public void openParachute(){
        if(!parachuteOpen){
            parachuteOpen = true;
            velocityY *= 0.4;
        }
    }
    
    public boolean isParachuteOpen(){
        return parachuteOpen;
    }
    
    public void crash(){
        crashed = true;
        skydiverColor = new Color(100, 0, 0); // Dark red for crashed state
    }
    
    public boolean isCrashed(){
        return crashed;
    }
    
    public void drawMe(Graphics g){
        if(parachuteOpen && !crashed){
            g.setColor(parachuteColor);
            int parachuteWidth = 80;
            int parachuteHeight = 50;
            int parachuteX = (int)x - 25;
            int parachuteY = (int)y - 45;
            
            g.fillArc(parachuteX, parachuteY, parachuteWidth, parachuteHeight, 0, 180);
            
            g.setColor(Color.WHITE);
            g.drawLine(parachuteX + 10, parachuteY + 25, (int)x + 10, (int)y + 10);
            g.drawLine(parachuteX + 30, parachuteY + 15, (int)x + 15, (int)y + 10);
            g.drawLine(parachuteX + 50, parachuteY + 15, (int)x + 15, (int)y + 10);
            g.drawLine(parachuteX + 70, parachuteY + 25, (int)x + 20, (int)y + 10);
        }
        
        g.setColor(skydiverColor);
        
        if(crashed){
            // Draw crashed state - flat on ground
            g.fillRect((int)x, (int)y + 20, 30, 10);
            g.fillOval((int)x + 7, (int)y + 15, 16, 16);
        } else {
            // Normal skydiver
            g.fillRect((int)x + 10, (int)y + 10, 10, 20);
            
            g.fillOval((int)x + 7, (int)y, 16, 16);
            
            if(parachuteOpen){
                g.fillRect((int)x + 5, (int)y + 15, 4, 10);
                g.fillRect((int)x + 21, (int)y + 15, 4, 10);
            } else {
                g.fillRect((int)x, (int)y + 15, 10, 4);
                g.fillRect((int)x + 20, (int)y + 15, 10, 4);
            }
            
            g.fillRect((int)x + 8, (int)y + 30, 4, 10);
            g.fillRect((int)x + 18, (int)y + 30, 4, 10);
        }
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
        double control = parachuteOpen ? PARACHUTE_CONTROL : AIR_CONTROL;
        velocityX -= control;
        
        double maxHorizontalSpeed = parachuteOpen ? 2 : 3;
        if(velocityX < -maxHorizontalSpeed){
            velocityX = -maxHorizontalSpeed;
        }
    }
    
    public void moveRight(){
        double control = parachuteOpen ? PARACHUTE_CONTROL : AIR_CONTROL;
        velocityX += control;
        
        double maxHorizontalSpeed = parachuteOpen ? 2 : 3;
        if(velocityX > maxHorizontalSpeed){
            velocityX = maxHorizontalSpeed;
        }
    }
}