package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;

/**
 * CantorFractal represents a Cantor fractal that consists of a line divided into three parts.
 */
public class CantorFractal extends AFractal {

    private static final int MARGIN = 40;

    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0) {

            final Line initialLine = getInitialLines().get(0);

            final double thirdLength = FractalUtils.getDistance(initialLine) / 3;
            final double newY = initialLine.getFrom_y() + MARGIN;

            IFractal subFractalLeft = new CantorFractal();
            IFractal subFractalRight = new CantorFractal();

            subFractalLeft.setup(new Line(initialLine.getFrom_x(), newY, initialLine.getFrom_x() + thirdLength, newY));
            subFractalRight.setup(new Line(initialLine.getTo_x() - thirdLength, newY, initialLine.getTo_x(), newY));

            getSubFractals().add(subFractalLeft);
            getSubFractals().add(subFractalRight);

            subFractalLeft.generateNextIteration(iteration - 1);
            subFractalRight.generateNextIteration(iteration - 1);
        }
    }
}


