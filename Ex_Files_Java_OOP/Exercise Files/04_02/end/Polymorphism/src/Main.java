import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        OddArrayList oddListy = new OddArrayList(1, 2, 3, 4,
                7, 9, -13);

        System.out.println(oddListy);

        ArrayList<Integer> listy = new ArrayList<>();

        listy.add(1);
        listy.add(2);
        listy.add(3);
        listy.add(4);
        listy.add(7);
        listy.add(9);
        listy.add(-13);

        System.out.println(listy);
    }
}
