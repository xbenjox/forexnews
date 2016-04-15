package ben;

public class ForexNewsApplication {
  NewsApi api = new BTApi();

  public static void main(java.lang.String[] args) {
    BleuTradeApplication btApp = new BleuTradeApplication();
    btApp.run();
  }

  private void run() {
    try {
      cc.green();
      System.out.println("Starting...");

      api.getMarketSummaries();
      System.out.println(api.MarketSummary);

      System.out.println("... Ending.");
      cc.reset();    
      }
    catch(Exception e) {
      e.printStackTrace();
      }
  }
}

