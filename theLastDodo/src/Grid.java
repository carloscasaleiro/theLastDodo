import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

/**
 * Auxiliary grid class
 */
public class Grid {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 10;
    private final int cols;
    private final int rows;

    //Simple graphics grid constructor with a certain number of rows and columns
    public Grid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }

    public ArrayList<Rectangle> startBlack= new ArrayList<>();
    //Initializes the field simple graphics rectangle and draws it
    public void init() {
        startBlack.add(new Rectangle(PADDING,PADDING, cols * CELLSIZE, rows * CELLSIZE));
        startBlack.get(0).setColor(Color.BLACK);
        startBlack.get(0).draw();
        startBlack.get(0).fill();
    }

    //Auxiliary method to compute the x value that corresponds to a specific column
    public static int columnToX(int column) {
        return PADDING + (CELLSIZE * column);
    }

    //Auxiliary method to compute the y value that corresponds to a specific row
    public static int rowToY(int row) {
        return PADDING + (CELLSIZE * row);
    }
}
