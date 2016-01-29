package models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Evento {
    int id;
    String name;
    String startDate;
    String endDate;
    int span;
    @SerializedName("organizers")
    List<Organizer> organizerList;
    @SerializedName("sponsors")
    List<Sponsor> sponsorList;
    String description;
    @SerializedName("subjects")
    List<Subject> subjectList;
    Map map;
    @SerializedName("program")
    List<Program> dayListProgram;
    /**
     * TODO Terminar el modelo de evento (Apoyate en la clase EventoDeLista y ServerEventListResponse)
     */

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getStartDate(){
        return startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public int getSpan(){
        return span;
    }
    public Organizer getOrganizers
    public String getDescription(){
        return description;
    }

    class Organizer{

    }

    class Sponsor{

    }

    class Subject{

    }

    class Map{

    }

    class Program{

    }
}
