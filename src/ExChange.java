public record ExChange(
  String base_code,
  String target_code,
  double conversion_rate) {

  public double convert(double amount) {
    return amount * conversion_rate;
  }
}
