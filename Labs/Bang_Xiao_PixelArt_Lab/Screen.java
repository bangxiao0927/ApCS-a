import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JPanel;

public class Screen extends JPanel implements MouseListener, MouseMotionListener {
    private static final int ROWS = 16;
    private static final int COLS = 16;
    private static final int CELL_SIZE = 30;

    private static final int GRID_X = 30;
    private static final int GRID_Y = 120;

    private static final int PALETTE_X = 30;
    private static final int PALETTE_Y = 55;
    private static final int PALETTE_SIZE = 35;
    private static final int PALETTE_GAP = 15;

    private static final int BUTTON_Y = 620;
    private static final int BUTTON_WIDTH = 110;
    private static final int BUTTON_HEIGHT = 35;
    private static final String FILE_NAME = "pixelart.txt";

    private Square[][] grid;
    private Color[] palette;
    private Rectangle[] paletteBoxes;
    private Rectangle clearButton;
    private Rectangle saveButton;
    private Rectangle loadButton;
    private int selectedColorIndex;

    public Screen() {
        setPreferredSize(new Dimension(560, 690));
        setBackground(new Color(245, 245, 245));

        grid = new Square[ROWS][COLS];
        palette = new Color[] {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
        paletteBoxes = new Rectangle[palette.length];

        clearButton = new Rectangle(30, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        saveButton = new Rectangle(160, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadButton = new Rectangle(290, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        selectedColorIndex = 0;

        fillGridWithWhite();
        buildPaletteBoxes();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void fillGridWithWhite() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Square(255, 255, 255);
            }
        }
    }

    private void buildPaletteBoxes() {
        for (int i = 0; i < paletteBoxes.length; i++) {
            int x = PALETTE_X + i * (PALETTE_SIZE + PALETTE_GAP);
            paletteBoxes[i] = new Rectangle(x, PALETTE_Y, PALETTE_SIZE, PALETTE_SIZE);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Pixel Art Studio", 30, 35);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Choose a color, then click or drag on the grid to draw.", 30, 105);

        drawPalette(g);
        drawGrid(g);
        drawButtons(g);
    }

    private void drawPalette(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Palette", PALETTE_X, PALETTE_Y - 10);

        for (int i = 0; i < palette.length; i++) {
            Rectangle box = paletteBoxes[i];
            g.setColor(palette[i]);
            g.fillRect(box.x, box.y, box.width, box.height);
            g.setColor(Color.BLACK);
            g.drawRect(box.x, box.y, box.width, box.height);

            if (i == selectedColorIndex) {
                g.drawRect(box.x - 3, box.y - 3, box.width + 6, box.height + 6);
            }
        }
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int x = GRID_X + col * CELL_SIZE;
                int y = GRID_Y + row * CELL_SIZE;
                grid[row][col].drawMe(g, x, y, CELL_SIZE);
            }
        }
    }

    private void drawButtons(Graphics g) {
        drawButton(g, clearButton, "Clear");
        drawButton(g, saveButton, "Save");
        drawButton(g, loadButton, "Load");
    }

    private void drawButton(Graphics g, Rectangle button, String label) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(button.x, button.y, button.width, button.height);
        g.setColor(Color.BLACK);
        g.drawRect(button.x, button.y, button.width, button.height);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(label, button.x + 32, button.y + 23);
    }

    private void handleMouse(int mouseX, int mouseY) {
        if (chooseColor(mouseX, mouseY)) {
            repaint();
        } else if (clearButton.contains(mouseX, mouseY)) {
            clearGrid();
        } else if (saveButton.contains(mouseX, mouseY)) {
            saveToFile();
        } else if (loadButton.contains(mouseX, mouseY)) {
            loadFromFile();
        } else {
            drawOnGrid(mouseX, mouseY);
        }
    }

    private boolean chooseColor(int mouseX, int mouseY) {
        for (int i = 0; i < paletteBoxes.length; i++) {
            if (paletteBoxes[i].contains(mouseX, mouseY)) {
                selectedColorIndex = i;
                return true;
            }
        }
        return false;
    }

    private void drawOnGrid(int mouseX, int mouseY) {
        int col = (mouseX - GRID_X) / CELL_SIZE;
        int row = (mouseY - GRID_Y) / CELL_SIZE;

        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            Color color = palette[selectedColorIndex];
            grid[row][col].setColor(color.getRed(), color.getGreen(), color.getBlue());
            repaint();
        }
    }

    private void clearGrid() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col].setColor(255, 255, 255);
            }
        }
        repaint();
    }

    private void saveToFile() {
        File file = new File(FILE_NAME);

        try {
            FileWriter output = new FileWriter(file);
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    output.write("" + colorToNumber(grid[row][col].getColor()));
                    if (col < grid[row].length - 1) {
                        output.write(" ");
                    }
                }
                output.write("\n");
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println("Could not save file.");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);

        try {
            Scanner input = new Scanner(new FileReader(file));
            clearGrid();
            int row = 0;
            while (input.hasNextLine() && row < grid.length) {
                String line = input.nextLine().trim();
                if (line.length() > 0) {
                    String[] numbers = line.split(" ");
                    for (int col = 0; col < numbers.length && col < grid[row].length; col++) {
                        int number = Integer.parseInt(numbers[col]);
                        Color color = numberToColor(number);
                        grid[row][col].setColor(color.getRed(), color.getGreen(), color.getBlue());
                    }
                    row++;
                }
            }
            input.close();
            repaint();
        } catch (Exception e) {
            System.out.println("Could not load file.");
        }
    }

    private int colorToNumber(Color color) {
        if (color.equals(Color.WHITE)) {
            return 0;
        }

        for (int i = 0; i < palette.length; i++) {
            if (color.equals(palette[i])) {
                return i + 1;
            }
        }

        return 0;
    }

    private Color numberToColor(int number) {
        if (number == 0) {
            return Color.WHITE;
        }

        if (number >= 1 && number <= palette.length) {
            return palette[number - 1];
        }

        return Color.WHITE;
    }

    public void mousePressed(MouseEvent e) {
        handleMouse(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        drawOnGrid(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
    }
}
