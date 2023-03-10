package Logic;

import Logic.ConcreteFractals.CantorFractal;
import Logic.ConcreteFractals.SierpinskiTriangle;
import Logic.ConcreteFractals.Tree1;
import Logic.ConcreteFractals.Tree2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AFractal implements IFractal {

    // initial Lines of the Fractal (iteration 1)
    private final List<Line> initialLines;
    // List of the Fractals of the previous iteration
    private final List<IFractal> subFractals;

    public AFractal() {
        initialLines = new ArrayList<>();
        subFractals = new ArrayList<>();
    }

    @Override
    public List<IFractal> getAllIterations() {
        List<IFractal> result = new ArrayList<>();
        result.add(this);

        // delete previous (if needed)
        if (subFractals.size() > 0) {
            if (shouldDeletePrevious()) {
                result.clear();
            }
            subFractals.forEach(previousFractal -> result.addAll(previousFractal.getAllIterations()));
        }
        return result;
    }

    @Override
    public void setup(Line... initialLines) {
        this.initialLines.clear();
        this.initialLines.addAll(Arrays.stream(initialLines).toList());
    }

    @Override
    public void clear() {
        subFractals.clear();
    }

    @Override
    public List<Line> getInitialLines() {
        return initialLines;
    }

    @Override
    public List<IFractal> getSubFractals() {
        return subFractals;
    }

    private boolean shouldDeletePrevious() {
        return !(this instanceof CantorFractal ||
                this instanceof Tree1 ||
                this instanceof Tree2 ||
                this instanceof SierpinskiTriangle);
    }
}
