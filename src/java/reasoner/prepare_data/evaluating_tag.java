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
public class evaluating_tag {
    private String tag_name;
    private float evaluating_point;
    
    public evaluating_tag(String tag_name, float point){
        this.tag_name = tag_name;
        evaluating_point = point;
    }
    
    public String get_name(){
        return tag_name;
    }
    
    public void set_name(String name){
        tag_name = name;
    }
    
    public void set_point(int point){
        evaluating_point = point;
    }
    
    public float get_point(){
        return evaluating_point;
    }
    
    public void add_point(int point){
        evaluating_point += point;
    }
}
