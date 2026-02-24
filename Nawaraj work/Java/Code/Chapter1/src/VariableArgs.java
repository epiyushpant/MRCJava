public class VariableArgs {
        public static int sum(int... numbers) {
            int total = 0;
            for (int n : numbers) {
                total += n;
            }
            return total;
        }

        public static void CallVarArgs(){
            System.out.println(sum(2, 3));           // 5
            System.out.println(sum(1, 2, 3, 4, 5)); // 15
            System.out.println(sum());

            // 0
        }
}


