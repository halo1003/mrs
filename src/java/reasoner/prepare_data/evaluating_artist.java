/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner.prepare_data;

/**
 *
 * @author HuyLy
 */
public class evaluating_artist {
    private String artist_name;
    private float evaluating_point;
    
    public evaluating_artist(String artist_name, int point){
        this.artist_name = artist_name;
        evaluating_point = point;
    }
    
    public String get_name(){
        return artist_name;
    }
    
    public void set_name(String artist_name){
        this.artist_name = artist_name;
    }
    
    public float get_point(){
        return evaluating_point;
    }
    
    public void set_point(int evaluating_point){
        this.evaluating_point = evaluating_point;
    }
    
    public void add_point(int point){
        evaluating_point += point;
    }
    
}
