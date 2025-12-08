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
    
    // Game state management
    public enum GameState {
        MAIN_MENU,
        LEVEL_SELECT,
        RECORDS,
        PLAYING
    }
    
    private GameState currentState;

    public Game(){
        currentState = GameState.MAIN_MENU;
    }
    
    public void startNewJump(){
        skydiver = new Player(400, SKY_START);
        landingZone = new Target(350, GROUND_LEVEL - 75);
        
        // Randomize wind speed for each jump (-3 to 3)
        windSpeed = (Math.random() * 6) - 3;
        
        gameOver = false;
        landed = false;
        resultMessage = "";
        currentState = GameState.PLAYING;
    }

    public void update(){
        if(currentState == GameState.PLAYING && !gameOver && !landed){
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
        switch(currentState){
            case MAIN_MENU:
                drawMainMenu(g);
                break;
            case LEVEL_SELECT:
                drawLevelSelect(g);
                break;
            case RECORDS:
                drawRecords(g);
                break;
            case PLAYING:
                drawGameplay(g);
                break;
        }
    }
    
    private void drawMainMenu(Graphics g){
        // Background
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, 600);
        
        // Draw clouds
        g.setColor(Color.WHITE);
        g.fillOval(100, 100, 120, 60);
        g.fillOval(500, 150, 150, 70);
        g.fillOval(300, 80, 100, 50);
        
        // Title
        g.setColor(new Color(255, 140, 0));
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString("SKY DIVER", 180, 150);
        
        // Subtitle
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.ITALIC, 24));
        g.drawString("Master the winds, nail the landing", 200, 200);
        
        // Draw menu buttons (these will be clickable areas)
        drawButton(g, 300, 280, 200, 60, "Level Select", new Color(255, 140, 0));
        drawButton(g, 300, 360, 200, 60, "Records", new Color(50, 150, 255));
        
        // Instructions
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Click on a button to continue", 270, 500);
    }
    
    private void drawLevelSelect(Graphics g){
        // Background
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, 600);
        
        // Title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Level Select", 250, 80);
        
        // Level buttons
        drawButton(g, 150, 150, 180, 80, "Level 1\nBeginner", new Color(100, 255, 100));
        drawButton(g, 150, 260, 180, 80, "Level 2\nIntermediate", new Color(255, 255, 100));
        drawButton(g, 150, 370, 180, 80, "Level 3\nAdvanced", new Color(255, 150, 100));
        
        drawButton(g, 450, 150, 180, 80, "Level 4\nExpert", new Color(255, 100, 100));
        drawButton(g, 450, 260, 180, 80, "Level 5\nMaster", new Color(200, 100, 255));
        drawButton(g, 450, 370, 180, 80, "Endless\nMode", new Color(255, 140, 0));
        
        // Back button
        drawButton(g, 300, 500, 200, 50, "Back to Menu", new Color(150, 150, 150));
    }
    
    private void drawRecords(Graphics g){
        // Background
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 800, 600);
        
        // Title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Records", 300, 80);
        
        // Records panel
        g.setColor(new Color(255, 255, 255, 200));
        g.fillRoundRect(150, 120, 500, 350, 20, 20);
        
        // Sample records
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Best Scores:", 200, 160);
        
        g.setFont(new Font("Courier", Font.PLAIN, 18));
        String[] records = {
            "Level 1:    Perfect Landing x5",
            "Level 2:    Perfect Landing x3",
            "Level 3:    Miss by 15 pixels",
            "Level 4:    Miss by 28 pixels",
            "Level 5:    Not Attempted",
            "",
            "Endless Mode:",
            "  Best Streak: 0 landings",
            "  Total Jumps: 0"
        };
        
        int yPos = 200;
        for(String record : records){
            g.drawString(record, 200, yPos);
            yPos += 30;
        }
        
        // Back button
        drawButton(g, 300, 500, 200, 50, "Back to Menu", new Color(150, 150, 150));
    }
    
    private void drawGameplay(Graphics g){
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
            g.drawString("Press ESC for main menu", 270, 380);
        }
    }
    
    private void drawButton(Graphics g, int x, int y, int width, int height, String text, Color color){
        // Button background
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 15, 15);
        
        // Button border
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width, height, 15, 15);
        
        // Button text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        
        // Handle multi-line text
        String[] lines = text.split("\n");
        int textY = y + height/2 - (lines.length - 1) * 10;
        for(String line : lines){
            int textWidth = g.getFontMetrics().stringWidth(line);
            int textX = x + (width - textWidth) / 2;
            g.drawString(line, textX, textY);
            textY += 25;
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
        if(currentState == GameState.PLAYING && !gameOver && !landed){
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
    
    public void backToMenu(){
        currentState = GameState.MAIN_MENU;
    }
    
    public void handleClick(int mouseX, int mouseY){
        switch(currentState){
            case MAIN_MENU:
                // Level Select button
                if(isInButton(mouseX, mouseY, 300, 280, 200, 60)){
                    currentState = GameState.LEVEL_SELECT;
                }
                // Records button
                else if(isInButton(mouseX, mouseY, 300, 360, 200, 60)){
                    currentState = GameState.RECORDS;
                }
                break;
                
            case LEVEL_SELECT:
                // Level 1
                if(isInButton(mouseX, mouseY, 150, 150, 180, 80)){
                    startNewJump();
                }
                // Level 2
                else if(isInButton(mouseX, mouseY, 150, 260, 180, 80)){
                    startNewJump();
                }
                // Level 3
                else if(isInButton(mouseX, mouseY, 150, 370, 180, 80)){
                    startNewJump();
                }
                // Level 4
                else if(isInButton(mouseX, mouseY, 450, 150, 180, 80)){
                    startNewJump();
                }
                // Level 5
                else if(isInButton(mouseX, mouseY, 450, 260, 180, 80)){
                    startNewJump();
                }
                // Endless Mode
                else if(isInButton(mouseX, mouseY, 450, 370, 180, 80)){
                    startNewJump();
                }
                // Back button
                else if(isInButton(mouseX, mouseY, 300, 500, 200, 50)){
                    currentState = GameState.MAIN_MENU;
                }
                break;
                
            case RECORDS:
                // Back button
                if(isInButton(mouseX, mouseY, 300, 500, 200, 50)){
                    currentState = GameState.MAIN_MENU;
                }
                break;
        }
    }
    
    private boolean isInButton(int mouseX, int mouseY, int btnX, int btnY, int btnWidth, int btnHeight){
        return mouseX >= btnX && mouseX <= btnX + btnWidth && 
               mouseY >= btnY && mouseY <= btnY + btnHeight;
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    public GameState getCurrentState(){
        return currentState;
    }
}