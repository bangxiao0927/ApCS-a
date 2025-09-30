import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Scenery extends JPanel {
    // instance variables
    private final String dayOrNight;
    private final String weather;
    private final int width;
    private final int height;
    private final int minScale;
    private final int groundHeight;
    //final is just to make sure the variable won't be changed after initialized

    // constructor
    public Scenery(String dayOrNight, String weather, int width, int height) {
        this.dayOrNight = dayOrNight;
        this.weather = weather;
        if(width <= 1920) {this.width = width;} else {this.width = 1920;} // max width 1920
        if(height <= 1080) {this.height = height;} else {this.height = 1080;} // max height 1080
        this.minScale = Math.min(this.width, this.height);
        this.groundHeight = this.height / 5 * 4;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    // Paint component methods
    private void drawBackGround(Graphics g, String dayOrNight, int width, int height) {
        if (dayOrNight.equals("day")) {
            Color skyColor = new Color(153, 255, 255); // Sky blue
            g.setColor(skyColor);
        }
        else if (dayOrNight.equals("night") && !weather.equals("sunny")) {
            Color nightColor = new Color(1, 34, 125);
            g.setColor(nightColor);
        }
        else {
            System.out.println("Invalid background selection.");
        }
        g.fillRect(0, 0, width, height);
    }

    private void drawWeather(Graphics g, String weather) {
        if (weather.equals("sunny") && dayOrNight.equals("day")) {
            Color lightYellow = new Color(255, 255, 153);
            g.setColor(lightYellow);
            g.fillOval(0, 0, this.minScale / 4, this.minScale / 4);
        }
        else if (weather.equals("cloudy")) {
            drawClouds(g);
        }
        else if (weather.equals("rainy")) {
            drawRain(g);
            drawClouds(g);
        }
        else {
            System.out.println("Invalid weather selection. No weather will be drawn.");
        }
    }

    private void drawClouds(Graphics g) {
        Color gray = new Color(169, 169, 169);
        g.setColor(gray);
        int randomX;
        int randomY;
        int randomAmount = (int)(Math.random() * (9 - 3 + 1)) + 3;
        for(int i = 0;i < randomAmount;i++) {
            randomX = (int)(Math.random() * (this.width - this.minScale / 4));
            randomY = (int)(Math.random() * (this.height / 2 - this.minScale / 8));
            g.fillOval(randomX, randomY, this.minScale / 5, this.minScale / 10);
        }
    }

    private void drawRain(Graphics g) {
        Color rainBlue = new Color(26, 145, 205);
        g.setColor(rainBlue);
        int randomX;
        int randomY;
        int randomAmount = (int)(Math.random() * (100 - 50 + 1)) + 50;
        for(int i = 0;i < randomAmount;i++) {
            randomX = (int)(Math.random() * this.width);
            randomY = (int)(Math.random() * this.height);
            g.drawLine(randomX, randomY, randomX - (this.minScale / 11), randomY + (this.minScale / 12) * 3);
        }
    }

    private void drawGround(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, this.groundHeight, this.width, this.height);
    }

    private void drawBuildings(Graphics g) {
        // Building color
        Color buildingColor = new Color(96, 96, 96);

        int maxBuildingWidth = this.width / 12;
        int minBuildingWidth = this.width / 20;
        int maxBuildingHeight = this.height / 3;
        int minBuildingHeight = this.height / 10;

        int buildingPosition = 0;

        while(buildingPosition < this.width) {
            // random building height
            int buildingWidth = (int)(Math.random() * (maxBuildingWidth - minBuildingWidth)) + minBuildingWidth;
            int buildingHeight = (int)(Math.random() * (maxBuildingHeight - minBuildingHeight)) + minBuildingHeight;

            if (buildingPosition + buildingWidth > this.width) break;

            // building body
            g.setColor(buildingColor);
            g.fillRect(buildingPosition, this.groundHeight - buildingHeight, buildingWidth, buildingHeight);

            // frame
            g.setColor(Color.BLACK);
            g.drawRect(buildingPosition, this.groundHeight - buildingHeight, buildingWidth, buildingHeight);

            // windows
            int windowWidth = buildingWidth / 6;
            int windowHeight = buildingHeight / 15;
            int windowGapX = windowWidth / 2;
            int windowGapY = windowHeight;

            int startX = buildingPosition + windowGapX;
            int startY = this.groundHeight - buildingHeight + windowGapY;

            if(this.dayOrNight.equals("night")) {
                g.setColor(new Color(255, 255, 180));
            }
            else {
                g.setColor(new Color(224, 224, 224));
            }

            // windows table
            for (int currentX = startX; currentX + windowWidth < buildingPosition + buildingWidth - windowGapX; currentX += windowWidth + windowGapX) {
                for (int currentY = startY; currentY + windowHeight < this.groundHeight - windowGapY; currentY += windowHeight + windowGapY) {
                    g.fillRect(currentX, currentY, windowWidth, windowHeight);
                }
            }

            // go to next building position
            buildingPosition += buildingWidth + 10;
        }
    }

    //main: Shanghai pearl tower

    private void drawPearlTower(Graphics g, int xPosition) {
        Color purplePink = new Color(204, 0, 102);
        //big ball size
        int bigSphereDiameter = this.width / 8;
        int bigSphereX = xPosition - bigSphereDiameter / 2;
        int bigSphereY = this.groundHeight - bigSphereDiameter;

        // small ball size
        int smallSphereDiameter = this.width / 12;
        int smallSphereX = xPosition - smallSphereDiameter / 2;
        int smallSphereY = bigSphereY - smallSphereDiameter - this.height / 15;

        //feet
        g.setColor(Color.DARK_GRAY);
        for(int i = 0;i < 75;i++) {
            g.drawLine(xPosition - bigSphereDiameter / 2 - i, this.groundHeight, xPosition, bigSphereY);
            g.drawLine(xPosition + bigSphereDiameter / 2 + i, this.groundHeight, xPosition, bigSphereY);
        }

        // body
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xPosition - smallSphereDiameter / 8 * 2, smallSphereY + smallSphereDiameter / 8, smallSphereDiameter/10, this.groundHeight - (smallSphereY + smallSphereDiameter / 8) - this.height / 20);
        g.fillRect(xPosition + smallSphereDiameter / 8, smallSphereY + smallSphereDiameter / 8, smallSphereDiameter/10, this.groundHeight - (smallSphereY + smallSphereDiameter / 8) - this.height / 20);

        // horizontal sub body
        g.drawLine(xPosition - smallSphereDiameter / 8 * 2, (smallSphereY + bigSphereY) / 2 + smallSphereDiameter/2, smallSphereX + smallSphereDiameter/8 * 5, (smallSphereY + bigSphereY) / 2 + smallSphereDiameter/2);
        
        //big ball
        g.setColor(purplePink);
        g.fillOval(bigSphereX, bigSphereY, bigSphereDiameter, bigSphereDiameter);
        g.setColor(Color.BLACK);
        g.drawOval(bigSphereX, bigSphereY, bigSphereDiameter, bigSphereDiameter);

        //small ball
        g.setColor(purplePink);
        g.fillOval(smallSphereX, smallSphereY, smallSphereDiameter, smallSphereDiameter);
        g.setColor(Color.BLACK);
        g.drawOval(smallSphereX, smallSphereY, smallSphereDiameter, smallSphereDiameter);

        //ender1
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xPosition - smallSphereDiameter / 15, smallSphereY / 2, smallSphereDiameter / 8, smallSphereY / 2);

        //ender2
        g.setColor(Color.BLACK);
        g.drawLine(xPosition, smallSphereY / 10 * 3, xPosition, smallSphereY / 2);
    }

    //draw river 
    private void drawRiver(Graphics g) {
        Color riverBlue = new Color(0, 102, 204);
        g.setColor(riverBlue);

        int minRiverHeight = this.height / 16;
        int maxRiverHeight = this.height / 8;
        int riverHeight = (int)(Math.random() * (maxRiverHeight - minRiverHeight + 1)) + minRiverHeight;

        // start y position
        int minStartY = this.groundHeight + this.height / 30;
        int maxStartY = this.height - riverHeight;
        int startY = (int)(Math.random() * (maxStartY - minStartY + 1)) + minStartY;

        // draw
        g.fillRect(0, startY, this.width, riverHeight);
    }

    // draw cars
    private void drawCars(Graphics g) {
        int carCount = (int)(Math.random() * (8 - 4 + 1)) + 4;
        int carWidth = this.width / 12;
        int carHeight = this.height / 15;

        for (int i = 0; i < carCount; i++) {
            //random x and y position
            int carX = (int)(Math.random() * (this.width - carWidth));
            int carY = this.groundHeight - carHeight;

            // random color
            Color carColor = new Color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
            g.setColor(carColor);

            // car body
            int bodyHeight = carHeight * 2 / 3;
            g.fillRect(carX, carY + carHeight - bodyHeight, carWidth, bodyHeight);

            int topWidth = carWidth * 2 / 3;
            int topHeight = carHeight / 3;
            int topX = carX + (carWidth - topWidth) / 2;
            int topY = carY;
            g.fillRect(topX, topY, topWidth, topHeight);

            // car wheels
            g.setColor(Color.BLACK);
            int wheelDiameter = carHeight / 3;
            int wheelY = this.groundHeight - wheelDiameter / 2;
            g.fillOval(carX + carWidth / 6, wheelY, wheelDiameter, wheelDiameter);
            g.fillOval(carX + carWidth - carWidth / 6 - wheelDiameter, wheelY, wheelDiameter, wheelDiameter);

            // frame
            g.drawRect(carX, carY + carHeight - bodyHeight, carWidth, bodyHeight);
            g.drawRect(topX, topY, topWidth, topHeight);
        }
    }

    //draw birds
    private void drawBirds(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            int x = (int)(Math.random() * this.width);
            int y = (int)(Math.random() * this.height / 2);
            g.drawArc(x, y, 20, 20, 0, 180);
        }
    }

    //draw lamps
    private void drawLamps(Graphics g,int amount) {
        int lampHeight = this.height / 8;
        int lampY = this.groundHeight - lampHeight;
        int lampLightRadius = this.width / 80;
        int lampLightY = lampY - lampLightRadius / 2;
        int lampCapHeight = lampHeight / 10;  
        int randomXPosition;

        for(int i = 0;i < amount;i++) {
            randomXPosition = (int)(Math.random() * (this.width - this.width / 10)) + this.width / 20;

            g.setColor(Color.DARK_GRAY);
            g.fillRect(randomXPosition, lampY, this.width / 80, lampHeight);

            g.drawLine(randomXPosition + this.width / 80, lampY, randomXPosition + this.width / 80 + this.width / 40, lampY - lampCapHeight);

            g.setColor(Color.YELLOW);
            g.fillOval(randomXPosition + this.width / 80, lampLightY, lampLightRadius, lampLightRadius);
        }
    }

    // Paint the component
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        drawBackGround(g, this.dayOrNight, this.width, this.height);

        // Draw ground
        drawGround(g);

        // Draw river
        drawRiver(g);

        // Draw buildings
        drawBuildings(g);

        // Draw the Shanghai Pearl Tower
        drawPearlTower(g, this.width / 2);

        // Draw lamps
        drawLamps(g, 3);

        // Draw cars
        drawCars(g);

        // Draw birds
        drawBirds(g);

        // Draw weather
        drawWeather(g, this.weather);
    }
}