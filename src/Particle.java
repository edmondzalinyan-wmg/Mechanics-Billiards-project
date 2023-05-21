public class Particle {
    private Position position;
    private Momentum momentum;
    private final double mass = 1;

    public Particle(Position position) {
        this.position = position;
        this.momentum = new Momentum(0, 0);
    }

    public Particle(Position position, Momentum momentum) {
        this.position = position;
        this.momentum = momentum;
    }

    public Particle(double x, double y) {
        this.position = new Position(x, y);
    }

    public static Particle createRandom() {
        Position pos = Position.createRandomInCircleWithRadius(1);
        Momentum mom = Momentum.createRandomMomentum();
        return new Particle(pos, mom);
    }

    private boolean isOutOfCircle(Position position) {
        return (position.getX() * position.getX())
                + (position.getY() * position.getY()) > 1;
    }

    public void moveTillReflection() {
        this.setPosition(findNextReflectionPosition());
    }

    public void reflect() {
        this.setMomentum(getReflectedMomentum());
    }

    public void reverse() {
        this.momentum.reverse();
    }

    public Position findNextReflectionPosition() {
        double k, b; //y=kx+b
        if (momentum.getX() != 0)
            k = momentum.getY() / momentum.getX();
        else {
            if (momentum.getY() > 0) //check for direction
                return new Position(position.getX(), Math.sqrt(1 - position.getX() * position.getX()));
            else
                return new Position(position.getX(), -Math.sqrt(1 - position.getX() * position.getX()));
        }
        b = position.getY() - k * position.getX();

        //y=kx+b & y*y+x*x=1
        double x, y;
        if (momentum.getX() > 0) //check for direction
            x = (Math.sqrt(-b * b + k * k + 1) - b * k) / (k * k + 1);
        else x = (-Math.sqrt(-b * b + k * k + 1) - b * k) / (k * k + 1);

        y = k * x + b;
        return new Position(x, y);
    }

    public Momentum getReflectedMomentum() {
        double newpx, newpy, x, y, px, py;
        x = position.getX();
        y = position.getY();
        py = momentum.getY();
        px = momentum.getX();

        newpx = (y * y - x * x) * px - 2 * x * y * py;
        newpy = -2 * x * y * px + (x * x - y * y) * py;

        return new Momentum(newpx, newpy);
    }

    @Override
    public String toString() {
        return "particle at (" + position.getX() + ", " + position.getY() + ")" +
                " with momentum [" + momentum.getX() + "; " + momentum.getY() + "]";
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Momentum getMomentum() {
        return momentum;
    }

    public void setMomentum(Momentum momentum) {
        this.momentum = momentum;
    }
}
