package models;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServerEventResponse {
    @SerializedName("events")
    List<EventoDeLista> eventList;

    // public constructor is necessary for collections
    public ServerEventResponse() {
        eventList = new ArrayList<EventoDeLista>();
    }

    //Parseando la respuesta
    public static ServerEventResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        ServerEventResponse serverEventResponse = gson.fromJson(response, ServerEventResponse.class);
        return serverEventResponse;
    }

    public List<EventoDeLista> getEventList(){
        return this.eventList;
    }
}
