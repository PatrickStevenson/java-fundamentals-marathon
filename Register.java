import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Register {

  public static String RECEIPT_READ_PATH = "./outstandingReceipts.txt";

  public static void main(String[] args) throws IOException {
    double cashRegisterBalance = getMoneyValue("How much money do you have?");

    File receiptList = new File(RECEIPT_READ_PATH);
    Scanner receiptScanner = new Scanner(receiptList);
    while (receiptScanner.hasNextLine()) {
      cashRegisterBalance += receiptScanner.nextInt();
    }

    System.out.printf("Cash balance is $%.2f\n", cashRegisterBalance);

    double customerOrder = getMoneyValue("What is the order cost?");
    System.out.println(customerOrder);

    double customerPayment = getMoneyValue("How much cash did the customer provide?");
    System.out.println(customerPayment);

    while (customerPayment < customerOrder) {
      customerPayment = getMoneyValue("Not enough to cover order. Please provide more funds.");
    }

//    Calculate change
    double changeDue = customerPayment - customerOrder;
//    System.out.printf("Change due: $%.2f\n", changeDue);

    while (cashRegisterBalance < changeDue || changeDue < 0) {
      customerPayment = getMoneyValue("Please provide cash closer to the amount due.");
      changeDue = customerPayment - customerOrder;
    }
    System.out.printf("Change due: $%.2f\n", changeDue);

  }

  public static double getMoneyValue(String prompt) {
    System.out.println(prompt);
    Scanner scanner = new Scanner(System.in);
    String orderCostString = scanner.nextLine();

    while (orderCostString.isBlank()) {
      System.out.println("You have entered a blank value. Please enter a valid value.");
      orderCostString = scanner.nextLine();
    }

    double orderCost = Double.parseDouble(orderCostString);
    return orderCost;
  }

}