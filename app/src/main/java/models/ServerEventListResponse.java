package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ServerEventListResponse {
    /**el Json manda { "events": [ ...]} pero por tenerlo claro llamo a la lista de eventos eventList
     * haciendo el @SerializedName("events") 'iguala' eventList->events
     */
    @SerializedName("events")
    List<EventoDeLista> eventList;

    // public constructor is necessary for collections
    public ServerEventListResponse() {
        eventList = new ArrayList<EventoDeLista>();
    }

    //Parseando la respuesta
    public static ServerEventListResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        ServerEventListResponse serverEventListResponse = gson.fromJson(response, ServerEventListResponse.class);
        /**Lo pongo así para tenerlo más claro, sin embargo podría reducirse a:
         * return gson.fromJson(response, ServerEventListResponse.class);*/
        return serverEventListResponse;
    }

    public List<EventoDeLista> getEventList(){
        return this.eventList;
    }
}
