
package aix.study.res.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public class Resource implements Model {
    
    @Id
    private String id;
    
    @Indexed
    private String name;
    
    @Indexed
    private String description;
    
    @Indexed
    private String author;
    
    private Date createdDate;
    private Date updatedDate;
    
    @Indexed
    private ResourceType resourceType;
    
    @Indexed
    private String urlKey;    
    
    @Indexed
    private List<String> tags;
    
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
        
        if(this.createdDate == null) {
            this.createdDate = date;
        }
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
     * @return String
     */    
    public ResourceType getResourceType() {
        return this.resourceType;
    }
    
    /**
     * Sets Resource Type
     * 
     * @param type
     */     
    public void setResourceType(ResourceType type) {
        this.resourceType = type;
    }
    
    /**
     * Returns Url Key
     * 
     * @return String
     */    
    public String getUrlKey() {
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

    /**
     * Return Tags
     * 
     * @return List<String>
     */    
    public List<String> getTags() {
        return this.tags;
    }
    
    /**
     * Set Tags
     * 
     * @param tags
     */     
    public void setTags(List<String> tags) {
        this.tags = tags;
    }    
}