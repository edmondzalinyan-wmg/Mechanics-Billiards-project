import java.util.ArrayList;
import java.util.List;

public class Task3 {
    /**
     * For Task 3, the (0,0) point is taken in the middle of the Stadium.
     * So the centers of circles are becoming (-L/2; 0) and (L/2,0)
     * Computations are done on that coordinate system.
     */
    public static void main(String[] args) {

        Particle p = Particle.createRandom();
        double L = 2;
        int n = 50;
        List<Position> positionList = new ArrayList<>();
        List<Position> reversePositionList = new ArrayList<>();
        for (int i = 0; i < n; i++) { //iterate the path
            p.moveStraightToReflectionPointInStadium(L);
            p.reflectInStadium(L);
            positionList.add(p.getPosition());
        }
        p.moveStraightToReflectionPointInStadium(L);
//        do not reflect this time
        p.reverse();
        for (int i = 0; i < n; i++) { //iterate the reversed path
            p.moveStraightToReflectionPointInStadium(L);
            p.reflectInStadium(L);
            reversePositionList.add(p.getPosition());
        }
        for (int i = 0; i < positionList.size(); i++) {
            System.out.println(positionList.get(i) + " vs " + reversePositionList.get(n - i - 1));
            /*
            Points on the reversed path almost coincide with the points of the original path.
            The tiny difference is because of how Java approximates doubles and happens on ~10^-16 precision.
            */
        }

    }
}
