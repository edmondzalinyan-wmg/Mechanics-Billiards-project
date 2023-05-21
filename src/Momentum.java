import java.util.Random;

public class Momentum extends CoordinatePair {

    public Momentum(double x, double y) {
        super(x, y);
    }

    public static Momentum createRandomMomentumInRange(int lowerBound, int upperBound) {
        Random random = new Random();
        //using polar system
        double distance = lowerBound + random.nextDouble() * (upperBound - lowerBound);
        double angle = random.nextDouble() * 360;
        double x = Math.cos(angle) * distance;
        double y = Math.sin(angle) * distance;

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
