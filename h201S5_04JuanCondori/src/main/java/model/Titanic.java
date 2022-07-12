package model;

import lombok.Data;

@Data

// AzureML
public class Titanic {
    
    double confidence;
    String category;
    double credits;
    double probability;
    String result;
    
    int SCOREDLABELS;
    Double SCOREDPROBABILITIES1;
    Double SCOREDPROBABILITIES2;
    String RESULTADO;
    String CATEGORIA;
    
    int ID;            //ID
    int SOBREVIVENCIA; //SOBREVIVENCIA 1=SI, 0=NO
    int CLASE;         //CLASE 1,2,3
    String NOMBRE;     //NOMBRE
    String SEXO;       //SEXO
    int EDAD;          //EDAD
    
    int PassengerId;   //ID
    int Survived;      //SOBREVIVENCIA 1=SI, 0=NO
    int Pclass;        //CLASE 1,2,3
    String name;       //NOMBRE
    String sex;        //SEXO
    String age;        //EDAD
    
//CAMPOS JAVA
//PassengerId
//Survived
//Pclass
//Name
//Sex
//Age    
}
