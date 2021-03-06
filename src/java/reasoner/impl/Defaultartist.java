package reasoner.impl;

import edu.stanford.smi.protege.model.FrameID;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.impl.*;
import edu.stanford.smi.protegex.owl.javacode.AbstractCodeGeneratorIndividual;
import java.util.*;

import reasoner.artist;
import reasoner.*;

/**
 * Generated by Protege-OWL  (http://protege.stanford.edu/plugins/owl).
 * Source OWL Class: http://www.owl-ontologies.com/Ontology1494167488.owl#artist
 *
 * @version generated on Sun Nov 12 13:55:39 GMT+07:00 2017
 */
public class Defaultartist extends AbstractCodeGeneratorIndividual
         implements artist {

    public Defaultartist(OWLModel owlModel, FrameID id) {
        super(owlModel, id);
    }


    public Defaultartist() {
    }



    // Property http://www.owl-ontologies.com/Ontology1494167488.owl#presents

    public Collection getPresents() {
        return getPropertyValuesAs(getPresentsProperty(), song.class);
    }


    public RDFProperty getPresentsProperty() {
        final String uri = "http://www.owl-ontologies.com/Ontology1494167488.owl#presents";
        final String name = getOWLModel().getResourceNameForURI(uri);
        return getOWLModel().getRDFProperty(name);
    }


    public boolean hasPresents() {
        return getPropertyValueCount(getPresentsProperty()) > 0;
    }


    public Iterator listPresents() {
        return listPropertyValuesAs(getPresentsProperty(), song.class);
    }


    public void addPresents(song newPresents) {
        addPropertyValue(getPresentsProperty(), newPresents);
    }


    public void removePresents(song oldPresents) {
        removePropertyValue(getPresentsProperty(), oldPresents);
    }


    public void setPresents(Collection newPresents) {
        setPropertyValues(getPresentsProperty(), newPresents);
    }
}
