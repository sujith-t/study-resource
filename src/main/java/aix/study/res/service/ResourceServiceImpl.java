
package aix.study.res.service;

import aix.study.res.Errors;
import aix.study.res.ObjectBuilder;
import aix.study.res.dao.nosql.ResourceDao;
import aix.study.res.dao.nosql.YouTubeDao;
import aix.study.res.domain.Domain;
import aix.study.res.domain.YouTubeResource;
import aix.study.res.model.Resource;
import aix.study.res.model.ResourceType;
import aix.study.res.model.YouTube;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.ejb.Lock;
import javax.ejb.LockType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    
    private final ResourceDao resourceDao;
    private final YouTubeDao utubeDao;
    private final ObjectBuilder builder;
    
    /**
     * Constructor
     * @param resourceDao
     * @param utubeDao
     */
    @Autowired
    public ResourceServiceImpl(ResourceDao resourceDao, YouTubeDao utubeDao) {
        this.resourceDao = resourceDao;
        this.utubeDao = utubeDao;
        this.builder = new ObjectBuilder();
    }
    
    /**
     * Creates a YouTube Resource
     * 
     * @param resource
     * 
     * @return Errors.Code
     */      
    @Transactional
    @Lock(LockType.WRITE)
    @Override
    public Errors.Code create(YouTubeResource resource) {
        resource.setCreatedDate(new Date());

        Errors.Code response = resource.isValid();
        if(!response.equals(Errors.Code.RES_NO_ERROR) && !response.equals(Errors.Code.RES_UTR_REQ_ID)) {
            return response;
        }
        
        Resource res = (Resource)this.builder.toModel(resource, Resource.class);
        YouTube utube = (YouTube)this.builder.toModel(resource, YouTube.class);
        
        if(resource.getId() != null && resource.getId().trim().length() > 0) {
            Optional<Resource> ut = this.resourceDao.findById(resource.getId());
            try {
                if(ut.get() != null && ut.get().getId().equals(resource.getId())) {
                    return Errors.Code.RES_UTR_DUPLICATE_ID;
                }
            } catch(NoSuchElementException e) {}
        }
        
        List<YouTube> list = this.utubeDao.findByUrl(resource.getUrl());
        if(list.size() > 0) {
            return Errors.Code.RES_UTR_DUPLICATE_URL;
        }
        
        this.resourceDao.save(res);
        utube.setId(res.getId());
        this.utubeDao.save(utube);
        resource.setId(res.getId());
        
        return Errors.Code.RES_NO_ERROR;
    }

    
    /**
     * Update a YouTube Resource
     * 
     * @param resource
     * 
     * @return Errors.Code
     */      
    @Transactional
    @Lock(LockType.WRITE)
    @Override
    public Errors.Code update(YouTubeResource resource) {
        resource.setUpdatedDate(new Date());

        Errors.Code response = resource.isValid();
        if(!response.equals(Errors.Code.RES_NO_ERROR)) {
            return response;
        }
        
        Resource res = (Resource)this.builder.toModel(resource, Resource.class);
        YouTube utube = (YouTube)this.builder.toModel(resource, YouTube.class);
        
        //retrieve and check whether the resource is already available
        Optional<Resource> optRes = this.resourceDao.findById(resource.getId());
        Optional<YouTube> optYT = this.utubeDao.findById(resource.getId());
        try {
            if(optRes.get() == null ||  optYT.get() == null) {
                return Errors.Code.RES_UTR_NO_YTRESOURCE_UPDATE;
            }
        } catch(NoSuchElementException e) {
            return Errors.Code.RES_UTR_NO_YTRESOURCE_UPDATE;
        }
       
        List<YouTube> list = this.utubeDao.findByUrl(resource.getUrl());
        if(list.size() > 0 && !list.get(0).getId().equals(utube.getId())) {
            return Errors.Code.RES_UTR_DUPLICATE_URL;
        }
        
        this.resourceDao.save(res);
        this.utubeDao.save(utube);
        
        return Errors.Code.RES_NO_ERROR;        
    }

    /**
     * Returns a YouTube Resource
     * 
     * @param id
     * 
     * @return aix.study.res.domain.Resource
     */      
    @Override
    public aix.study.res.domain.Resource getResourceById(String id) {
        
        Class clazz = Domain.class;
        aix.study.res.domain.Resource resourceDomain;
        Optional<Resource> resource = this.resourceDao.findById(id);
        
        try {
            Resource res = resource.get();
            if(res.getResourceType().equals(ResourceType.UTUBE)) {
                clazz = YouTubeResource.class;
            }
            
            resourceDomain = (aix.study.res.domain.Resource)this.builder.toDomain(res, clazz);
            
            if(res.getResourceType().equals(ResourceType.UTUBE)) {
                Optional<YouTube> ut = this.utubeDao.findById(id);
                return (YouTubeResource) this.builder.toDomain(ut.get(), (YouTubeResource)resourceDomain);
            }
            
        } catch(NoSuchElementException e) {}

        return null;
    }
    
    /**
     * Returns a YouTube Resource By url key
     * 
     * @param key
     * 
     * @return aix.study.res.domain.Resource
     */      
    @Override
    public aix.study.res.domain.Resource getResourceByUrlKey(String key) {
        
        Class clazz = Domain.class;
        aix.study.res.domain.Resource resourceDomain;
        Optional<Resource> resource = this.resourceDao.findByUrlKey(key);
        
        try {
            Resource res = resource.get();
            if(res.getResourceType().equals(ResourceType.UTUBE)) {
                clazz = YouTubeResource.class;
            }
            
            resourceDomain = (aix.study.res.domain.Resource)this.builder.toDomain(res, clazz);
            
            if(res.getResourceType().equals(ResourceType.UTUBE)) {
                Optional<YouTube> ut = this.utubeDao.findById(res.getId());
                return (YouTubeResource) this.builder.toDomain(ut.get(), (YouTubeResource)resourceDomain);
            }
            
        } catch(NoSuchElementException e) {}

        return null;
    }

    /**
     * Returns a YouTube Resource By Author
     * 
     * @param author
     * 
     * @return List<aix.study.res.domain.Resource>
     */    
    @Override
    public List<aix.study.res.domain.Resource> getResourcesByAuthor(String author) {
        List<aix.study.res.domain.Resource> list = new ArrayList<>();
        
        List<Resource> resources = this.resourceDao.findByAuthor(author);
        resources.forEach((res) -> {
            try {
                Class clazz = Domain.class;
                if(res.getResourceType().equals(ResourceType.UTUBE)) {
                    clazz = YouTubeResource.class;
                }
                
                aix.study.res.domain.Resource resourceDomain;
                resourceDomain = (aix.study.res.domain.Resource)this.builder.toDomain(res, clazz);

                if(res.getResourceType().equals(ResourceType.UTUBE)) {
                    Optional<YouTube> ut = this.utubeDao.findById(res.getId());
                    YouTubeResource ytr = (YouTubeResource) this.builder.toDomain(ut.get(), (YouTubeResource)resourceDomain);
                    list.add(ytr);
                }
                
            } catch(NoSuchElementException e) {}
        });
        
        return list;
    }

    /**
     * Returns a YouTube Resource By url
     * 
     * @param url
     * 
     * @return aix.study.res.domain.YouTubeResource
     */     
    @Override
    public YouTubeResource getYouTubeByUrl(String url) {
        
        List<YouTube> list = this.utubeDao.findByUrl(url);
        if(list.size() > 0) {
            YouTube ut = list.get(0);
            Optional<Resource> opt = this.resourceDao.findById(ut.getId());
            
            try {
                YouTubeResource ytr = (YouTubeResource)this.builder.toDomain(opt.get(), YouTubeResource.class);
                ytr = (YouTubeResource)this.builder.toDomain(ut, ytr);
                
                return ytr;
            } catch(NoSuchElementException e) {}
        }

        return null;        
    }
}
