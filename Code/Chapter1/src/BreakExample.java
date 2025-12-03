public class BreakExample {
public static void ShowBreakExample(){
        for (int i = 1; i <= 10; i++) {

            if (i == 5) {
                System.out.println("Number 5 found — breaking the loop!");
                break;   // loop stops here
            }
            System.out.println("i = " + i);
        }

        System.out.println("Loop ended.");

    }


    public static void ShowContinueExample(){
        for (int i = 1; i <= 10; i++) {

            if (i == 5) {
                System.out.println("Skipping number 5");
                continue;  // skip this iteration
            }

            System.out.println("i = " + i);
        }

    }

}
