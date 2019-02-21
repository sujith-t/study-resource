
package aix.study.res.restcontroller;

import aix.study.res.domain.Resource;
import aix.study.res.service.ResourceService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@RestController
public class ResourceController {

    private final ResourceService service;
    
    /**
     * Constructor
     * 
     * @param service
     */      
    @Autowired
    public ResourceController(ResourceService service) {
        this.service = service;
    }
    
    /**
     * Returns Resource By Id
     * 
     * @param id
     * 
     * @return Optional<Resource>
     */      
    @RequestMapping(value="/resource", method=GET, params="id")
    public Optional<Resource> getResourceById(@RequestParam(value="id") String id) {
        
        Resource resource = this.service.getResourceById(id);
        return Optional.ofNullable(resource);
    }
    
    /**
     * Returns All Resource By url key
     * 
     * @param key
     * 
     * @return Optional<Resource>
     */      
    @RequestMapping(value="/resource", method=GET, params="key")
    public Optional<Resource> getResourceByUrlKey(@RequestParam(value="key") String key) {
        
        Resource resource = this.service.getResourceByUrlKey(key);
        return Optional.ofNullable(resource);
    }
    
    /**
     * Returns All Resource from Author
     * 
     * @param author
     * 
     * @return List<Resource>
     */    
    @RequestMapping(value="/resource", method=GET, params="author")
    public @ResponseBody List<Resource> getResourceByAuthor(@RequestParam(value="author") String author) {
        
        List <Resource> list = this.service.getResourcesByAuthor(author);
        return list;
    }     
}
