package edu.sdccd.cisc191.template;

import java.util.Arrays;
import java.util.Scanner;

public class CLI {
  static String[][] array = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };

  public static void main(String[] args) {
    // module 1 & 2
    Scanner scanner = new Scanner(System.in);
    String input;
    do {
      System.out.println(
          "Choose from the following:\n(e)xit\n(g)etAtIndex\n(s)etAtIndex\n(f)indIndexOf\n(p)rintAll\n(d)eleteAtIndex\n(ex)pand\n(sh)rink");
      input = scanner.nextLine().trim().toLowerCase();
      int indexOne;
      int indexTwo;

      switch (input) {
        case "p":
          System.out.println(Arrays.deepToString(array));
          break;
        case "ex":
        case "sh": {
          String direction;
          do {
            System.out.println("(h)orizontally or (v)ertiacally?");
            direction = scanner.nextLine().trim().toLowerCase();
          } while (!direction.equals("h") && !direction.equals("v"));
          if (direction.equals("h")) {
            if (input.equals("ex")) {
              // expand horizontally
              array = Arrays.copyOf(array, array.length + 1);
              // initialize each new cell to ""
              for (int i = array.length - 1; i >= array.length - 1; i--) {
                array[i] = new String[array[i].length + 1];
              }
            } else {
              // shrink horizontally
              array = Arrays.copyOf(array, array.length - 1);
              // initialize each new cell to ""
              for (int i = array.length - 1; i >= array.length - 1; i--) {
                array[i] = new String[array[i].length - 1];
              }
            }
          } else {
            if (input.equals("ex")) {
              // expand vertically
              for (int i = 0; i < array.length; i++) {
                array[i] = Arrays.copyOf(array[i], array[i].length + 1);
              }
            } else {
              // shrink vertically
              for (int i = 0; i < array.length; i++) {
                array[i] = Arrays.copyOf(array[i], array[i].length - 1);
              }
            }
          }
          break;
        }
        default: {
          System.out.println("Index 1?");
          indexOne = scanner.nextInt();
          System.out.println("Index 2?");
          indexTwo = scanner.nextInt();
          switch (input) {
            case "g":
              System.out.println(array[indexOne][indexTwo]);
              break;
            case "s":
              System.out.println("Value?");
              array[indexOne][indexTwo] = scanner.nextLine();
              break;
            case "f":
              System.out.println("Value?");
              // find index of value in the array
              for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                  if (array[i][j].equals(scanner.nextLine())) {
                    System.out.println(i + " " + j);
                  }
                }
              }
              break;
            case "d":
              array[indexOne] = Arrays.copyOf(array[indexOne], array[indexOne].length - 1);
              break;
          }
        }
      }
    } while (!input.equals("e"));
    scanner.close();
  }
}
