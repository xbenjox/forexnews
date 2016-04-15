package ben;

class ConsoleCol {

  public static void green() {
    System.out.print("\u001B[32m");
    }

  public static void reset() {
    System.out.print("\u001B[0m");
    }
}

