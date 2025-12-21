public class Main {

    public static void main(String[] args) {

        Contact contactOne = new Contact("Sally",
                new PhoneNumber("2637263737"));
        Contact contactTwo = new Contact("Maggie Smith",
                new PhoneNumber(41, "9384713401"));
        Contact contactThree = new Contact("Roger Williams",
                new PhoneNumber("448474734929"));
        Contact contactFour = new Contact("David Jones",
                "david_jones@bluewire.com");
        Contact contactFive = new Contact("Sarah Brown",
                new PhoneNumber("2029384982"), "sarahb@tech.com");

        System.out.println(contactOne);
        System.out.println(contactTwo);
        System.out.println(contactThree);
        System.out.println(contactFour);
        System.out.println(contactFive);
    }
}
