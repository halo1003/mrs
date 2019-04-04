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
public class evaluating_artist_list {
    private ArrayList<evaluating_artist> list;
    
    public evaluating_artist_list(){
        list = new ArrayList();
    }
    
    public boolean remove_all(){
        return list.removeAll(list);
    }
    
    public void add_artist(evaluating_artist e_artist){
        list.add(e_artist);
    }
    
    public ArrayList<evaluating_artist> get_list(){
        return list;
    }
}
