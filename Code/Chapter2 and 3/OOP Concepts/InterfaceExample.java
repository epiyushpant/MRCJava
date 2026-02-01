// InterfaceBestExample.java

// Interface
interface Payment {
    void pay(double amount);
}

// Credit Card Payment
class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

// Mobile Banking Payment
class MobileBankingPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Mobile Banking");
    }
}

// Cash Payment
class CashPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " in Cash");
    }
}

// Main class
public class InterfaceExample {
    public static void main(String[] args) {

        Payment p1 = new CreditCardPayment();
        Payment p2 = new MobileBankingPayment();
        Payment p3 = new CashPayment();

        p1.pay(5000);
        p2.pay(2500);
        p3.pay(1000);
    }
}
