import java.util.Random;

public class Momentum extends CoordinatePair {

    public Momentum(double x, double y) {
        super(x, y);
    }

    public static Momentum createRandomMomentum() {
        Random random = new Random();
        double x = random.nextDouble();
        double y = Math.sqrt(1 - x * x);
        return new Momentum(x, y);
    }

    public void reverse() {
        this.setX(-this.getX());
        this.setY(-this.getY());
    }

    @Override
    public String toString() {
        return "momentum (" + this.getX() + ", " + this.getY() + ")";
    }
}
