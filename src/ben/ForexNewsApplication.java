package ben;

public class ForexNewsApplication {
  NewsApi api = new NewsApi();

  public static void main(java.lang.String[] args) {
    ForexNewsApplication newsApp = new ForexNewsApplication();
    newsApp.run();
  }

  private void run() {
    try {
      System.out.println("Starting...");

      System.out.println(api.thisWeekNews);

      System.out.println("... Ending.");
      }
    catch(Exception e) {
      e.printStackTrace();
      }
  }
}

