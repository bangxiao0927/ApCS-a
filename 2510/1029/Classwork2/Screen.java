public class Screen {
public void paintComponent(Graphics g){
    super.paintComponent(g);
   
//draw a group of grass 2 x 3 (2 rows and 3 columns = 6 blades of grass)
//10 is the spacing between each blade
    int grassX = 100;
    int grassY = 400;
    for(int row=0; row<2; row++){
        for(int col=0; col<3; col++){
            drawGrass(g,grassX, grassY);
            grassX += 10;  
        }
        grassX = 100; //Reset x position when we move down a row
        grassY += 10; //Increase y position when we move down a row
    }

    //or
    int startX = 100;
    int startY = 400;
    for(int row=0; row<2; row++){
        for(int col=0; col<3; col++){
            int grassX = col*10 + startX; //col is the x
            int grassY = row*10 + startY; //row is the y
            drawGrass(g,grassX, grassY);
        }
    }
}

public void drawGrass(Graphics g, int x, int y){
    //code to draw ONE grass based upon x and y
}

}
