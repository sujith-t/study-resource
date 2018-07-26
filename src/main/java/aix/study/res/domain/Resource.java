
package aix.study.res.domain;

import aix.study.res.Errors;
import aix.study.res.model.ResourceType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public class Resource implements Domain {

    protected String id;
    protected String name;
    protected String description;
    protected String author;
    protected Date createdDate;
    protected Date updatedDate;
    protected ResourceType resourceType;
    protected String urlKey;
    protected Set<String> tags;
    
    /**
     * Returns Id
     * 
     * @return String
     */    
    public String getId() {
        return id;
    }

    /**
     * Sets Id
     * 
     * @param id
     */    
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns Name
     * 
     * @return String
     */    
    public String getName() {
        return this.name;
    }

    /**
     * Sets Name
     * 
     * @param name
     */     
    public void setName(String name) {
        this.name = name;
        this.urlKey = null;
    }
    
    /**
     * Returns Description
     * 
     * @return String
     */    
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets Name
     * 
     * @param desc
     */     
    public void setDescription(String desc) {
        this.description = desc;
    }
    
    /**
     * Returns Author
     * 
     * @return String
     */    
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets Author
     * 
     * @param username
     */     
    public void setAuthor(String username) {
        this.author = username;
    }
    
    /**
     * Returns Created Date
     * 
     * @return Date
     */    
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Sets Created Date
     * 
     * @param date
     */     
    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }
    
    /**
     * Returns Updated Date
     * 
     * @return Date
     */    
    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    /**
     * Sets Updated Date
     * 
     * @param date
     */     
    public void setUpdatedDate(Date date) {
        this.updatedDate = date;
    }
    
    /**
     * Returns Resource Type
     * 
     * @return ResourceType
     */    
    public ResourceType getResourceType() {
        return this.resourceType;
    }

    /**
     * Return Tags
     * 
     * @return Set<String>
     */    
    public Set<String> getTags() {
        return this.tags;
    }
    
    /**
     * Set Tags
     * 
     * @param tags
     */     
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }    
    
    /**
     * Returns Url Key
     * 
     * @return String
     */    
    public String getUrlKey() {
        if(this.urlKey == null && this.name != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
         
            String key = this.name.replaceAll("[\\s|&/:!`'\"]", "-") + "-" + dateFormat.format(new Date());
            this.urlKey = key.replaceAll("(\\s)+", "-");
        } 
        
        return this.urlKey;
    }
    
    /**
     * Sets Url Key
     * 
     * @param key
     */     
    public void setUrlKey(String key) {
        this.urlKey = key;
    }

    @Override
    public Errors.Code isValid() {
        return Errors.Code.RES_NO_ERROR;
    }
}
