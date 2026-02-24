public class Main {

    public static void main(String[] args) {
        PasswordChangeEvent eventOne = new PasswordChangeEvent("321928399");
        AccountTransferEvent eventTwo = new AccountTransferEvent("1936428194");
        MissedPaymentEvent eventThree = new MissedPaymentEvent("93827451");

        Event[] events = {eventOne, eventTwo, eventThree};

        for (Event e : events) {
            System.out.println(e.getTimeStamp());
            e.process();
            System.out.println();
        }
    }
}
