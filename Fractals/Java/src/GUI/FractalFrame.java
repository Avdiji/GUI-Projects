package GUI;

import Logic.EFractals;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.stream.IntStream;

public class FractalFrame extends JFrame {

    // Constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    // Button - Panels
    private JPanel buttons;

    // Buttons
    private JButton cantor;
    private JButton sierpinski_triangle;
    private JButton koch_curve;
    private JButton tree1;
    private JButton tree2;
    private JButton squares;

    // Other...
    private JSlider iterationsSlider;
    private Canvas canvas;
    EFractals currentFractalType;

    public FractalFrame() {
        initFrame();
        initComponents();
        addComponents();
        this.setVisible(true);
    }

    /**
     * Method initializes this frame
     */
    private void initFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Method initializes all components
     */
    private void initComponents() {
        initIterationSlider();

        // set Listeners
        iterationsSlider.addChangeListener(e -> canvas.paintCurrentFractal(currentFractalType == null ? EFractals.CANTOR : currentFractalType, iterationsSlider.getValue()));

        canvas = new Canvas();
        buttons = new JPanel();

        cantor = new JButton("Cantor");
        sierpinski_triangle = new JButton("Sierpinski Triangle");
        koch_curve = new JButton("Koch Curve");
        tree1 = new JButton("Tree1");
        tree2 = new JButton("Tree2");
        squares = new JButton("Squares");
    }

    /**
     * Method puts all components together
     */
    private void addComponents() {
        // add ActionListeners
        cantor.addActionListener(e -> onButtonClick(EFractals.CANTOR));
        sierpinski_triangle.addActionListener(e -> onButtonClick(EFractals.SIERPINSKI_TRIANGLE));
        koch_curve.addActionListener(e -> onButtonClick(EFractals.KOCH_CURVE));
        tree1.addActionListener(e -> onButtonClick(EFractals.TREE1));
        tree2.addActionListener(e -> onButtonClick(EFractals.TREE2));
        squares.addActionListener(e -> onButtonClick(EFractals.SQUARES));

        // add to panels
        buttons.add(cantor);
        buttons.add(sierpinski_triangle);
        buttons.add(koch_curve);
        buttons.add(tree1);
        buttons.add(tree2);
        buttons.add(squares);

        // add to this
        this.getContentPane().add(canvas, BorderLayout.CENTER);
        this.getContentPane().add(buttons, BorderLayout.SOUTH);
        this.getContentPane().add(iterationsSlider, BorderLayout.WEST);
    }

    /**
     * Method initialized the iterationsSlider
     */
    private void initIterationSlider() {
        iterationsSlider = new JSlider(JSlider.VERTICAL, 0, 12, 0);
        Hashtable<Integer, JLabel> sliderTable = new Hashtable<>();
        IntStream.range(0, 13).forEach(i -> sliderTable.put(i, new JLabel(i + "")));
        iterationsSlider.setLabelTable(sliderTable);
        iterationsSlider.setPaintTicks(true);
        iterationsSlider.setMinorTickSpacing(1);
        iterationsSlider.setPaintLabels(true);
    }

    /**
     * Method sets the currentFractalType and initializes the corresponding actionListener
     *
     * @param fractalType Fractaltype
     */
    private void onButtonClick(EFractals fractalType) {
        currentFractalType = fractalType;
        canvas.paintCurrentFractal(currentFractalType, iterationsSlider.getValue());
    }
}
