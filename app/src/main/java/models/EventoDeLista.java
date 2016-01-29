package models;


import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EventoDeLista {
    String name;
    String startDate;

    public String getName(){
        return name;
    }

    public String getStartDate(){
        return startDate;
    }

    /*
    private Event evento;

    public Event getEvento(){
        return this.evento;
    }

    // Decodes EventoDeLista json into business model object
    public static EventoDeLista fromJson(JSONObject jsonObject) {
        EventoDeLista lde = new EventoDeLista();
        // Deserialize json into object fields
        try {
            lde.evento = jsonObject.getJSONObject("event");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return lde;
    }

    class Event{
        private String name;
        private String startDate;

        public String getName(){
            return this.name;
        }

        public String getStartDate(){
            return this.startDate;
        }
    }*/
}
