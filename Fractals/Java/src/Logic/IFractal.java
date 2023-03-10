package Logic;

import java.util.List;

public interface IFractal {

    /**
     * @return a List of all sub-Fractals of all the previous iterations
     **/
    List<IFractal> getAllIterations();

    /**
     * Method calculates the next iteration of the Fractal
     *
     * @param iteration iteration step
     */
    void generateNextIteration(int iteration);

    /**
     * Method sets the lines of the initial Fractal
     *
     * @param initialLines all lines of the initial Fractal
     */
    void setup(Line... initialLines);

    /**
     * @return List of all the lines of the initial Fractal (iteration step 1)
     */
    List<Line> getInitialLines();

    /**
     * @return List of all the Fractals from the previous iteration
     */
    List<IFractal> getSubFractals();

    /**
     * Method removes all Fractals
     */
    void clear();
}
