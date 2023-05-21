import java.util.Random;

public class Position extends CoordinatePair {

    public Position(double x, double y) {
        super(x, y);
    }

    public static Position getRandomInCircleWithRadius(double radius) {
        Random random = new Random();
        double distance = random.nextDouble() * radius;
        double angle = random.nextDouble() * 360;
        double x = Math.cos(angle) * distance;

        distance = random.nextDouble() * radius;
        angle = random.nextDouble() * 360;
        double y = Math.sin(angle) * distance;
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "point [" + this.getX() + "; " + this.getY() + "]";
    }
}
