import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game {
    private Player skydiver;
    private Target landingZone;
    private double windSpeed;
    private boolean gameOver;
    private boolean landed;
    private String resultMessage;
    
    private static final int GROUND_LEVEL = 550;
    private static final int SKY_START = 50;

    public Game(){
        startNewJump();
    }
    
    public void startNewJump(){
        skydiver = new Player(400, SKY_START);
        landingZone = new Target(350, GROUND_LEVEL - 75);
        
        // Randomize wind speed for each jump (-3 to 3)
        windSpeed = (Math.random() * 6) - 3;
        
        gameOver = false;
        landed = false;
        resultMessage = "";
    }

    public void update(){
        if(!gameOver && !landed){
            // Apply gravity to skydiver
            skydiver.applyGravity();
            
            // Apply wind physics
            skydiver.applyWind(windSpeed);
            
            // Check if skydiver has reached the ground
            if(skydiver.getY() >= GROUND_LEVEL - skydiver.getHeight()){
                landed = true;
                checkLanding();
            }
        }
    }
    
    private void checkLanding(){
        // Check if skydiver landed in the target zone
        int skydiverCenterX = skydiver.getX() + skydiver.getWidth()/2;
        int targetLeft = landingZone.getX();
        int targetRight = landingZone.getX() + landingZone.getWidth();
        
        if(skydiverCenterX >= targetLeft && skydiverCenterX <= targetRight){
            resultMessage = "Perfect Landing!";
        } else {
            int distance = Math.min(
                Math.abs(skydiverCenterX - targetLeft),
                Math.abs(skydiverCenterX - targetRight)
            );
            resultMessage = "Missed by " + distance + " pixels!";
        }
        gameOver = true;
    }

    public void drawMe(Graphics g){
        // Draw sky background
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, GROUND_LEVEL);
        
        // Draw ground
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, GROUND_LEVEL, 800, 50);
        
        // Draw landing zone
        landingZone.drawMe(g);
        
        // Draw skydiver
        skydiver.drawMe(g);
        
        // Draw wind indicator
        drawWindIndicator(g);
        
        // Draw HUD
        drawHUD(g);
        
        // Draw result message if game over
        if(gameOver){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString(resultMessage, 250, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press SPACE to jump again", 260, 350);
        }
    }
    
    private void drawWindIndicator(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Wind: ", 10, 30);
        
        // Draw wind arrow
        int arrowX = 70;
        int arrowY = 25;
        int arrowLength = (int)(Math.abs(windSpeed) * 10);
        
        if(windSpeed > 0){
            // Right wind
            g.drawLine(arrowX, arrowY, arrowX + arrowLength, arrowY);
            g.drawLine(arrowX + arrowLength, arrowY, arrowX + arrowLength - 5, arrowY - 5);
            g.drawLine(arrowX + arrowLength, arrowY, arrowX + arrowLength - 5, arrowY + 5);
        } else if(windSpeed < 0){
            // Left wind
            g.drawLine(arrowX, arrowY, arrowX - arrowLength, arrowY);
            g.drawLine(arrowX - arrowLength, arrowY, arrowX - arrowLength + 5, arrowY - 5);
            g.drawLine(arrowX - arrowLength, arrowY, arrowX - arrowLength + 5, arrowY + 5);
        }
        
        g.drawString(String.format("%.1f", windSpeed), arrowX + 40, 30);
    }
    
    private void drawHUD(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Display altitude
        int altitude = GROUND_LEVEL - skydiver.getY() - skydiver.getHeight();
        g.drawString("Altitude: " + altitude, 10, 60);
        
        // Display velocity
        g.drawString(String.format("Speed: %.1f", skydiver.getVelocityY()), 10, 85);
        
        // Display controls
        g.drawString("Controls: LEFT/RIGHT arrows", 600, 30);
    }

    public void movePlayer(String direction){
        if(!gameOver && !landed){
            if(direction.equals("left")){
                skydiver.moveLeft();
            } else if(direction.equals("right")){
                skydiver.moveRight();
            }
        }
    }
    
    public void restartGame(){
        if(gameOver){
            startNewJump();
        }
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
}