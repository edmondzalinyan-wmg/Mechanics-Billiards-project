public class Momentum extends CoordinatePair {

    public Momentum(double x, double y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "momentum [" + this.getX() + "; " + this.getY() + "]";
    }
}
