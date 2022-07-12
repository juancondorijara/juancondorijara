package controller;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.Titanic;
import org.json.JSONObject;
import org.json.JSONException;
//import org.primefaces.json.JSONObject;
import services.Servicio;
import lombok.Data;

@Data
@Named(value = "titanicC")
@SessionScoped
public class TitanicC implements Serializable {

    Titanic titanic;

    public TitanicC() {
        titanic = new Titanic();
        titanic.setID(1);
        titanic.setSOBREVIVENCIA(0);
        titanic.setCLASE(3);
        titanic.setNOMBRE("Braund, Mr. Owen Harris");
        titanic.setSEXO("male");
        titanic.setEDAD(22);
    }

    public  void obtenerDatosTitanic() throws IOException, InterruptedException {
        try {            
            JSONObject cadenaJson = Servicio.obtenerJSonTitanic(titanic);  
            titanic.setSCOREDLABELS(cadenaJson.getInt("Scored Labels"));
            titanic.setCATEGORIA(cadenaJson.getString("Scored Labels"));
            titanic.setSCOREDPROBABILITIES1(cadenaJson.getDouble("Scored Probabilities for Class \"0\""));
            titanic.setSCOREDPROBABILITIES2(cadenaJson.getDouble("Scored Probabilities for Class \"1\""));
            if (titanic.getCATEGORIA().equals("1")) {
                titanic.setRESULTADO("Que bien, Sobrevivio al Titanic");
            } else {
                titanic.setRESULTADO("Lo sentimos, según nuestro modelo no sobrevivio al Titanic");
            }
        } catch (Exception e) {
            System.out.println("Error en obtenerDatosTitanic: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
