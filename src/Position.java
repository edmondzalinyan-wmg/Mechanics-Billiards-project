import java.util.Random;

public class Position extends CoordinatePair {

    public Position(double x, double y) {
        super(x, y);
    }

    public static Position createRandomInCircleWithRadius(double radius) {
        Random random = new Random();
        //using polar system to get random point
        double distance = random.nextDouble() * radius;
        double angle = random.nextDouble() * 360;
        double x = Math.cos(angle) * distance;
        double y = Math.sin(angle) * distance;

        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "point (" + this.getX() + ", " + this.getY() + ")";
    }
}
