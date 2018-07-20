
package aix.study.res.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@Document("utube")
public class YouTube implements Model {
    
    private String id;
    
    @Indexed
    private String url;
    
    @Indexed
    private String presenter;
    
    /**
     * Returns Resource Id
     * 
     * @return String
     */    
    public String getId() {
        return this.id;
    }

    /**
     * Sets Resource Id
     * 
     * @param id
     */     
    public void setId(String id) {
        this.id = id;
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
}
