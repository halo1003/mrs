/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner;

import java.io.File;

import edu.stanford.smi.protegex.owl.jena.Jena;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;

/**
 *
 * @author HuyLy
 */
public class ontology {
	private static final String user_url = "file:///Users//toands//Documents//NetBeansProjects//mrs//database//user_song.owl";
	private static final String artist_url = "file:///Users//toands//Documents//NetBeansProjects//mrs//database//artist_song.owl";
	private static final String tag_url = "file:///Users//toands//Documents//NetBeansProjects//mrs//database//tag_song.owl";
	private static final String evaluation_url = "file:///Users//toands//Documents//NetBeansProjects//mrs//database//evaluation.owl";
	private static final String history_url = "file:///Users//toands//Documents//NetBeansProjects//mrs//database//history.owl";

        private static MyFactory history_factory = null;
	private static MyFactory artist_factory = null;
	private static MyFactory evaluation_factory = null;
	private static MyFactory tag_factory = null;
	private static MyFactory user_factory = null;

	private static OWLModel user_owlModel = null;
	private static OWLModel artist_owlModel = null;
	private static OWLModel tag_owlModel = null;
	private static OWLModel evaluation_owlModel = null;
	private static OWLModel history_owlModel = null;

	public static void getFile() {
		File f = new File(user_url);
		System.out.println(f);
	}
        
//        public static void main(String[] args) throws OntologyLoadException {
//            loadOntology();
//        }
	
	
	public static void loadOntology() throws OntologyLoadException {
	    	try{
	            user_owlModel = ProtegeOWL.createJenaOWLModelFromURI(user_url);
	            artist_owlModel = ProtegeOWL.createJenaOWLModelFromURI(artist_url);
	            tag_owlModel = ProtegeOWL.createJenaOWLModelFromURI(tag_url);
	            evaluation_owlModel = ProtegeOWL.createJenaOWLModelFromURI(evaluation_url);
	            history_owlModel = ProtegeOWL.createJenaOWLModelFromURI(history_url);

	            history_factory = new MyFactory(history_owlModel);
	            artist_factory = new MyFactory(artist_owlModel);
	            evaluation_factory = new MyFactory(evaluation_owlModel);
	            tag_factory = new MyFactory(tag_owlModel);
	            user_factory = new MyFactory(user_owlModel);
            } catch (OntologyLoadException e1) {
	        }
    }
	
	

	public static boolean checkConnection() {
            if(user_owlModel == null) return false;
                return true;
	}
//    
//    
//    
//    public static void saveOntology() {
//    		Jena.saveOntModel(user_owlModel, new File("/Users/lythanhhoang/Desktop/user.owl"), evaluation_owlModel.getOntModel(), "");
//    		Jena.saveOntModel(artist_owlModel, new File("/Users/lythanhhoang/Desktop/artist.owl"), evaluation_owlModel.getOntModel(), "");
//    		Jena.saveOntModel(tag_owlModel, new File("/Users/lythanhhoang/Desktop/tag.owl"), evaluation_owlModel.getOntModel(), "");
//    		Jena.saveOntModel(evaluation_owlModel, new File("/Users/lythanhhoang/Desktop/evaluation.owl"), evaluation_owlModel.getOntModel(), "");
//    		Jena.saveOntModel(history_owlModel, new File("/Users/lythanhhoang/Desktop/history.owl"), evaluation_owlModel.getOntModel(), "");
//    }
    
    public static MyFactory getHistory_factory() {
		return history_factory;
	}



	public static void setHistory_factory(MyFactory history_factory) {
		ontology.history_factory = history_factory;
	}



	public static MyFactory getArtist_factory() {
		return artist_factory;
	}



	public static void setArtist_factory(MyFactory artist_factory) {
		ontology.artist_factory = artist_factory;
	}



	public static MyFactory getEvaluation_factory() {
		return evaluation_factory;
	}



	public static void setEvaluation_factory(MyFactory evaluation_factory) {
		ontology.evaluation_factory = evaluation_factory;
	}



	public static MyFactory getTag_factory() {
		return tag_factory;
	}



	public static void setTag_factory(MyFactory tag_factory) {
		ontology.tag_factory = tag_factory;
	}



	public static MyFactory getUser_factory() {
		return user_factory;
	}



	public static void setUser_factory(MyFactory user_factory) {
		ontology.user_factory = user_factory;
	}

}
