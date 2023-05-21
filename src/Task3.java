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
        int n = 100;
        List<Position> positionList = new ArrayList<>();
        List<Position> reversePositionList = new ArrayList<>();
        for (int i = 0; i < n; i++) { //iterate the original path
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
            //compare corresponding points
            System.out.println(positionList.get(i) + " vs " + reversePositionList.get(n - i - 1));
            /*
            Points on the reversed path almost coincide with the points of the original path.
            The tiny difference is because of how Java approximates doubles and happens on ~10^-16 precision.
            */
        }

        System.out.println("\n\n\n                      SUBTASK 3 \n");

        List<Double> randoms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = positionList.get(i).getX();
            double y = positionList.get(i).getY();
            if (y == 1 || y == -1)
                randoms.add(x / L);
        }
        int M = 4;
        int[] countInBean = new int[M];
        System.out.println("total " + randoms.size() + " numbers in [-1,1] range" +
                "\n the quantities in " + M + " equal beans` ");
        randoms.sort(Double::compareTo);
        for (int j = 0; j < M; j++)
            for (int i = 0; i < randoms.size(); i++) {
                double k = randoms.get(i);
                if (k > -1 + j * (2. / M) && k < -1 + (j + 1) * (2. / M))
                    countInBean[j]++;
            }
        for (int i = 0; i < M; i++)
            System.out.print(countInBean[i] + "          ");
        /*
        For large number of n, number of values in beans differs very much
         and we can safely assume that the distribution is not uniform
         */
    }
}
