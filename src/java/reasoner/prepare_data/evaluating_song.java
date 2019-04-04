/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner.prepare_data;

import java.util.ArrayList;

/**
 *
 * @author HuyLy
 */
public class evaluating_song {
    private String title;
    private String track_id;
    private ArrayList<String> artist;
    private ArrayList<String> tag;
    private float evaluating_point;
    
    public evaluating_song(String track_id, float point){
        this.track_id = track_id;
        evaluating_point = point;
        artist = new ArrayList();
        tag = new ArrayList();
    }

    public void setTitle(String title) {
        this.title = title.replace("_", " ");
    }

    public String getTitle() {
        return title;
    }
    
    public boolean has_tag(){
        return !tag.isEmpty();
    }
    
    public boolean has_artist(){
        return !artist.isEmpty();
    }
    
    public void add_tag(String tag){
        this.tag.add(tag);
    }
    
    public void set_tag(ArrayList<String> tag){
        this.tag = tag;
    }
    
    public ArrayList<String> get_tag(){
        return tag;
    }
    
    public void add_artist(String artist_name){
        this.artist.add(artist_name);
    }
    
    public void set_artist(ArrayList<String> artists){
        this.artist = artists;
    }
    
    public ArrayList<String> get_artist(){
        return artist;
    }
    
    public String get_display_artist(){
        String artists = "";
        for(Object obj : artist){
            artists += obj.toString().replace("_", " ") + ", ";
        }
        return artists.substring(0, artists.length()-2);
    }
    
    public String get_track_id(){
        return track_id;
    }
    
    public void set_track_id(String track_id){
        this.track_id = track_id;
    }
    
    public float get_point(){
        return evaluating_point;
    }
    
    public void set_point(float evaluating_point){
        this.evaluating_point = evaluating_point;
    }
    
    public void add_point(float point){
        evaluating_point += point;
    }
}
