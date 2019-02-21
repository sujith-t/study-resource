
package aix.study.res.domain;

import aix.study.res.Errors;
import aix.study.res.model.ResourceType;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public class YouTubeResource extends Resource {
    
    private String url;
    private String presenter;
    
    public YouTubeResource() {
        this.resourceType = ResourceType.UTUBE;
    }
    
    /**
     * Returns URL
     * 
     * @return String
     */    
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets URL
     * 
     * @param url
     */     
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Returns Presenter Name
     * 
     * @return String
     */    
    public String getPresenter() {
        return this.presenter;
    }

    /**
     * Sets URL
     * 
     * @param name
     */     
    public void setPresenter(String name) {
        this.presenter = name;
    }    

    /**
     * Validates the domain object
     * 
     * @return Errors.Code
     */      
    @Override
    public Errors.Code isValid() {
        if(this.name == null || this.name.isEmpty() || this.name.trim().length() == 0) {
            return Errors.Code.RES_UTR_REQ_NAME;
        }
        
        if(this.author == null || this.author.isEmpty() || this.author.trim().length() == 0) {
            return Errors.Code.RES_UTR_REQ_AUTHOR;
        }
        
        if(this.url == null || this.url.isEmpty() || this.url.trim().length() == 0) {
            return Errors.Code.RES_UTR_REQ_URL;
        }
        
        try {
            URL resourceUrl = new URL(this.url);
        } catch(MalformedURLException e) {
            return Errors.Code.RES_UTR_INVALID_URL;
        }
        
        /* id validation should be last always */
        if(this.id == null || this.id.isEmpty() || this.id.trim().length() == 0) {
            return Errors.Code.RES_UTR_REQ_ID;
        }        
        
        return Errors.Code.RES_NO_ERROR;
    }
}