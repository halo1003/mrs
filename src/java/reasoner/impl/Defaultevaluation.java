package reasoner.impl;

import edu.stanford.smi.protege.model.FrameID;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.impl.*;
import edu.stanford.smi.protegex.owl.javacode.AbstractCodeGeneratorIndividual;
import reasoner.evaluation;
import reasoner.*;

/**
 * Generated by Protege-OWL  (http://protege.stanford.edu/plugins/owl).
 * Source OWL Class: http://www.owl-ontologies.com/Ontology1494167488.owl#evaluation
 *
 * @version generated on Sun Nov 12 13:55:39 GMT+07:00 2017
 */
public class Defaultevaluation extends AbstractCodeGeneratorIndividual
         implements evaluation {

    public Defaultevaluation(OWLModel owlModel, FrameID id) {
        super(owlModel, id);
    }


    public Defaultevaluation() {
    }



    // Property http://www.owl-ontologies.com/Ontology1494167488.owl#point

    public float getPoint() {
        return getPropertyValueLiteral(getPointProperty()).getFloat();
    }


    public RDFProperty getPointProperty() {
        final String uri = "http://www.owl-ontologies.com/Ontology1494167488.owl#point";
        final String name = getOWLModel().getResourceNameForURI(uri);
        return getOWLModel().getRDFProperty(name);
    }


    public boolean hasPoint() {
        return getPropertyValueCount(getPointProperty()) > 0;
    }


    public void setPoint(float newPoint) {
        setPropertyValue(getPointProperty(), new java.lang.Float(newPoint));
    }
}
