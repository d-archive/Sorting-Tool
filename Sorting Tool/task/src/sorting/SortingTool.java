package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class SortingTool<T extends Comparable> {
  private List<T> list;
  private SortingType sortingType;
  private Map<T, Integer> dataEntryToQuantity;
  private Map<Integer, Set<T>> quantityToDataEntries;

  public SortingTool(List<T> list, SortingType sortingType) {
    this.list = list;
    this.sortingType = sortingType;
    this.dataEntryToQuantity = new HashMap<>();
    this.quantityToDataEntries = new HashMap<>();
    sort();
  }

  private void sort() {
    if (SortingType.BYCOUNT == sortingType) {
      sortByCount();
    } else {
      naturalSort();
    }
  }

  private List<T> naturalSort() {
    Collections.sort(list);
    return list;
  }

  private Map<Integer, Set<T>> sortByCount() {
    for (T elem : list) {
      dataEntryToQuantity.put(elem, dataEntryToQuantity.getOrDefault(elem, 0) + 1);
    }

    for (Map.Entry<T, Integer> pair : dataEntryToQuantity.entrySet()) {
      if (quantityToDataEntries.containsKey(pair.getValue())) {
        Set<T> lines = quantityToDataEntries.get(pair.getValue());
        lines.add(pair.getKey());
      } else {
        quantityToDataEntries.put(pair.getValue(), new TreeSet<>(Arrays.asList(pair.getKey())));
      }
    }

    return quantityToDataEntries;
  }

  public void print(DataType dataType, String outputFileName) {
    File file = new File(".\\" + outputFileName);

      if (SortingType.BYCOUNT == sortingType) {
        if (outputFileName != null) {
          try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf(getTotalString(dataType));
            for (String s : getQuantityStrings()) {
              printWriter.print(s);
            }
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
        } else {
          System.out.print(getTotalString(dataType));
          for (String s : getQuantityStrings()) {
            System.out.print(s);
          }
        }
      } else {
        if (outputFileName != null) {
          try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf(getTotalString(dataType));
            printWriter.printf("Sorted data: ");
            list.forEach(e -> printWriter.print(e + " "));
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
        } else {
          System.out.print(getTotalString(dataType));
          System.out.print("Sorted data: ");
          list.forEach(e -> System.out.print(e + " "));
        }
    }
  }

  private String getTotalString(DataType dataType) {
    return String.format("Total %s: %d.%n", dataType.getPluralForm(), list.size());
  }

  private List<String> getQuantityStrings() {
    List<Integer> quantities = new ArrayList(quantityToDataEntries.keySet());
    Collections.sort(quantities);
    List<String> times = new ArrayList<>();

    for (Integer quantity : quantities) {
      long percentage = Math.round((double) quantity / (double) list.size() * 100);
      for (T line : quantityToDataEntries.get(quantity)) {
        times.add(String.format("%s: %d times(s), %d%%%n", line, quantity, percentage));
      }
    }

    return times;
  }
}
