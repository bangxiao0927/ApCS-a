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
    private int currentLevel;
    private boolean autoRevive;
    private long reviveTimer;
    private static final long REVIVE_DELAY = 2000; // 2 seconds delay before auto revive
    
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
        currentLevel = 1;
        autoRevive = false;
        reviveTimer = 0;
    }
    
    public void startNewJump(){
        startNewJump(currentLevel);
    }
    
    public void startNewJump(int level){
        currentLevel = level;
        
        // Enable auto revive for endless mode (level 6+)
        autoRevive = (level >= 6);
        reviveTimer = 0;
        
        // Random spawn position for level 4+
        int spawnX;
        if(level >= 4){
            // Random X position for player spawn, keeping them on screen
            spawnX = 100 + (int)(Math.random() * 401);
        } else {
            // Centered spawn for easier levels
            spawnX = 400;
        }
        
        skydiver = new Player(spawnX, SKY_START);
        
        // Target size decreases with level
        int targetSize = getTargetSizeForLevel(level);
        
        // Random position for level 4+
        int targetX;
        if(level >= 4){
            // Random X position, keeping target on screen
            targetX = 100 + (int)(Math.random() * (600 - targetSize));
        } else {
            // Centered position for easier levels
            targetX = 400 - targetSize/2;
        }
        
        landingZone = new Target(targetX, GROUND_LEVEL - targetSize, targetSize, targetSize);
        
        // Wind speed varies by level difficulty
        windSpeed = generateWindForLevel(level);
        
        gameOver = false;
        landed = false;
        resultMessage = "";
        currentState = GameState.PLAYING;
    }
    
    private int getTargetSizeForLevel(int level){
        switch(level){
            case 1: return 120; // Very large
            case 2: return 100; // Large
            case 3: return 80;  // Medium
            case 4: return 60;  // Small
            case 5: return 45;  // Very small
            default: return 35; // Tiny (Endless)
        }
    }
    
    private double generateWindForLevel(int level){
        double windRangeLeft, windRangeRight;
        switch(level){
            case 1: // Beginner: -2 to 2
                windRangeLeft = 0.0;
                windRangeRight = 2.0;
                break;
            case 2: // Intermediate: -3.5 to 3.5
                windRangeLeft = 1.0;
                windRangeRight = 3.5;
                break;
            case 3: // Advanced: -5 to 5
                windRangeLeft = 2.5;
                windRangeRight = 5.0;
                break;
            case 4: // Expert: -6.5 to 6.5
                windRangeLeft = 3.5;
                windRangeRight = 6.5;
                break;
            case 5: // Master: -8 to 8
                windRangeLeft = 5.0;
                windRangeRight = 8.0;
                break;
            default: // Endless: -10 to 10
                windRangeLeft = 6.0;
                windRangeRight = 10.0;
                break;
        }
        return Math.random() * (windRangeRight + windRangeLeft) - windRangeLeft;
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
        
        // Handle auto revive in endless mode
        if(currentState == GameState.PLAYING && gameOver && autoRevive){
            if(reviveTimer == 0){
                reviveTimer = System.currentTimeMillis();
            } else if(System.currentTimeMillis() - reviveTimer >= REVIVE_DELAY){
                // Auto restart after delay
                startNewJump(currentLevel);
            }
        }
    }
    
    private void checkLanding(){
        // Check landing speed - must be slow enough to survive
        double landingSpeed = skydiver.getVelocityY();
        boolean speedSafe = landingSpeed <= 2.5; // Safe landing speed threshold
        
        // Check if skydiver landed in the target zone
        int skydiverCenterX = skydiver.getX() + skydiver.getWidth()/2;
        int targetLeft = landingZone.getX();
        int targetRight = landingZone.getX() + landingZone.getWidth();
        
        boolean inTargetZone = skydiverCenterX >= targetLeft && skydiverCenterX <= targetRight;
        
        if(!speedSafe){
            // Crashed due to high speed
            if(autoRevive){
                resultMessage = "CRASH! Auto reviving in 2 seconds... Speed: " + String.format("%.1f", landingSpeed);
            } else {
                resultMessage = "CRASH! Speed too high: " + String.format("%.1f", landingSpeed);
            }
            skydiver.crash();
        } else if(inTargetZone){
            // Perfect landing - slow speed and in target
            if(autoRevive){
                resultMessage = "Perfect Landing! New jump starting... Speed: " + String.format("%.1f", landingSpeed);
            } else {
                resultMessage = "Perfect Landing! Speed: " + String.format("%.1f", landingSpeed);
            }
        } else {
            // Missed target but survived
            int distance = Math.min(
                Math.abs(skydiverCenterX - targetLeft),
                Math.abs(skydiverCenterX - targetRight)
            );
            if(autoRevive){
                resultMessage = "Missed by " + distance + " pixels. Auto reviving... Speed: " + String.format("%.1f", landingSpeed);
            } else {
                resultMessage = "Missed by " + distance + " pixels. Speed: " + String.format("%.1f", landingSpeed);
            }
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
        
        // Draw skydiver with parachute in left corner
        drawMenuSkydiver(g, 50, 30);
        
        // Title
        g.setColor(new Color(255, 140, 0));
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString("SKY DIVER", 180, 150);
        
        // Subtitle - RED
        g.setColor(new Color(220, 20, 20)); // Bright red
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
    
    private void drawMenuSkydiver(Graphics g, int x, int y){
        // Draw a decorative skydiver with deployed parachute
        Color skydiverColor = new Color(255, 140, 0);
        Color parachuteColor = new Color(255, 50, 50);
        
        // Parachute canopy
        g.setColor(parachuteColor);
        g.fillArc(x - 15, y - 35, 60, 40, 0, 180);
        
        // Parachute lines
        g.setColor(Color.WHITE);
        g.drawLine(x, y - 10, x + 10, y + 5);
        g.drawLine(x + 15, y - 15, x + 12, y + 5);
        g.drawLine(x + 30, y - 15, x + 18, y + 5);
        g.drawLine(x + 45, y - 10, x + 20, y + 5);
        
        // Skydiver body
        g.setColor(skydiverColor);
        g.fillRect(x + 10, y + 5, 8, 15); // Body
        g.fillOval(x + 8, y - 5, 12, 12); // Head
        
        // Arms down (holding lines)
        g.fillRect(x + 6, y + 8, 3, 8);
        g.fillRect(x + 19, y + 8, 3, 8);
        
        // Legs
        g.fillRect(x + 9, y + 20, 3, 8);
        g.fillRect(x + 16, y + 20, 3, 8);
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
            // Check if it was a crash
            boolean crashed = skydiver.isCrashed();
            
            if(crashed){
                g.setColor(new Color(255, 0, 0));
            } else {
                g.setColor(Color.BLACK);
            }
            
            g.setFont(new Font("Arial", Font.BOLD, 36));
            
            // Calculate text position based on length
            int textWidth = g.getFontMetrics().stringWidth(resultMessage);
            int textX = (800 - textWidth) / 2;
            g.drawString(resultMessage, textX, 300);
            
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
        g.drawString("Wind: ", 0, 30);
        
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
        
        g.drawString(String.format("%.1f", windSpeed), arrowX + 70, 30);
    }
    
    private void drawHUD(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Display altitude
        int altitude = GROUND_LEVEL - skydiver.getY() - skydiver.getHeight();
        g.drawString("Altitude: " + altitude, 10, 60);
        
        // Display velocity
        g.drawString(String.format("Speed: %.1f", skydiver.getVelocityY()), 10, 85);
        
        // Display parachute status
        if(skydiver.isParachuteOpen()){
            g.setColor(new Color(100, 255, 100));
            g.drawString("PARACHUTE: DEPLOYING...", 10, 110);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString("Press SPACE to open parachute", 10, 110);
        }
        
        // Display safe landing speed warning
        if(skydiver.getVelocityY() > 3.0){
            g.setColor(new Color(255, 50, 50));
            g.drawString("WARNING: SPEED TOO HIGH!", 10, 135);
        } else {
            g.setColor(new Color(100, 255, 100));
            g.drawString("Landing speed: SAFE", 10, 135);
        }
        
        // Display controls
        g.setColor(Color.WHITE);
        g.drawString("Controls: LEFT/RIGHT arrows, SPACE", 560, 30);
        
        // Display level
        String levelName = getLevelName(currentLevel);
        g.drawString("Level: " + levelName, 10, 160);
        
        // Display auto revive status for endless mode
        if(autoRevive){
            g.setColor(new Color(100, 255, 100));
            g.drawString("AUTO REVIVE: ENABLED", 10, 185);
        }
    }
    
    private String getLevelName(int level){
        switch(level){
            case 1: return "Beginner";
            case 2: return "Intermediate";
            case 3: return "Advanced";
            case 4: return "Expert";
            case 5: return "Master";
            default: return "Endless";
        }
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
    
    public void openParachute(){
        if(currentState == GameState.PLAYING && !gameOver && !landed){
            skydiver.openParachute();
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
                    startNewJump(1);
                }
                // Level 2
                else if(isInButton(mouseX, mouseY, 150, 260, 180, 80)){
                    startNewJump(2);
                }
                // Level 3
                else if(isInButton(mouseX, mouseY, 150, 370, 180, 80)){
                    startNewJump(3);
                }
                // Level 4
                else if(isInButton(mouseX, mouseY, 450, 150, 180, 80)){
                    startNewJump(4);
                }
                // Level 5
                else if(isInButton(mouseX, mouseY, 450, 260, 180, 80)){
                    startNewJump(5);
                }
                // Endless Mode
                else if(isInButton(mouseX, mouseY, 450, 370, 180, 80)){
                    startNewJump(6);
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