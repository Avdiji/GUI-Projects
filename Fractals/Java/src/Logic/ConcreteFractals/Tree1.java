package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;
import Logic.LineBuilder;

public class Tree1 extends AFractal {

    private static final double ROTATION_ANGLE = 40;
    private static final double LENGTH_FACTOR = 1.3;

    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0) {

            final LineBuilder builder = new LineBuilder();
            final Line base = getInitialLines().get(0);
            final double distance = FractalUtils.getDistance(base);

            IFractal leftSubFractal = new Tree1();
            IFractal rightSubFractal = new Tree1();

            leftSubFractal.setup(builder.setLine(base).createRotatedLineFromEnd(distance / LENGTH_FACTOR, ROTATION_ANGLE).build());
            rightSubFractal.setup(builder.setLine(base).createRotatedLineFromEnd(distance / LENGTH_FACTOR, -ROTATION_ANGLE).build());

            getSubFractals().add(leftSubFractal);
            getSubFractals().add(rightSubFractal);

            leftSubFractal.generateNextIteration(iteration - 1);
            rightSubFractal.generateNextIteration(iteration - 1);
        }
    }
}
