package Logic.ConcreteFractals;

import Logic.AFractal;
import Logic.FractalUtils;
import Logic.IFractal;
import Logic.Line;
import Logic.LineBuilder;

public class Squares extends AFractal {
    @Override
    public void generateNextIteration(int iteration) {
        if (iteration > 0 && iteration < 8) {

            LineBuilder builder = new LineBuilder();

            for (Line line : getInitialLines()) {

                IFractal fractal = new Squares();
                double distance = FractalUtils.getDistance(line) / 3;

                fractal.setup(
                        builder.setLine(line).adjustLineFromStart(distance).build(),
                        builder.createRotatedLineFromEnd(distance, 90).build(),
                        builder.createRotatedLineFromEnd(distance, -90).build(),
                        builder.createRotatedLineFromEnd(distance, -90).build(),
                        builder.createRotatedLineFromEnd(distance, 90).build());

                getSubFractals().add(fractal);
                fractal.generateNextIteration(iteration - 1);
            }
        }
    }
}
