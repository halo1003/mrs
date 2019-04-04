package reasoner;

import edu.stanford.smi.protegex.owl.model.*;

import java.util.*;

/**
 * Generated by Protege-OWL  (http://protege.stanford.edu/plugins/owl).
 * Source OWL Class: http://www.owl-ontologies.com/Ontology1494167488.owl#tag
 *
 * @version generated on Sun Nov 12 13:55:39 GMT+07:00 2017
 */
public interface tag extends OWLIndividual {

    // Property http://www.owl-ontologies.com/Ontology1494167488.owl#includes

    Collection getIncludes();

    RDFProperty getIncludesProperty();

    boolean hasIncludes();

    Iterator listIncludes();

    void addIncludes(song newIncludes);

    void removeIncludes(song oldIncludes);

    void setIncludes(Collection newIncludes);
}
