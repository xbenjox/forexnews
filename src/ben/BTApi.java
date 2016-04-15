package ben;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

class BTApi {

  public String MarketSummary;

  public BTApi() {
    try {
      System.out.println("Contacting BleuTrade...");
      }
    catch(Exception e) {
      e.printStackTrace();
      }
    }

  private String executePost(String targetURL, String urlParameters) {
    HttpURLConnection connection = null;  
    try {
      //Create connection
      URL url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", 
          "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length", 
          Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");  

      connection.setUseCaches(false);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream (
          connection.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.close();

      //Get Response  
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      StringBuilder response = new StringBuilder();  
      String line;
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if(connection != null) {
        connection.disconnect(); 
      }
    }
  }

  public void getMarketSummaries() {
    parseMarketSummaries(executePost("https://bleutrade.com/api/v2/public/getmarketsummaries",""));    
  }

  private void parseMarketSummaries(String data) {
    try{ 
      JSONObject obj = (JSONObject) new JSONParser().parse(data);

      System.out.println(obj.get("success"));
      System.out.println(obj.get("message"));
      System.out.println(obj.get("result"));

      JSONArray markets = (JSONArray) obj.get("result");
      for(Object aObj : markets) {
        JSONObject market = (JSONObject) aObj;
        System.out.println(market.get("MarketName"));
      }
     // if( (String) obj.get("success").equals("true")) {
        this.MarketSummary = data;
    //  }
     // else {
     //   this.MarketSummary = "Error";
     // }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }


  private void parseJSON(String jsonData) {
    //JSONObject obj = new JSONObject(" .... ");
    //String pageName = obj.getJSONObject("pageInfo").getString("pageName");

    //JSONArray arr = obj.getJSONArray("posts");
    //for (int i = 0; i < arr.length(); i++) {
    //  String post_id = arr.getJSONObject(i).getString("post_id");
    //}

  }
}
