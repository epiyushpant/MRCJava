import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        OddArrayList oddListy = new OddArrayList(7);
        oddListy.add(1);
        oddListy.add(2);
        System.out.println(oddListy.size());

        ArrayList<Integer> listy = new ArrayList<>();
        listy.add(1);
        listy.add(2);
        System.out.println(listy.size());
    }
}
