import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    apiExChange apiExChange = new apiExChange();
    CurrencyMapper currencyMapper = new CurrencyMapper();
    List<String> conversionHistory = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    while (true) {
      try {
        System.out.println("Enter the currency of origin:");
        String fromCurrencyText = scanner.nextLine().toUpperCase();

        System.out.println("Enter the target currency:");
        String toCurrencyText = scanner.nextLine().toUpperCase();

        System.out.println("Enter the value in " + fromCurrencyText);
        double valueCurrency = scanner.nextDouble();
        scanner.nextLine();

        String fromCurrency = currencyMapper.mapCurrency(fromCurrencyText);
        String toCurrency = currencyMapper.mapCurrency(toCurrencyText);

        ExChange api = apiExChange.getExchangeRate(fromCurrency, toCurrency);
        System.out.println("Conversion rate: " + api.conversion_rate());

        var convertValue = api.convert(valueCurrency);

        System.out.println(
          valueCurrency + " " + fromCurrency + " is equivalent to: "
            + convertValue + " " + toCurrency
        );

        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        String conversion = formattedDateTime + " " + valueCurrency + " " + fromCurrency + " to " +
          toCurrency + ": " + convertValue;
        conversionHistory.add(conversion);

        System.out.println("""
                        Do you want to continue? type yes to 
                        continue or press enter key to exit
                        """);

        String continueOption = scanner.nextLine().toLowerCase();

        if (!continueOption.equals("yes")) {
          System.out.println("Exiting program...");
          System.out.println("Conversion history:");
          for (String entry : conversionHistory) {
            System.out.println(entry);
          }
          break;
        }
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
        System.out.println("Finalizing the application");
      }
    }
  }
}
