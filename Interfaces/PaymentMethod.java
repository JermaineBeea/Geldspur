package Interfaces;

interface PaymentMethod {
    boolean processPayment(double amount);
    void refund(double amount);
    double getBalance();
    String getPaymentType();
}

@SuppressWarnings("all")
// CreditCard class implementing PaymentMethod
class CreditCard implements PaymentMethod {
    private String cardNumber;
    private String cardholderName;
    private double balance;
    private double limit;
    
    public CreditCard(String cardNumber, String cardholderName, double limit) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.limit = limit;
        this.balance = 0;
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (balance + amount <= limit) {
            balance += amount;
            System.out.println("Credit card payment processed: $" + amount);
            return true;
        }
        System.out.println("Credit card payment failed: Exceeds limit");
        return false;
    }
    
    @Override
    public void refund(double amount) {
        balance -= amount;
        System.out.println("Refunded $" + amount + " to credit card");
    }
    
    @Override
    public double getBalance() {
        return balance;
    }
    
    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}

@SuppressWarnings("all")
// PayPal class implementing PaymentMethod
class PayPal implements PaymentMethod {
    private String email;
    private double balance;
    
    public PayPal(String email, double initialBalance) {
        this.email = email;
        this.balance = initialBalance;
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("PayPal payment processed: $" + amount);
            return true;
        }
        System.out.println("PayPal payment failed: Insufficient funds");
        return false;
    }
    
    @Override
    public void refund(double amount) {
        balance += amount;
        System.out.println("Refunded $" + amount + " to PayPal account");
    }
    
    @Override
    public double getBalance() {
        return balance;
    }
    
    @Override
    public String getPaymentType() {
        return "PayPal";
    }
}

// Example usage
class PaymentProcessor {
    public static void main(String[] args) {
        // Create different payment methods
        PaymentMethod creditCard = new CreditCard("1234-5678-9012-3456", "John Doe", 5000);
        PaymentMethod payPal = new PayPal("john.doe@example.com", 1000);
        
        // Process an order using different payment methods
        processOrder(creditCard, 499.99);
        processOrder(payPal, 299.99);
        
        // Check balances
        System.out.println("\nCurrent balances:");
        System.out.println(creditCard.getPaymentType() + ": $" + creditCard.getBalance());
        System.out.println(payPal.getPaymentType() + ": $" + payPal.getBalance());
        
        // Process a refund
        System.out.println("\nProcessing refund...");
        creditCard.refund(100);
        
        // Check updated balance
        System.out.println("Updated " + creditCard.getPaymentType() + " balance: $" + creditCard.getBalance());
    }
    
    // Method that works with any payment method
    public static void processOrder(PaymentMethod payment, double amount) {
        System.out.println("\nProcessing order of $" + amount + " with " + payment.getPaymentType());
        boolean success = payment.processPayment(amount);
        if (success) {
            System.out.println("Order completed successfully!");
        } else {
            System.out.println("Order failed!");
        }
    }
}