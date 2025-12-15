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
    private double parachuteDeployProgress; // 0.0 to 1.0, tracks deployment animation
    
    private static final double GRAVITY = 0.15;
    private static final double PARACHUTE_GRAVITY = 0.03;
    private static final double MAX_FALL_SPEED = 8.0;
    private static final double MAX_PARACHUTE_SPEED = 2.0; // Changed from 2.5 to 2.0
    private static final double AIR_CONTROL = 0.8;
    private static final double PARACHUTE_CONTROL = 0.5;
    private static final double SAFE_LANDING_SPEED = 3.0;
    private static final double PARACHUTE_DEPLOY_RATE = 0.015; // Changed from 0.05 to 0.015 - much slower deployment
    
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
        this.parachuteDeployProgress = 0.0;
        this.skydiverColor = new Color(255, 140, 0);
        this.parachuteColor = new Color(255, 50, 50);
    }
    
    public void applyGravity(){
        // Gradually deploy parachute if it's been opened
        if(parachuteOpen && parachuteDeployProgress < 1.0){
            parachuteDeployProgress += PARACHUTE_DEPLOY_RATE;
            if(parachuteDeployProgress > 1.0){
                parachuteDeployProgress = 1.0;
            }
        }
        
        // Interpolate between freefall and parachute gravity based on deployment progress
        double currentGravity = GRAVITY;
        double maxSpeed = MAX_FALL_SPEED;
        
        if(parachuteOpen){
            // Smoothly transition gravity and max speed as parachute deploys
            currentGravity = GRAVITY + (PARACHUTE_GRAVITY - GRAVITY) * parachuteDeployProgress;
            maxSpeed = MAX_FALL_SPEED + (MAX_PARACHUTE_SPEED - MAX_FALL_SPEED) * parachuteDeployProgress;
        }
        
        // Increase downward velocity
        velocityY += currentGravity;
        
        // Cap maximum fall speed
        if(velocityY > maxSpeed){
            velocityY = maxSpeed;
        }
        
        // Update position
        y += velocityY;
        
        // Apply air resistance to horizontal movement
        velocityX *= 0.98;
    }
    
    public void applyWind(double windSpeed){
        // Wind effect changes based on parachute deployment progress
        double windEffect = 0.3;
        if(parachuteOpen){
            // Wind has less effect as parachute deploys (gradually)
            windEffect = 0.3 + (0.15 - 0.3) * parachuteDeployProgress;
        }
        
        // Wind affects horizontal position
        x += windSpeed * windEffect;
        
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
    
    public void openParachute(){
        if(!parachuteOpen){
            parachuteOpen = true;
            // Don't instantly slow down - let the deployment animation handle it
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
            // Draw parachute with deployment animation
            g.setColor(parachuteColor);
            int parachuteWidth = (int)(80 * parachuteDeployProgress);
            int parachuteHeight = (int)(50 * parachuteDeployProgress);
            int parachuteX = (int)x - (int)(25 * parachuteDeployProgress);
            int parachuteY = (int)y - (int)(45 * parachuteDeployProgress);
            
            if(parachuteWidth > 10){ // Only draw if parachute has started opening
                g.fillArc(parachuteX, parachuteY, parachuteWidth, parachuteHeight, 0, 180);
                
                // Draw parachute lines (fade in with deployment)
                if(parachuteDeployProgress > 0.3){
                    g.setColor(Color.WHITE);
                    g.drawLine(parachuteX + (int)(10 * parachuteDeployProgress), 
                              parachuteY + (int)(25 * parachuteDeployProgress), 
                              (int)x + 10, (int)y + 10);
                    g.drawLine(parachuteX + (int)(30 * parachuteDeployProgress), 
                              parachuteY + (int)(15 * parachuteDeployProgress), 
                              (int)x + 15, (int)y + 10);
                    g.drawLine(parachuteX + (int)(50 * parachuteDeployProgress), 
                              parachuteY + (int)(15 * parachuteDeployProgress), 
                              (int)x + 15, (int)y + 10);
                    g.drawLine(parachuteX + (int)(70 * parachuteDeployProgress), 
                              parachuteY + (int)(25 * parachuteDeployProgress), 
                              (int)x + 20, (int)y + 10);
                }
            }
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
            
            // Arms gradually move down as parachute deploys
            if(parachuteOpen && parachuteDeployProgress > 0.5){
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
        // Control changes based on parachute deployment progress
        double control = AIR_CONTROL;
        double maxHorizontalSpeed = 3;
        
        if(parachuteOpen){
            control = AIR_CONTROL + (PARACHUTE_CONTROL - AIR_CONTROL) * parachuteDeployProgress;
            maxHorizontalSpeed = 3 + (2 - 3) * parachuteDeployProgress;
        }
        
        velocityX -= control;
        
        if(velocityX < -maxHorizontalSpeed){
            velocityX = -maxHorizontalSpeed;
        }
    }
    
    public void moveRight(){
        // Control changes based on parachute deployment progress
        double control = AIR_CONTROL;
        double maxHorizontalSpeed = 3;
        
        if(parachuteOpen){
            control = AIR_CONTROL + (PARACHUTE_CONTROL - AIR_CONTROL) * parachuteDeployProgress;
            maxHorizontalSpeed = 3 + (2 - 3) * parachuteDeployProgress;
        }
        
        velocityX += control;
        
        if(velocityX > maxHorizontalSpeed){
            velocityX = maxHorizontalSpeed;
        }
    }
}