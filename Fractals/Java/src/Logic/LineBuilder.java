package Logic;

// Builder for Line
public class LineBuilder {

    private Line line;

    /**
     * Setter
     *
     * @param line Line
     * @return LineBuilder with a new Line
     */
    public LineBuilder setLine(Line line) {
        this.line = line;
        return this;
    }

    /**
     * Method creates a new Line with the same starting Point of the given Line, but a different Length
     *
     * @param newLength new Length of the Line
     * @return Line with the same starting coordinates but a different length
     */
    public LineBuilder adjustLineFromStart(double newLength) {
        double x2 = line.getFrom_x() + newLength * Math.cos(FractalUtils.getAngle(line));
        double y2 = line.getFrom_y() + newLength * Math.sin(FractalUtils.getAngle(line));

        this.line = new Line(line.getFrom_x(), line.getFrom_y(), x2, y2);
        return this;
    }

    /**
     * Method creates a new Line with the same endpoint of the given Line, but a different Length
     *
     * @param newLength new Length of the Line
     * @return Line with the same ending coordinates but a different length
     */
    public LineBuilder adjustLineFromEnd(double newLength) {
        double x1 = line.getTo_x() - newLength * Math.cos(FractalUtils.getAngle(line));
        double y1 = line.getTo_y() - newLength * Math.sin(FractalUtils.getAngle(line));

        this.line = new Line(x1, y1, line.getTo_x(), line.getTo_y());
        return this;
    }

    /**
     * create a new rotated line, with a given length from the starting point of the initial Line
     *
     * @param length length of the line
     * @param degree degrees to be rotated
     * @return new rotated line
     */
    public LineBuilder createRotatedLineFromStart(double length, double degree) {
        double angle = Math.atan2(this.line.getTo_y() - this.line.getFrom_y(), this.line.getTo_x() - this.line.getFrom_x());

        double x2 = this.line.getFrom_x() + length * Math.cos(angle - Math.toRadians(degree));
        double y2 = this.line.getFrom_y() + length * Math.sin(angle - Math.toRadians(degree));

        this.line = new Line(this.line.getFrom_x(), this.line.getFrom_y(), x2, y2);
        return this;
    }


    /**
     * create a new rotated line, with a given length from the endpoint of the initial Line
     *
     * @param length length of the line
     * @param degree degrees to be rotated
     * @return new rotated line
     */
    public LineBuilder createRotatedLineFromEnd(double length, double degree) {
        double x2 = this.line.getTo_x() + length * Math.cos(FractalUtils.getAngle(this.line) - Math.toRadians(degree));
        double y2 = this.line.getTo_y() + length * Math.sin(FractalUtils.getAngle(this.line) - Math.toRadians(degree));

        this.line = new Line(this.line.getTo_x(), this.line.getTo_y(), x2, y2);
        return this;
    }

    /**
     * @return Line
     */
    public Line build() {
        return this.line;
    }
}
