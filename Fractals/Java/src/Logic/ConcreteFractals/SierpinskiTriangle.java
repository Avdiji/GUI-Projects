package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;
import Logic.LineBuilder;

public class SierpinskiTriangle extends AFractal {

    private static final double ANGLE = -120;

    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0 && iteration < 10) {

            final LineBuilder builder = new LineBuilder();
            final Line initialLeftLine = getInitialLines().get(0);
            final double distance = FractalUtils.getDistance(initialLeftLine) / 2;

            IFractal leftSubTriangle = new SierpinskiTriangle();
            IFractal rightSubTriangle = new SierpinskiTriangle();
            IFractal topSubTriangle = new SierpinskiTriangle();

            // building the triangle might get a bit confusing because im using the builder in a weird way
            // build the left triangle
            leftSubTriangle.setup(
                    builder.setLine(initialLeftLine).adjustLineFromStart(distance).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build());

            // build the right triangle
            rightSubTriangle.setup(
                    builder.createRotatedLineFromStart(distance, ANGLE).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build());

            // build top triangle
            topSubTriangle.setup(
                    builder.setLine(initialLeftLine).adjustLineFromEnd(distance).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build(),
                    builder.createRotatedLineFromEnd(distance, ANGLE).build());

            getSubFractals().add(leftSubTriangle);
            getSubFractals().add(rightSubTriangle);
            getSubFractals().add(topSubTriangle);

            leftSubTriangle.generateNextIteration(iteration - 1);
            rightSubTriangle.generateNextIteration(iteration - 1);
            topSubTriangle.generateNextIteration(iteration - 1);
        }
    }
}
