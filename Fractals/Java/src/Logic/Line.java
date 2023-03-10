package Logic;

public class Line {

    private final double from_x;
    private final double from_y;

    private final double to_x;
    private final double to_y;

    public Line(double from_x, double from_y, double to_x, double to_y) {
        this.from_x = from_x;
        this.from_y = from_y;
        this.to_x = to_x;
        this.to_y = to_y;
    }

    public double getFrom_x() { return this.from_x; }
    public double getFrom_y() { return this.from_y; }
    public double getTo_x() { return this.to_x; }
    public double getTo_y() { return this.to_y; }
}
