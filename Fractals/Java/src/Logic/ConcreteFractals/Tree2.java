package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;
import Logic.LineBuilder;

public class Tree2 extends AFractal {

    private static final double ROTATION_ANGLE = 15;
    private static final double LENGTH_FACTOR = 1.3;

    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0) {

            LineBuilder builder = new LineBuilder();

            Line base = getInitialLines().get(0);
            double distance = FractalUtils.getDistance(base);

            IFractal newLeft = new Tree2();
            IFractal newRight = new Tree2();

            newLeft.setup(builder.setLine(base).createRotatedLineFromEnd(distance / LENGTH_FACTOR, ROTATION_ANGLE).build());
            newRight.setup(builder.setLine(base).createRotatedLineFromEnd(distance / LENGTH_FACTOR, -ROTATION_ANGLE).build());

            getSubFractals().add(newLeft);
            getSubFractals().add(newRight);

            newLeft.generateNextIteration(iteration - 1);
            newRight.generateNextIteration(iteration - 1);
        }
    }
}
