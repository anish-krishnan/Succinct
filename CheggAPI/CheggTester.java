public class CheggTester{
  String [] relatedBooks;
  public CheggTester(String bookName){
    textBook = bookName;
  }

  public run(String textBook){
      textBookID = textToID(textBook);
      int listLength  = findSimilar(textBookID);
      String [] myIDList = new String [listlength];
      for(int i=0; i<listLength; i++){
        relatedBooks [i] = IDtoName(myIDList[i]);
      }
  }

  public String textToID(){
    String ID = "";

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
      .url("https://hackingedu.chegg.com/hacking-edu/search?q=" + textToSearch)
      .get()
      .addHeader("accept", "application/json")
      .addHeader("authorization", "Basic TXlPYUhWOUFtN01QdmxuSmJVVVV2SXZyaVBnYmVlQUE6MG1zQVFWbk1wemNS MXNjNA==")
      .addHeader("cache-control", "no-cache")
      .addHeader("postman-token", "46e822fd-4a38-3db0-b60b-4bb26491f121")
      .build();

    Response response = client.newCall(request).execute();

    String myResponse = response.result[0].responseContent.docs[0];
    ID = myResponse.id;
    return ID;



  }

  public void findSimilar(idToGetRelated){
    String IDofRelated = "";
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
  .url("https://hackingedu.chegg.com/hacking-edu/catalog/relateditem/priced/getRelatedItems?catalogItemId=" + idToGetRelated)
  .get()
  .addHeader("accept", "application/json")
  .addHeader("content-type", "application/json")
  .addHeader("authorization", "Basic TXlPYUhWOUFtN01QdmxuSmJVVVV2SXZyaVBnYmVlQUE6MG1zQVFWbk1wemNS MXNjNA==")
  .addHeader("cache-control", "no-cache")
  .addHeader("postman-token", "2b21d3c9-023b-519e-4681-a7e20281879e")
  .build();

  Response response = client.newCall(request).execute();
  String myResponse = response.result[0].responseContent.docs[0];
  IDofRelated = myResponse.id;
  String [] IDlist = new String [response.result.length];
    for(int i=0; i<response.result.length; i++){
      IDlist [i] = response.result[i].responseContent.docs[0].id;
    }
    return IDList;
  }

  public void IDtoName(String idToBeConverted){
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

  Response response = client.newCall(request).execute();

  textBookTitle = response.title;
  return textBookTitle;

  }
