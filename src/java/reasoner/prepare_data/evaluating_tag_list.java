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
public class evaluating_tag_list {
    private ArrayList<evaluating_tag> list;
    
    public evaluating_tag_list(){
        list = new ArrayList();
    }
    
    public boolean remove_all(){
        return list.removeAll(list);
    }
    
    public void add_tag(evaluating_tag e_tag){
        list.add(e_tag);
    }
    
    public ArrayList<evaluating_tag> get_list(){
        return list;
    }
}
