import java.util.Random;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
