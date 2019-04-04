/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner.prepare_data;

import reasoner.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author HuyLy
 */
public class evaluating_song_list {
    private ArrayList<evaluating_song> list;
    
    public evaluating_song_list(){
        list = new ArrayList();
    }
    
    public boolean remove_all(){
        return list.removeAll(list);
    }
    
    public boolean add_song(evaluating_song e_song){
        return list.add(e_song);
    }
    
    public ArrayList<evaluating_song> get_list(){
        return list;
    }
    
    public void set_list(ArrayList<evaluating_song> list){
        this.list = list;
    }
    
    public boolean delete(evaluating_song e_song){
        return list.remove(e_song);
    }
    
    
    public boolean delete_listened_songs(Collection<history> histories){
        if(histories == null) return false;
        for(history history : histories){
            for (Iterator<evaluating_song> iterator = list.iterator(); iterator.hasNext(); ) {
                evaluating_song e_song = iterator.next();
                String track_id = (history.getLocalName().split("_"))[1];
                if(track_id.equals(e_song.get_track_id())) {
                    iterator.remove();
                }
            }
        }
        return true;
    }
    
    public int size(){
        return list.size();
    }
    
    public void sort_descending(){
        ArrayList<evaluating_song> sorted_list = new ArrayList();
        while(!list.isEmpty()){
//        while(sorted_list.size() < 9) {
            evaluating_song max = list.get(0);
            for(evaluating_song s : list){
                if(s.get_point() > max.get_point()){
                    max = s;
                }
            }
            sorted_list.add(max);
            delete(max);
        }
        set_list(sorted_list);
    }
}
