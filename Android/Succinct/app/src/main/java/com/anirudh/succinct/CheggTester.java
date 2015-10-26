package com.anirudh.succinct;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;
import org.json.simple.*;
public class CheggTester{
  static String [] relatedBooks;

  public static void main(String [] args){
    String test = "Stats: Modeling the World ";
    System.out.println("CURRENT ID: " + textToID(test));
    System.out.println("Testing with: " + test);
    run(test);
    /*for(int i=0; i<relatedBooks.length; i++){
      System.out.println(relatedBooks[i]);
    }
    */
  }
  public static void runn(String t)
  {

  }
  public static String[] run(String textBook){
      String textBookID = textToID(textBook);
      int listLength  = findSimilar(textBookID).length;
      String [] myIDList = new String [listLength];
      myIDList = findSimilar(textBookID);
      relatedBooks = new String [listLength];
      for(int i=0; i<listLength; i++){
        relatedBooks [i] = IDtoName(myIDList[i]);
      }
      return relatedBooks;
  }

  public static String textToID(String bookName){
    String ID = "";

    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(1000, TimeUnit.SECONDS);
    client.setReadTimeout(1000, TimeUnit.SECONDS);

    Request request = new Request.Builder()
      .url("https://hackingedu.chegg.com/hacking-edu/search?q=" + bookName)
      .get()
      .addHeader("accept", "application/json")
      .addHeader("authorization", "Basic TXlPYUhWOUFtN01QdmxuSmJVVVV2SXZyaVBnYmVlQUE6MG1zQVFWbk1wemNS MXNjNA==")
      .addHeader("cache-control", "no-cache")
      //.addHeader("postman-token", "46e822fd-4a38-3db0-b60b-4bb26491f121")
      .build();
      Response response = null;
      String fullResponse = "";
      try{
        response = client.newCall(request).execute();
        fullResponse = response.body().string();
        System.out.println(fullResponse);
      }catch(Exception e){
        System.out.println(e);
      }


  //  ID = response.result[0].responseContent.docs[0].id;
    int x = fullResponse.indexOf("LBP");
    if(x < 0)
      x = 0;
    String finalID = fullResponse.substring(x, x +11);
    System.out.println(fullResponse.substring(x, x +11));
    return finalID;

  }



  public static String [] findSimilar(String idToGetRelated){
    String IDofRelated = "";
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
  .url("https://hackingedu.chegg.com/hacking-edu/catalog/relateditem/priced/getRelatedItems?catalogItemId=" + idToGetRelated)
  .get()
  .addHeader("accept", "application/json")
  .addHeader("content-type", "application/json")
  .addHeader("authorization", "Basic TXlPYUhWOUFtN01QdmxuSmJVVVV2SXZyaVBnYmVlQUE6MG1zQVFWbk1wemNS MXNjNA==")
  .addHeader("cache-control", "no-cache")
  //.addHeader("postman-token", "2b21d3c9-023b-519e-4681-a7e20281879e")
  .build();

  Response response =null;

  try{
    response = client.newCall(request).execute();
    System.out.println(response.toString());
  }catch(Exception e){
    System.out.println(e);
  }
  String myResponse = "";
  try{
    myResponse = response.body().string();
  }catch(Exception e){
    System.out.println(e);
  }
  System.out.println("MY RESPONSE " + myResponse);

  /*JSONObject jObject = new JSONObject(new String(myResponse));
  JSONArray jArray = jObject.getJSONArray("result");*/

  Object obj = JSONValue.parse(myResponse);
  JSONArray array=(JSONArray)obj;
  System.out.println("ARRAY: " + array.toString());
  String [] IDlist = new String [array.size()];
  for(int i=0; i< array.size(); i++){
    IDlist [i] = (String)((JSONObject)array.get(i)).get("catalogItemIdTo");
  }
  // IDofRelated = myResponse;

  // String [] IDlist = new String [response.result.length];
  //   for(int i=0; i<response.result.length; i++){
  //     IDlist [i] = response.result[i].responseContent.docs[0].id;
  //   }
    return IDlist;
  }

  public static String IDtoName(String idToBeConverted){
    String textBookTitle = "";

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
  .url("http://hackingedu.chegg.com/hacking-edu/catalog/priced/byId/" + idToBeConverted)
  .get()
  .addHeader("accept", "application/json")
  .addHeader("content-type", "application/json")
  .addHeader("authorization", "Basic TXlPYUhWOUFtN01QdmxuSmJVVVV2SXZyaVBnYmVlQUE6MG1zQVFWbk1wemNS MXNjNA==")
  .addHeader("cache-control", "no-cache")
  .addHeader("postman-token", "1cfc968c-0021-3d99-4dc7-e674d33db19c")
  .build();

  // Response response = client.newCall(request).execute();

  Response response =null;

  try{
    response = client.newCall(request).execute();
    System.out.println(response.toString());
  }catch(Exception e){
    System.out.println(e);
  }
  String myResponse = "";
  try{
    myResponse = response.body().string();
  }catch(Exception e){
    System.out.println(e);
  }
  System.out.println("MY RESPONSE " + myResponse);
  Object obj = JSONValue.parse(myResponse);
  JSONObject object=(JSONObject)obj;
  System.out.println("IDtoText: " + object.toString());
  textBookTitle = (String)(object.get("title"));


  //textBookTitle = response.title;
  return textBookTitle;
  }

}
