package Logic;

// Utils class
public class FractalUtils {

    // private Constructor, so that no instance of FractalUtils can be created
    private FractalUtils() {
    }

    /**
     * @param line Line
     * @return distance of the two Points of line
     */
    public static double getDistance(Line line) {
        double dx = line.getTo_x() - line.getFrom_x();
        double dy = line.getTo_y() - line.getFrom_y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Method calcualtes the angle of a Line
     *
     * @param line Line to calculate the angle with
     * @return Angle of the line
     */
    public static double getAngle(Line line) { return Math.atan2(line.getTo_y() - line.getFrom_y(), line.getTo_x() - line.getFrom_x()); }
}
