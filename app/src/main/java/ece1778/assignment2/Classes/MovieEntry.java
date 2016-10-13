package ece1778.assignment2.Classes;


import java.io.Serializable;

public class MovieEntry implements Serializable{
    public String Title;
    public String Actor;
    public int Year;

    public MovieEntry(String Title, String Actor, int Year){
        this.Title = Title;
        this.Actor = Actor;
        this.Year = Year;
    }
}
