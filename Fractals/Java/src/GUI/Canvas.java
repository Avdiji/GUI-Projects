package GUI;

import Logic.EFractals;
import Logic.FractalManager;
import Logic.IFractal;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

// JComponent, on which the fractals are being drawn
public class Canvas extends JComponent {

    private final List<IFractal> fractalIterations;
    private final FractalManager fractalManager;

    public Canvas() {
        fractalIterations = new ArrayList<>();
        fractalManager = new FractalManager();
    }

    /**
     * Method repaints the Fractal, based on the Fractaltype
     *
     * @param fractalType Fractaltype
     * @param iteration   iteration steps
     */
    public void paintCurrentFractal(EFractals fractalType, int iteration) {
        IFractal currentFractal = fractalManager.getFractal(fractalType);
        fractalIterations.clear();
        currentFractal.clear();
        currentFractal.generateNextIteration(iteration);
        fractalIterations.addAll(currentFractal.getAllIterations());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        fractalIterations.forEach(currentFractal -> { // for all iterations
            currentFractal.getInitialLines().forEach(line -> { // for all lines in the iterations
                ((Graphics2D) g).draw(new Line2D.Double(line.getFrom_x(), line.getFrom_y(), line.getTo_x(), line.getTo_y()));
            });
        });
    }

}
