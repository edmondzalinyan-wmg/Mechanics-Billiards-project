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

    public static Particle createRandom() {
        Position pos = Position.createRandomInCircleWithRadius(1);
        Momentum mom = Momentum.createRandomMomentumInRange(0, 1);
        return new Particle(pos, mom);
    }

    private boolean isOutOfCircle(Position position) {
        return (position.getX() * position.getX())
                + (position.getY() * position.getY()) > 1;
    }

    public void moveStraightToReflectionPoint() {
        this.setPosition(nextReflectionPositionByStraightPath());
    }

    public void moveStraightToReflectionPointInStadium(double L) {
        this.setPosition(nextReflectionPositionInStadium(L));
    }

    public void reflect() {
        this.setMomentum(getReflectedMomentum());
    }

    public void reflectInStadium(double L) {
        this.setMomentum(getReflectionMomentumInStadium(L));
    }

    public void reverse() {
        this.momentum.reverse();
    }

    public Position nextReflectionPositionByStraightPath() {
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

    public Position nextReflectionPointByParabolicPath() {
        // y=a(x-d)^2+c
        // y'=2a(x-d)
        double a, d, c;
        c = position.getY();
        d = position.getX();
        a = position.getY() / (2 * (position.getX() - d));
        return null;
    }

    public Position nextReflectionPositionInStadium(double L) {
        double k, b; //y=kx+b
        if (momentum.getX() != 0)
            k = momentum.getY() / momentum.getX();
        else {
            if (position.getX() < -L / 2) { //left side
                if (momentum.getY() > 0) //direction check
                    return new Position(position.getX(), Math.sqrt(1 - (position.getX() + L) * (position.getX() + L)));
                else
                    return new Position(position.getX(), -Math.sqrt(1 - (position.getX() + L) * (position.getX() + L)));
            }
            if (position.getX() > L / 2) { //right side
                if (momentum.getY() > 0) //direction check
                    return new Position(position.getX(), Math.sqrt(1 - (position.getX() - L) * (position.getX() - L)));
                else
                    return new Position(position.getX(), -Math.sqrt(1 - (position.getX() - L) * (position.getX() - L)));
            }
            //in the middle
            if (position.getY() > 0)
                return new Position(position.getX(), 1);
            else return new Position(position.getX(), -1);
        }

        b = position.getY() - k * position.getX();

        double x, y;
        if (momentum.getX() > 0) {  //will go right
            if ((1 - b) / k > L / 2 || (-1 - b) / k > L / 2) //check if hits circle
                x = (Math.sqrt(-4 * b * b - 4 * b * k * L - k * k * (L * L - 4) + 4)
                        - 2 * b * k + L)
                        / (2 * (k * k + 1));
            else //will hit  the straight lines
                if (momentum.getY() > 0) //check the direction
                    x = (1 - b) / k;
                else x = (-1 - b) / k;
        } else { //will go left
            if ((1 - b) / k < -L / 2 || (-1 - b) / k < -L / 2) //check hits circle
                x = -(Math.sqrt(-4 * b * b + 4 * b * k * L - k * k * (L * L - 4) + 4)
                        + 2 * b * k + L)
                        / (2 * (k * k + 1));
            else //will hit  the straight lines
                if (momentum.getY() > 0) //check the direction
                    x = (1 - b) / k;
                else x = (-1 - b) / k;
        }

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

    public Momentum getReflectionMomentumInStadium(double L) {

        if (position.getY() == 1 || position.getY() == -1) //we are on the straight line
            return new Momentum(momentum.getX(), -momentum.getY());

        double x = position.getX();
        double y = position.getY();
        double px = momentum.getX();
        double py = momentum.getY();
        double xc, newpx, newpy;

        if (x > L / 2)
            xc = L / 2; //we are on right semicircle
        else xc = -L / 2; // we are on left semicircle

        newpx = (y * y - (x - xc) * (x - xc)) * px - 2 * (x - xc) * y * py;
        newpy = -2 * (x - xc) * y * px + ((x - xc) * (x - xc) - y * y) * py;
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
