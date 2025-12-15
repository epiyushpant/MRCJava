public class StringManipulationDemo {

    public static void DemoStringManipulation() {

        String s = "  Hello Java Programming  ";

        // 1. trim()
        System.out.println("Trimmed: '" + s.trim() + "'");

        // 2. length()
        System.out.println("Length: " + s.length());

        // 3. toUpperCase() & toLowerCase()
        System.out.println("Uppercase: " + s.toUpperCase());
        System.out.println("Lowercase: " + s.toLowerCase());

        // 4. charAt()
        System.out.println("charAt(3): " + s.charAt(3));

        // 5. substring()
        System.out.println("substring(2, 7): " + s.substring(2, 7));

        // 6. contains()
        System.out.println("Contains 'Java'? " + s.contains("Java"));

        // 7. equals() & equalsIgnoreCase()
        System.out.println("Equals 'HELLO'? " + s.trim().equals("HELLO"));
        System.out.println("equalsIgnoreCase 'hello'? " + s.trim().equalsIgnoreCase("hello"));

        // 8. startsWith() & endsWith()
        System.out.println("Starts with '  He'? " + s.startsWith("  He"));
        System.out.println("Ends with 'g  '? " + s.endsWith("g  "));

        // 9. indexOf() & lastIndexOf()
        System.out.println("indexOf('a'): " + s.indexOf('a'));
        System.out.println("lastIndexOf('a'): " + s.lastIndexOf('a'));

        // 10. replace()
        System.out.println("Replace 'Java' with 'Python': " + s.replace("Java", "Python"));

        // 11. split()
        String data = "apple,banana,orange";
        String[] fruits = data.split(",");
        System.out.println("\nSplit fruits:");
        for (String f : fruits) {
            System.out.println(f);
        }

        // 12. join()
        String date = String.join("-", "2025", "01", "15");
        System.out.println("\nJoined Date: " + date);

        // 13. StringBuilder example
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        sb.insert(5, " Java");
        System.out.println("\nStringBuilder: " + sb.toString());

        // 14. Reverse a string
        String rev = "";
        String word = "Java";
        for (int i = word.length() - 1; i >= 0; i--) {
            rev += word.charAt(i);
        }
        System.out.println("\nReversed 'Java': " + rev);

        // 15. Count vowels
        String str = "Programming";
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        System.out.println("Vowel Count in 'Programming': " + count);

        // 16. Palindrome check
        String p = "madam";
        String rev2 = new StringBuilder(p).reverse().toString();
        System.out.println("Is 'madam' palindrome? " + p.equals(rev2));
    }

    public static void showStringBuilderFunctions() {
            System.out.println("=== StringBuilder Common Operations ===\n");

            StringBuilder sb = new StringBuilder("Hello");

            // 1. append()
            sb.append(" World");
            System.out.println("append(): " + sb);

            // 2. insert()
            sb.insert(5, " Java");
            System.out.println("insert(): " + sb);

            // 3. delete()
            sb.delete(5, 10);
            System.out.println("delete(): " + sb);

            // 4. deleteCharAt() → pop-like behavior
            sb.deleteCharAt(sb.length() - 1);
            System.out.println("deleteCharAt() / pop: " + sb);

            // 5. replace()
            sb.replace(0, 2, "Hi");
            System.out.println("replace(): " + sb);

            // 6. setCharAt()
            sb.setCharAt(2, '!');
            System.out.println("setCharAt(): " + sb);

            // 7. reverse()
            sb.reverse();
            System.out.println("reverse(): " + sb);

            // 8. length()
            System.out.println("length(): " + sb.length());

            // 9. substring() - read-only
            System.out.println("substring(0, 3): " + sb.substring(0, 3));

            // 10. capacity()
            System.out.println("capacity(): " + sb.capacity());
        }


        static void comparePerformance() {

            int iterations = 100_000;   // large operations
            long start, end;

            // ---------------------------
            // Using String (slow)
            // ---------------------------
            start = System.currentTimeMillis();
            String str = "";
            for (int i = 0; i < iterations; i++) {
                str += "a";    // creates new object each time
            }
            end = System.currentTimeMillis();
            long stringTime = end - start;

            // ---------------------------
            // Using StringBuilder (fast)
            // ---------------------------
            start = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < iterations; i++) {
                sb.append("a");   // modifies same object
            }
            end = System.currentTimeMillis();
            long builderTime = end - start;

            // ---------------------------
            // Show output
            // ---------------------------
            System.out.println("=== Performance Test ===");
            System.out.println("String time       : " + stringTime + " ms");
            System.out.println("StringBuilder time: " + builderTime + " ms");

            if (stringTime > builderTime)
                System.out.println("\nStringBuilder is faster by " + (stringTime - builderTime) + " ms");
            else
                System.out.println("\nString and StringBuilder time difference is small here.");
        }
}

