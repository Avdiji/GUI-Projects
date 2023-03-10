package Logic;

import Logic.ConcreteFractals.CantorFractal;
import Logic.ConcreteFractals.KochCurve;
import Logic.ConcreteFractals.SierpinskiTriangle;
import Logic.ConcreteFractals.Squares;
import Logic.ConcreteFractals.Tree1;
import Logic.ConcreteFractals.Tree2;

import java.util.HashMap;
import java.util.Map;

// Manager for all the Concrete Fractals
public class FractalManager {

    private final Map<EFractals, IFractal> fractals;

    public FractalManager() {
        fractals = new HashMap<>();
        fractals.put(EFractals.CANTOR, new CantorFractal());
        fractals.put(EFractals.SIERPINSKI_TRIANGLE, new SierpinskiTriangle());
        fractals.put(EFractals.KOCH_CURVE, new KochCurve());
        fractals.put(EFractals.TREE1, new Tree1());
        fractals.put(EFractals.TREE2, new Tree2());
        fractals.put(EFractals.SQUARES, new Squares());
    }

    /**
     * Method returns a Fractal based on the given Frataltype
     *
     * @param fractalType Type of the Fractal
     * @return Fractal by Type
     */
    public IFractal getFractal(EFractals fractalType) {
        IFractal fractal = fractals.get(fractalType);
        setupByType(fractalType, fractal);
        return fractal;
    }

    /**
     * Method calculates the inital Lines of the first iteration of the fractal, based on the Fractaltype
     *
     * @param fractalType Type of the Fractal
     * @param fractal     Actual Fractal
     */
    private void setupByType(EFractals fractalType, IFractal fractal) {
        LineBuilder builder = new LineBuilder();

        switch (fractalType) {
            case CANTOR -> fractal.setup(new Line(120, 100, 620, 100));
            case TREE1, TREE2 -> fractal.setup(new Line(350, 550, 350, 450));

            case KOCH_CURVE, SIERPINSKI_TRIANGLE -> {
                Line base = new Line(170, 500, 570, 500);
                double distance = FractalUtils.getDistance(base);
                fractal.setup(
                        builder.setLine(base).createRotatedLineFromStart(distance, 60).build(),
                        builder.createRotatedLineFromEnd(distance, -120).build(),
                        builder.createRotatedLineFromEnd(distance, -120).build());
            }

            case SQUARES -> {
                Line l = new Line(250, 500, 250, 300);
                double distance = FractalUtils.getDistance(l);
                builder.setLine(l);
                fractal.setup(l,
                        builder.createRotatedLineFromEnd(distance, -90).build(),
                        builder.createRotatedLineFromEnd(distance, -90).build(),
                        builder.createRotatedLineFromEnd(distance, -90).build());
            }
        }
    }

}
