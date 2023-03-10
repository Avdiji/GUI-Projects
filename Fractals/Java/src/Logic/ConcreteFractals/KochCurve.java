package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;
import Logic.LineBuilder;

public class KochCurve extends AFractal {

    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0 && iteration < 9) {

            LineBuilder builder = new LineBuilder();

            for (Line line : getInitialLines()) {

                double segmentLength = FractalUtils.getDistance(line) / 3.0;
                IFractal fractal = new KochCurve();

                fractal.setup(builder.setLine(line).adjustLineFromStart(segmentLength).build(),
                        builder.createRotatedLineFromEnd(segmentLength, 60).build(),
                        builder.createRotatedLineFromEnd(segmentLength, -120).build(),
                        builder.createRotatedLineFromEnd(segmentLength, 60).build()
                );

                fractal.generateNextIteration(iteration - 1);
                getSubFractals().add(fractal);
            }
        }
    }
}
