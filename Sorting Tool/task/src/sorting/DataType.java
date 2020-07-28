package sorting;

public enum DataType {
  LONG("numbers"),
  LINE("lines"),
  WORD("words");

  String pluralForm;

  DataType(String pluralForm) {
    this.pluralForm = pluralForm;
  }
  public String getPluralForm() {
    return this.pluralForm;
  }
}
