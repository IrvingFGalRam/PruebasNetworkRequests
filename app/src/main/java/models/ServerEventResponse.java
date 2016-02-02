package models;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerEventResponse {
    Evento event;


    //Parseando la respuesta
    public static ServerEventResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, ServerEventResponse.class);
    }

    public Evento getEvent(){
        return this.event;
    }
}
