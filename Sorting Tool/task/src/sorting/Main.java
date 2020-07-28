package sorting;

import java.util.*;

public class Main {

  public static void main(final String[] args) {
    Parser parser = new Parser();
    parser.parse(args);

    List<String> userInput = parser.getUserInput();
    SortingTool sortingTool;

    if (DataType.LONG == parser.getDataType()) {
      List<Long> longs = new ArrayList<>();
      for (String input : userInput) {
        try {
          longs.add(Long.parseLong(input));
        } catch (Exception e) {
          e.getMessage();
          System.out.println(input + " isn't a long. It's skipped.");
        }
      }
      sortingTool = new SortingTool<>(longs, parser.getSortingType());
    } else {
      sortingTool = new SortingTool<>(userInput, parser.getSortingType());
    }

    sortingTool.print(parser.getDataType(), parser.getOutputFileName());
  }
}
