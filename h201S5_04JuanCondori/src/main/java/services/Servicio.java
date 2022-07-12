package services;

import com.google.gson.Gson;
import java.io.IOException;
import model.Titanic;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.json.JSONException;
//import org.primefaces.json.JSONException;
//import org.primefaces.json.JSONObject;

// AzureML
//    Request-Response
//    /execute?api-version=2.0&format=swagger   
public class Servicio {

    public static JSONObject obtenerJSonTitanic(Titanic modelo) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"Inputs\": {\r\n    \"input1\": [\r\n      {\r\n        \"PassengerId\":" + modelo.getID() + ",\r\n"
                + "\"Survived\":" + modelo.getSOBREVIVENCIA() + ",\r\n"
                + "\"Pclass\":" + modelo.getCLASE() + ",\r\n"
                        + "\"Name\": \"Braund, Mr. Owen Harris\",\r\n"
                        + "\"Sex\": \"male\",\r\n"
                        + "\"Age\":" + modelo.getEDAD() + ",\r\n"
                        + "\"SibSp\": 0,\r\n        \"Parch\": 0,\r\n        \"Ticket\": \"\",\r\n        \"Fare\": 0,\r\n        \"Cabin\": \"\",\r\n        \"Embarked\": \"\"\r\n      }\r\n    ]\r\n  },\r\n  \"GlobalParameters\": {}\r\n}\r\n");
        Request request = new Request.Builder()
                .url("https://ussouthcentral.services.azureml.net/workspaces/6b32e2f918884ee1aa9106acf3965558/services/8a9e015a8f5a458f9493d15cb2678888/execute?api-version=2.0&format=swagger")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 41YASIvz9sIdk3y8uZFDU2p/X/9s63SQ3Yirr7CXrAdI6KlVtS0cJOZ9rARiBxJKVoDzebCraMOI5l/PCfkPdA==")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        Gson gson = new Gson();
        // Convierte la cadena body en un objeto jsonObject
        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONObject cadenaJson = jsonObject.getJSONObject("Results").getJSONArray("output1").getJSONObject(0);
        return cadenaJson;
    }

    public static JSONObject obtenerJSonTitanic3() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"Inputs\": {\r\n    \"input1\": [\r\n      {\r\n        \"PassengerId\": 1,\r\n        \"Survived\": 0,\r\n        \"Pclass\": 3,\r\n        \"Name\": \"Braund, Mr. Owen Harris\",\r\n        \"Sex\": \"male\",\r\n        \"Age\": 22,\r\n        \"SibSp\": 0,\r\n        \"Parch\": 0,\r\n        \"Ticket\": \"\",\r\n        \"Fare\": 0,\r\n        \"Cabin\": \"\",\r\n        \"Embarked\": \"\"\r\n      }\r\n    ]\r\n  },\r\n  \"GlobalParameters\": {}\r\n}\r\n");
        Request request = new Request.Builder()
                .url("https://ussouthcentral.services.azureml.net/workspaces/6b32e2f918884ee1aa9106acf3965558/services/8a9e015a8f5a458f9493d15cb2678888/execute?api-version=2.0&format=swagger")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 41YASIvz9sIdk3y8uZFDU2p/X/9s63SQ3Yirr7CXrAdI6KlVtS0cJOZ9rARiBxJKVoDzebCraMOI5l/PCfkPdA==")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        Gson gson = new Gson();
        // Convierte la cadena body en un objeto jsonObject
        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONObject cadenaJson = jsonObject.getJSONObject("Results").getJSONArray("output1").getJSONObject(0);
        return cadenaJson;
    }

    public static void main(String[] args) throws IOException {
        try {
            Titanic titanic = new Titanic();
            titanic.setID(1);
            titanic.setSOBREVIVENCIA(0);
            titanic.setCLASE(3);
//            titanic.setNOMBRE("Braund, Mr. Owen Harris");
//            titanic.setSEXO("male");
            titanic.setEDAD(22);
            System.out.println("Lista = " + Servicio.obtenerJSonTitanic(titanic));
            JSONObject cadenaJson = Servicio.obtenerJSonTitanic(titanic);
            System.out.println("Scored = " + cadenaJson.getInt("Scored Labels"));
            System.out.println("Scored Probabilities for Class 0 = " + cadenaJson.getDouble("Scored Probabilities for Class \"0\""));
            System.out.println("Scored Probabilities for Class 1 = " + cadenaJson.getDouble("Scored Probabilities for Class \"1\""));

//            FUNCIONA SIN MODELO
//            System.out.println("Lista " + Servicio.obtenerJSonTitanic3());
//            JSONObject cadenaJson = Servicio.obtenerJSonTitanic3();
//            System.out.println("Scored = " + cadenaJson.getInt("Scored Labels"));
//            System.out.println("Scored Probabilities for Class 0 = " + cadenaJson.getDouble("Scored Probabilities for Class \"0\""));
//            System.out.println("Scored Probabilities for Class 1 = " + cadenaJson.getDouble("Scored Probabilities for Class \"1\""));
        } catch (Exception e) {
            System.out.println("error en Servicio main" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
