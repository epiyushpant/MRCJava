class NepalBank {

    double getInterestRate() {
        return 0.0;
    }

    void displayBankInfo() {
        System.out.println("This is a bank registered in Nepal");
    }
}

class NABIL extends NepalBank {

    @Override
    double getInterestRate() {
        return 8.0;
    }

    @Override
    void displayBankInfo() {
        System.out.println("NABIL Bank - One of the leading banks in Nepal");
    }
}

class NICAsia extends NepalBank {

    @Override
    double getInterestRate() {
        return 7.5;
    }
}

public class MethodOverridingNepalDemo {

    public static void main(String[] args) {

        NepalBank bank;

        bank =new NepalBank(); 
        System.out.println("Nepal Bank Interest Rate: " + bank.getInterestRate());
        bank.displayBankInfo();

        bank = new NABIL();  
        System.out.println("NABIL Interest Rate: " + bank.getInterestRate());
        bank.displayBankInfo();

        bank = new NICAsia(); 
        System.out.println("NIC Asia Interest Rate: " + bank.getInterestRate());
        bank.displayBankInfo();

        //BCRV pointing to child class object will call particular child method 
        

    }
}

