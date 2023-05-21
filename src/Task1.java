import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {

        Particle p = Particle.createRandom();
        int n = 50;
        List<Position> positionList = new ArrayList<>();
        List<Position> reversePositionList = new ArrayList<>();
        for (int i = 0; i < n; i++) { //iterate the path
            p.moveStraightToReflectionPoint();
            p.reflect();
            positionList.add(p.getPosition());
        }
        p.moveStraightToReflectionPoint();
//        do not reflect this time
        p.reverse();
        for (int i = 0; i < n; i++) { //iterate the reversed path
            p.moveStraightToReflectionPoint();
            p.reflect();
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
