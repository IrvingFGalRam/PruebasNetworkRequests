package models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    /** Lista de programas del evento (por día) */
    @SerializedName("program")
    List<Program> dayProgramList;
    /**
     * TODO Hacer todas las clases publicas
     */

    // public constructor is necessary for collections
    public Evento() {
        organizerList = new ArrayList<Organizer>();
        sponsorList = new ArrayList<Sponsor>();
        subjectList = new ArrayList<Subject>();
        dayProgramList = new ArrayList<Program>();
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getStartDate(){
        return this.startDate;
    }
    public String getEndDate(){
        return this.endDate;
    }
    public int getSpan(){
        return this.span;
    }
    public List<Organizer> getOrganizerList(){
        return this.organizerList;
    }
    public List<Sponsor> getSponsorList(){
        return this.sponsorList;
    }
    public String getDescription(){
        return this.description;
    }
    public List<Subject> getSubjectList(){
        return this.subjectList;
    }
    public Map getMap(){
        return this.map;
    }
    public List<Program> getDayProgramList() {
        return this.dayProgramList;
    }

    class Organizer{
        String name;
        @SerializedName("oURL")
        String link;

        public String getName() {
            return this.name;
        }
        public String getLink() {
            return this.link;
        }
    }

    class Sponsor{
        @SerializedName("sName")
        String name;
        @SerializedName("sURL")
        String link;
        @SerializedName("sLogo")
        String linkLogo;

        public String getName() {
            return this.name;
        }
        public String getLink() {
            return this.link;
        }
        public String getLinkLogo() {
            return this.linkLogo;
        }
    }

    class Subject{
        String tag;

        public String getTag() {
            return this.tag;
        }
    }

    class Map{
        /** El mapa
         * Se relaciona directamente con el contenido de subjectList
         */
        @SerializedName("fixedAreas")
        ConsAreas consAreas;
        @SerializedName("generalArea")
        List<ExpoArea> expoAreaList;

        public Map(){
            expoAreaList = new ArrayList<ExpoArea>();
        }

        public ConsAreas getConsAreas() {
            return this.consAreas;
        }
        public List<ExpoArea> getExpoAreaList() {
            return this.expoAreaList;
        }

        class ConsAreas{
            /** Las salas que no cambian de lugar en el CEC
             * area_1 -> sala 1
             * area_2 -> sala 2
                     *
                     *
             * area_7 -> sala 7
             * El número que pase es el tema del 'Área' (dependiendo de la posición en subjectList)
             * -1 significa que no aplica (no se usará dicha sala)
             */
            int area_1;
            int area_2;
            int area_3;
            int area_4;
            int area_5;
            int area_6;
            int area_7;

            public int getArea_1() {
                return this.area_1;
            }
            public int getArea_2() {
                return this.area_2;
            }
            public int getArea_3() {
                return this.area_3;
            }
            public int getArea_4() {
                return this.area_4;
            }
            public int getArea_5() {
                return this.area_5;
            }
            public int getArea_6() {
                return this.area_6;
            }
            public int getArea_7() {
                return this.area_7;
            }

        }
        public class ExpoArea{
            int subject;
            int id;
            @SerializedName("X")
            int x;
            @SerializedName("Y")
            int y;
            int spanX;
            int spanY;

            public int getSubject() {
                return this.subject;
            }
            public int getId() {
                return this.id;
            }
            public int getX() {
                return this.x;
            }
            public int getY() {
                return this.y;
            }
            public int getSpanX() {
                return this.spanX;
            }
            public int getSpanY() {
                return this.spanY;
            }
        }
    }

    public class Program{
        int day;
        String date;
        String span;
        @SerializedName("activities")
        List<Activity> activityList;

        public Program(){
            activityList = new ArrayList<Activity>();
        }

        public int getDay() {
            return this.day;
        }
        public String getDate() {
            return this.date;
        }
        public String getSpan() {
            return this.span;
        }
        public List<Activity> getActivityList() {
            return this.activityList;
        }

        public class Activity{
            @SerializedName("expoName")
            String name;
            String span;
            @SerializedName("location")
            int stand;

            public String getName() {
                return this.name;
            }
            public String getSpan() {
                return this.span;
            }
            public int getStand() {
                return this.stand;
            }
        }
    }
}
