
package aix.study.res.domain;

import aix.study.res.Errors;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public interface Domain {

    /**
     * Validates the domain object
     * 
     * @return Errors.Code
     */      
    public Errors.Code isValid();
}
