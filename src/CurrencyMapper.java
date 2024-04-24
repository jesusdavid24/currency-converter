import java.util.HashMap;
import java.util.Map;

public class CurrencyMapper {
  private Map<String, String> currencyMap;

  public CurrencyMapper() {
    currencyMap = new HashMap<>();
    currencyMap.put("DOLAR", "USD");
    currencyMap.put("EURO", "EUR");
    currencyMap.put("PESO ARGENTINO", "ARS");
    currencyMap.put("BOLIVIANO", "BOB");
    currencyMap.put("REAL BRASILEÑO", "BRL");
    currencyMap.put("PESO CHILENO", "CLP");
    currencyMap.put("PESO COLOMBIANO", "COP");
    currencyMap.put("DOLAR AUSTRALIANO", "AUD");
    currencyMap.put("DOLAR CANADIENSE", "CAD");
    currencyMap.put("FRANCOS SUIZOS", "CHF");
  }
  public String mapCurrency(String currencyText) {
    return currencyMap.getOrDefault(currencyText.toUpperCase(), currencyText);
  }
}
