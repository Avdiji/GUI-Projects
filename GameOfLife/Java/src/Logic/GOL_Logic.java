package main.java.Logic;

import main.java.GUI.Cell;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class is used to set the logic of the game of life
 **/
public class GOL_Logic {

    public static final int WIDTH_CELLS = 50;
    public static final int LENGTH_CELLS = 50;

    private List<Cell> cells;
    private EColorScheme currentColor;

    public GOL_Logic() {
        currentColor = EColorScheme.LIGHT;
        initCells();
    }


    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Method initializes all the cells
     **/
    private void initCells() {
        cells = new ArrayList<>();
        IntStream.range(0, WIDTH_CELLS * LENGTH_CELLS).forEach(i -> cells.add(new Cell()));
    }

    /**
     * Method return the cell at the index [i,j]
     *
     * @param i index (row)
     * @param j index (column)
     * @return cell at index [i,j]
     */
    private Cell getCellAt(final int i, final int j) {
        return cells.get((i * LENGTH_CELLS) + j);
    }


    /**
     * Method returns amount of neighbours of the cell
     *
     * @param i index (row)
     * @param j index (column)
     * @return amount of neighbours cell currently has
     */
    private int getAmountNeighbours(final int i, final int j) {
        int result = 0;

        int[] y = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] x = {-1, -1, 0, 1, 1, 1, 0, -1};

        for (int index = 0; index < x.length; ++index) {
            try {
                result += getCellAt(i + x[index], j + y[index]).isAlive() ? 1 : 0;
            } catch (IndexOutOfBoundsException ignored) {
            }
        }

        return result;
    }

    /**
     * Method generates the next iteration in the game of life
     **/
    public void generateNextIteration() {
        int neighbourCounter;
        List<Cell> kill = new ArrayList<>(); // list of cells that need to die
        List<Cell> live = new ArrayList<>(); // list of cells that are allowed to live

        // for each cell
        for (int i = 0; i < LENGTH_CELLS; ++i) {
            for (int j = 0; j < WIDTH_CELLS; ++j) {
                // get cell and alive neighbours of cell
                Cell currentCell = getCellAt(i, j);
                neighbourCounter = getAmountNeighbours(i, j);

                if (currentCell.isAlive() && neighbourCounter < 2 || neighbourCounter > 3) {
                    kill.add(getCellAt(i, j));
                } else if (!currentCell.isAlive() && neighbourCounter == 3) {
                    live.add(getCellAt(i, j));
                }

            }
        }
        kill.forEach(cell -> cell.setAlive(false));
        live.forEach(cell -> cell.setAlive(true));
    }

    // Method kills all cells
    public void resetCells() {
        cells.forEach(cell -> cell.setAlive(false));
    }

    // Method renders all cells
    public void renderCells(EColorScheme colorScheme) {
        // not the cleanest way but whatever...
        Color alive = null;
        Color dead = null;
        currentColor = colorScheme;

        switch (currentColor) {
            case BLUE -> {
                alive = Cell.COLOR_BLUE_ALIVE;
                dead = Cell.COLOR_BLUE_DEAD;
            }
            case RED -> {
                alive = Cell.COLOR_RED_ALIVE;
                dead = Cell.COLOR_RED_DEAD;
            }
            case DARK -> {
                alive = Cell.COLOR_DARK_ALIVE;
                dead = Cell.COLOR_DARK_DEAD;
            }
            case LIGHT -> {
                alive = Cell.COLOR_LIGHT_ALIVE;
                dead = Cell.COLOR_LIGHT_DEAD;
            }
        }

        for (Cell cell : cells) {
            cell.setCurrent_color_alive(alive);
            cell.setCurrent_color_dead(dead);
        }
        cells.forEach(Cell::renderCell);

    }

    public EColorScheme getCurrentColor() {
        return currentColor;
    }

}
