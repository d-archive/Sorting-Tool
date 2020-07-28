package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
  private String[] args;
  private final ArrayList<String> userInput;
  private SortingType sortingType;
  private DataType dataType;
  private String outputFileName;
  private String inputFileName;

  public Parser() {
    this.userInput = new ArrayList<>();
    dataType = DataType.WORD;
  }

  public void parse(final String[] args) {
    this.args = args;

    inputFileName = getArgumentOf("-inputFile");
    outputFileName = getArgumentOf("-outputFile");
    String sortingTypeArgument = getArgumentOf("-sortingType");
    String dataTypeArgument = getArgumentOf("-dataType");

    if ("byCount".equals(sortingTypeArgument)) {
      sortingType = SortingType.BYCOUNT;
    } else if ("natural".equals(sortingTypeArgument)) {
      sortingType = SortingType.NATURAL;
    }

    if ("long".equals(dataTypeArgument)) {
      dataType = DataType.LONG;
    } else if ("line".equals(dataTypeArgument)) {
      dataType = DataType.LINE;
    } else {
      dataType = DataType.WORD;
    }

    if (inputFileName != null) {
      if (!inputFileName.isEmpty()) {
        File file = new File(".\\" + inputFileName);
        try (Scanner fileScanner = new Scanner(file)) {
          readData(fileScanner);
        } catch (FileNotFoundException e) {
          System.out.println("Cannot find file!");
        }
      }
    } else {
      readData(new Scanner(System.in));
    }
  }

  private String getArgumentOf(String option) {
    List<String> argumentList = Arrays.asList(args);
    String argument = null;
    if (argumentList.contains(option)) {
      try {
        argument = argumentList.get(argumentList.indexOf(option) + 1);
        if (argument.startsWith("-")) {
          System.out.printf("No %s argument defined!%n", option);
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.printf("No %s argument defined!%n", option);
      }
    }
    return argument;
  }


  private void readData(Scanner scanner) {
    if (DataType.LINE == dataType) {
      while (scanner.hasNextLine()) {
        userInput.add(scanner.nextLine());
      }
    } else if (DataType.LONG == dataType || DataType.WORD == dataType) {
      while (scanner.hasNext()) {
        userInput.add(scanner.next());
      }
    }
  }

  public ArrayList getUserInput() {
    return userInput;
  }

  public SortingType getSortingType() {
    return sortingType;
  }

  public DataType getDataType() {
    return dataType;
  }

  public String getOutputFileName() {
    return outputFileName;
  }
}
