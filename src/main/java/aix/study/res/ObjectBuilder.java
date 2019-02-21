
package aix.study.res;

import aix.study.res.domain.Domain;
import aix.study.res.domain.YouTubeResource;
import aix.study.res.model.Resource;
import aix.study.res.model.YouTube;
import aix.study.res.model.Model;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public class ObjectBuilder {
    
    public Model toModel(Domain domain, Class<?> clazz) {
        Model model = null;
        
        if(clazz.equals(YouTube.class)) {
            model = new YouTube();
        }
        
        if(clazz.equals(Resource.class)) {
            model = new Resource();
        }
        
        model = this.toModel(domain, model);
        
        return model;
    }
    
    public Model toModel(Domain domain, Model model) {
        if(domain == null || model == null) {
            return null;
        }
        
        if(model.getClass().equals(Resource.class)) {
            YouTubeResource ytr = (YouTubeResource) domain;
            Resource resource = (Resource) model;
            
            resource.setId(ytr.getId());
            resource.setAuthor(ytr.getAuthor());
            resource.setCreatedDate(ytr.getCreatedDate());
            resource.setDescription(ytr.getDescription());
            resource.setName(ytr.getName());
            resource.setUrlKey(ytr.getUrlKey());
            resource.setResourceType(ytr.getResourceType());
            resource.setUpdatedDate(ytr.getUpdatedDate());
            resource.setTags(ytr.getTags());
            
            return resource;
        }
        
        if(model.getClass().equals(YouTube.class)) {
            YouTubeResource ytr = (YouTubeResource) domain;
            YouTube yt = (YouTube) model;
            
            yt.setId(ytr.getId());
            yt.setUrl(ytr.getUrl());
            yt.setPresenter(ytr.getPresenter());
            
            return yt;
        }
        
        return null;
    }
    
    public Domain toDomain(Model model, Class<?> clazz) {
        Domain dom = null;
             
        if(clazz.equals(YouTubeResource.class)) {
            dom = new YouTubeResource();
        }
        
        dom = this.toDomain(model, dom);
        return dom;
    }
    
    public Domain toDomain(Model model, Domain dom) {
        if(model == null || dom == null) {
            return null;
        }
        
        if(dom.getClass().equals(YouTubeResource.class)) {
            YouTubeResource ytr = (YouTubeResource)dom;
            
            if(model.getClass().equals(Resource.class)) {
                Resource rs = (Resource)model;
                ytr.setId(rs.getId());
                ytr.setAuthor(rs.getAuthor());
                ytr.setCreatedDate(rs.getCreatedDate());
                ytr.setUpdatedDate(rs.getUpdatedDate());
                ytr.setDescription(rs.getDescription());
                ytr.setName(rs.getName());
                ytr.setUrlKey(rs.getUrlKey());
                ytr.setTags(rs.getTags());
            }
            
            if(model.getClass().equals(YouTube.class)) {
                YouTube ut = (YouTube)model;
                ytr.setId(ut.getId());
                ytr.setUrl(ut.getUrl());
                ytr.setPresenter(ut.getPresenter());
            }
            
            return ytr;
        }
        
        return null;
    }
}
