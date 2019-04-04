/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import reasoner.prepare_data.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HuyLy
 */
public class Reasoner {

    private evaluating_artist_list evaluating_artist_list = new evaluating_artist_list();
    private evaluating_tag_list evaluating_tag_list = new evaluating_tag_list();
    private evaluating_song_list evaluating_song_list = new evaluating_song_list();
    private MyFactory history_factory;
    private MyFactory artist_factory;
    private MyFactory evaluation_factory;
    private MyFactory tag_factory;
    private MyFactory user_factory;
    private user user = null;

    public Reasoner(MyFactory history_factory, MyFactory artist_factory, MyFactory evaluation_factory, MyFactory tag_factory, MyFactory user_factory) {
        this.history_factory = history_factory;
        this.artist_factory = artist_factory;
        this.evaluation_factory = evaluation_factory;
        this.tag_factory = tag_factory;
        this.user_factory = user_factory;
    }

    public void logout() {
        user = null;
    }

    public String get_user_id() {
        if (user == null) {
            return null;
        }
//        if(user.getLocalName().length() >= 10)
//            return (user.getLocalName()).substring(0,10);
        return user.getLocalName();
    }

    public void remove_all_records() {
        evaluating_song_list.remove_all();
        evaluating_tag_list.remove_all();
        evaluating_artist_list.remove_all();
    }

    public ArrayList<evaluating_song> get_recommended_songs() {
        remove_all_records();
        try {
            prepare_evaluation(get_history(user.getLocalName()));
            return evaluating_song_list.get_list();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean login(String user_id) {
//        String ID = "u257c6f34bfdc6153204849499d7a9313841ad590";
        user = user_factory.getuser(user_id);
        if (user != null) {
            return true;
        }
        return false;
    }

    public boolean register(String user_id) {
        user = user_factory.getuser(user_id);
        if (user == null) {
            user = user_factory.createuser(user_id);
            return true;
        }
        return false;
    }

    
    List<Integer> liValue = new ArrayList<>();
    public List<String> get_data_from_url(String user) throws MalformedURLException, IOException, JSONException {

        URL url = new URL("http://localhost:8090/cluster/uid=" + user);
        List<String> li = new ArrayList<String>();
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        JSONArray jsonarray = null;
        while ((inputLine = in.readLine()) != null) {
            jsonarray = new JSONArray(inputLine.toString());
        }

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            jsonarray = new JSONArray("[" + jsonobject.getString("recommendation_list") + "]");
            for (int j = 0; j < jsonarray.length(); j++) {
                jsonobject = jsonarray.getJSONObject(i);
                Iterator<String> keys = jsonobject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE, key+":"+jsonobject.getString(key));
                    li.add(key);
                    liValue.add(Integer.parseInt(jsonobject.getString(key)));
                }
                
            }
        }

        in.close();

        return null;
    }
    
    List<Integer> liHistory_Value = new ArrayList<>();
    public List<String> get_history_from_url(String user) throws MalformedURLException, IOException, JSONException {

        URL url = new URL("http://localhost:8090/cluster/uid_history=" + user);
        List<String> li = new ArrayList<String>();
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        JSONArray jsonarray = null;
        while ((inputLine = in.readLine()) != null) {
            jsonarray = new JSONArray(inputLine.toString());
        }

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            jsonarray = new JSONArray("[" + jsonobject.getString("recommendation_list") + "]");
            for (int j = 0; j < jsonarray.length(); j++) {
                jsonobject = jsonarray.getJSONObject(i);
                Iterator<String> keys = jsonobject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE, key+":"+jsonobject.getString(key));
                    li.add(key);
                    liHistory_Value.add(Integer.parseInt(jsonobject.getString(key)));
                }
                
            }
        }

        in.close();

        return null;
    }

    public boolean prepare_evaluation(Collection<history> histories) { // Thay the hítories collection = out put cua mì 
        List<String> track_ids = new ArrayList<>();
        if (histories == null) {
            return false;
        }
        String uid = get_user_id();

        Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE, uid);
        try {

            if (uid != null) {
                track_ids = get_data_from_url(uid);
            }

        } catch (IOException ex) {
            Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (history history : histories) {
//            Logger.getLogger(Reasoner.class.getName()).log((LogRecord) history);
            int point = history.getListening_times();
//            if (history.getLike() == 1) {
//                point += 10;
//            }
            // Get track_id
            String track_id = (history.getLocalName().split("_"))[1];
            Logger.getLogger(Reasoner.class.getName()).log(Level.SEVERE,"[]"+ track_id+":"+point+" : "+history.getLocalName());
            // Add evaluating_artist to evaluating_valuating_list
            add_evaluating_artist((artist_factory.getsong(track_id)).getPresented_by(), point);
            // Tag
            add_evaluating_tag((tag_factory.getsong(track_id)).getBelongs(), point);
            // Similar song
            add_evaluating_song_for_similar_song((artist_factory.getsong(track_id)).getSimilar(), track_id, point);
        }
        
        int temp = 0;
        for (String track_id : track_ids) {
            int point = liValue.get(temp++) * 10;
            add_evaluating_artist((artist_factory.getsong(track_id)).getPresented_by(), point);
            // Tag
            add_evaluating_tag((tag_factory.getsong(track_id)).getBelongs(), point);
            // Similar song
            add_evaluating_song_for_similar_song((artist_factory.getsong(track_id)).getSimilar(), track_id, point);
        }

        evaluating_song_list.delete_listened_songs(histories);

        set_attributes_for_each_song();
        compute_evaluating_point();

        evaluating_song_list.sort_descending();
        return true;
    }

    public Collection<history> get_history(String user_id) {
        // Get listening history of each song. [without dislike]
        user user = history_factory.getuser(user_id);
        if (!user.hasHas_history()) {
            return null;
        }
        Collection<history> histories = user.getHas_history();
        Collection<history> return_histories = new ArrayList<>();
        // Ignore All songs which was disliked [like = -1]
        for (history history : histories) {
            if (history.getLike() != -1) {
                return_histories.add(history);
            }
        }
        return return_histories;
    }

    public void add_evaluating_song(Collection song_collection, int point) {
        Collection<song> songs = song_collection;
        for (song song : songs) {
            boolean existance = false;
            for (evaluating_song e_song : evaluating_song_list.get_list()) {
                if ((song.getLocalName()).equals(e_song.get_track_id())) {
                    e_song.add_point(point);
                    existance = true;
                    break;
                }
            }
            if (existance == false) {
                evaluating_song_list.add_song(new evaluating_song(song.getLocalName(), point));
            }
        }
    }

    public void add_evaluating_song_for_similar_song(Collection song_collection, String track_id, int point) {
        // track_id : 1 bai hat
        // song_collection: list of similar songs of bai hat tren
        // tu` track_id + 1 similar song => 1 evaluation : e
        //=> add similar song zo evaluating_song_list with: point + e.point()
        Collection<song> songs = song_collection;
        for (song song : songs) {
            evaluation e = evaluation_factory.getevaluation(song.getLocalName() + "_" + track_id);
            if (e == null) {
                e = evaluation_factory.getevaluation(track_id + "_" + song.getLocalName());
            }
            boolean existance = false;
            for (evaluating_song e_song : evaluating_song_list.get_list()) {
                if ((song.getLocalName()).equals(e_song.get_track_id())) {
                    e_song.add_point(e.getPoint() + point);
                    existance = true;
                    break;
                }
            }
            if (existance == false) {
                evaluating_song_list.add_song(new evaluating_song(song.getLocalName(), e.getPoint() + point));
            }
        }
    }

    public void add_evaluating_artist(Collection artist_collection, int point) {
        Collection<artist> artists = artist_collection;
        for (artist artist : artists) {
            boolean existance = false;
            // Check the existance of artist in evaluating_artist_list
            // If Artist already exist in the list => point += listening_times
            // Else creating new evaluation.
            for (evaluating_artist e_artist : evaluating_artist_list.get_list()) {
                if ((artist.getLocalName()).equals(e_artist.get_name())) {
                    e_artist.add_point(point);
                    existance = true;
                    break;
                }
            }
            if (existance == false) {
                // Create new evaluating_artist and add to evaluating_artist_list
                evaluating_artist_list.add_artist(
                        new evaluating_artist(artist.getLocalName(), point));
            }
            add_evaluating_song(artist.getPresents(), 0);
        }
    }

    public void add_evaluating_tag(Collection tag_collection, int point) {
        Collection<tag> tags = tag_collection;
        for (tag tag : tags) {
            boolean existance = false;
            for (evaluating_tag e_tag : evaluating_tag_list.get_list()) {
                if (tag.getLocalName().equals(e_tag.get_name())) {
                    e_tag.add_point(point);
                    existance = true;
                }
            }
            if (existance == false) {
                evaluating_tag_list.add_tag(
                        new evaluating_tag(tag.getLocalName(), point));
            }
            add_evaluating_song(tag.getIncludes(), 0);
        }
    }

    public void set_attributes_for_each_song() {
        for (evaluating_song e_song : evaluating_song_list.get_list()) {
            song s = artist_factory.getsong(e_song.get_track_id());
            Collection<artist> artists = s.getPresented_by();
            for (artist a : artists) {
                e_song.add_artist(a.getLocalName());
            }
            s = tag_factory.getsong(e_song.get_track_id());
            Collection<tag> tags = s.getBelongs();
            for (tag t : tags) {
                e_song.add_tag(t.getLocalName());
            }
            e_song.setTitle(user_factory.getsong(e_song.get_track_id()).getTitle());
        }
    }

    public void compute_evaluating_point() {
        for (evaluating_song s : evaluating_song_list.get_list()) {
            for (evaluating_artist artist : evaluating_artist_list.get_list()) {
                for (String a : s.get_artist()) {
                    if (a.equals(artist.get_name())) {
                        s.add_point(artist.get_point());
                    }
                }
            }

            for (evaluating_tag tag : evaluating_tag_list.get_list()) {
                for (String t : s.get_tag()) {
                    if (t.equals(tag.get_name())) {
                        s.add_point(tag.get_point());
                    }
                }
            }
        }
    }

    public ArrayList<song> get_top(int n) {
        // Get # songs which have highest listening times.
        // create hotest song = null and compare the listening times of
        // hosted song with all song in system.
        // If hosted song listening times < song listening times
        // => check the song is already existed in arraylist top or not.
        // If not => add this song to array list top
        // Else => continue with others.
        Collection<song> songs = user_factory.getAllsongInstances();
        ArrayList<song> top = new ArrayList<>();
        while (top.size() < n) {
            song hotest_song = null;
            int max = 0;
            for (song s : songs) {
                if (max < s.getListening_times()) {
                    boolean similar = false;
                    for (song top_song : top) {
                        similar = top_song.getLocalName().equals(s.getLocalName());
                        if (similar == true) {
                            break;
                        }
                    }
                    if (similar == true) {
                        continue;
                    } else {
                        max = s.getListening_times();
                        hotest_song = s;
                    }
                }
            }
            top.add(hotest_song);
        }
        return top;
    }

    public String get_artist_name_from_track_id(String track_id) {
        Collection<artist> artists = artist_factory.getsong(track_id).getPresented_by();
        String artist_name = "";
        for (artist a : artists) {
            artist_name += get_display_name(a.getLocalName()) + ", ";
        }
        return artist_name.substring(0, artist_name.length() - 2);
    }

    public String get_tag_name_from_track_id(String track_id) {
        Collection<tag> tags = tag_factory.getsong(track_id).getBelongs();
        String tag_name = "";
        for (tag t : tags) {
            tag_name += get_display_name(t.getLocalName()) + ", ";
        }
        if (tag_name == "") {
            return tag_name;
        }
        return tag_name.substring(0, tag_name.length() - 2);
    }

    public Collection<artist> get_arists() {
        Collection<artist> artists = artist_factory.getAllartistInstances();
        return artists;
    }

    public String get_display_name(String name) {
        return name.replace("_", " ");
    }

    public Collection<tag> get_tags() {
        Collection<tag> tags = tag_factory.getAlltagInstances();
        return tags;
    }

    public Collection<history> get_histories() {
        user user = history_factory.getuser(this.user.getLocalName());
        Collection<history> histories = user.getHas_history();
        return histories;
    }

    public song get_song_from_track_id(String track_id) {
        return user_factory.getsong(track_id);
    }

    public Collection<song> get_song_from_tag_id(String tag_id) {
        tag t = tag_factory.gettag(tag_id);
        Collection<song> songs = t.getIncludes();
        return songs;
    }

    public Collection<song> get_song_from_artist_id(String artist_id) {
        artist a = artist_factory.getartist(artist_id);
        Collection<song> songs = a.getPresents();
        return songs;
    }

    public Collection<song> get_song_from_search(String song_name) {
        Collection<song> songs = user_factory.getAllsongInstances();
        Collection<song> return_songs = new ArrayList<>();
        System.out.println("____________ " + song_name);
        for (song song : songs) {
            System.out.println("- " + song.getTitle().replace("_", " ").toLowerCase());
            System.out.println(". " + song.getTitle().replace("_", " ").toLowerCase().equals(song_name.toLowerCase()));
            if (song.getTitle().replace("_", " ").toLowerCase().equals(song_name.toLowerCase())) {
                return_songs.add(song);
            }
        }
        for (song song : return_songs) {
            System.out.println("- " + song.getTitle().replace("_", " ").toLowerCase());
        }
        return return_songs;
    }

    public String get_song_title_from_track_id(String track_id) {
        return user_factory.getsong(track_id).getTitle();
    }

    public void increase_listening_times(String track_id) {
        song song = user_factory.getsong(track_id);
        song.setListening_times(song.getListening_times() + 1);
        if (user != null && history_factory.gethistory(this.user.getLocalName() + "_" + track_id) == null) {
            crease_history(track_id);
        }
    }

    public void check_like(String track_id, String type) {
        history history = history_factory.gethistory(this.user.getLocalName() + "_" + track_id);
        String like_type = get_like(track_id);
        if ("like".equals(type)) {
            if ("liked".equals(like_type)) {
                history.setLike(0);
                compute_like_times(track_id, "-", "like");
            } else {
                if ("disliked".equals(like_type)) {
                    compute_like_times(track_id, "-", "dislike");
                }
                history.setLike(1);
                compute_like_times(track_id, "+", "like");
            }
        } else {
            if ("disliked".equals(like_type)) {
                history.setLike(0);
                compute_like_times(track_id, "-", "dislike");
            } else {
                if ("liked".equals(like_type)) {
                    compute_like_times(track_id, "-", "like");
                }
                history.setLike(-1);
                compute_like_times(track_id, "+", "dislike");
            }
        }
    }

    public void crease_history(String track_id) {
        history history = history_factory.createhistory(this.user.getLocalName() + "_" + track_id);
        history.setListening_times(1);
        history.setLike(0);
        if (history_factory.getuser(this.user.getLocalName()) == null) {
            history_factory.createuser(this.user.getLocalName());
        }
        history_factory.getuser(this.user.getLocalName()).addHas_history(history);
        user.addListens(user_factory.getsong(track_id));
    }

    private void compute_like_times(String track_id, String type, String like_type) {
        song song = user_factory.getsong(track_id);
        if (like_type.equals("like")) {
            int times = 1;
            if (type.equals("+")) {
                times = song.getLike() + 1;
            } else {
                times = song.getLike() - 1;
            }
            song.setLike(times);
        } else {
            int times = -1;
            if (type.equals("+")) {
                times = song.getDislike() + 1;
            } else {
                times = song.getDislike() - 1;
            }
            song.setDislike(times);
        }
    }

    public String get_like(String track_id) {
        history history = history_factory.gethistory(this.user.getLocalName() + "_" + track_id);
        switch (history.getLike()) {
            case 1:
                return "liked";
            case -1:
                return "disliked";
            default:
                return "null";
        }
    }
}
