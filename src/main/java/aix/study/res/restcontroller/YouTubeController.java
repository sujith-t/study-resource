
package aix.study.res.restcontroller;

import aix.study.res.Errors;
import aix.study.res.domain.Resource;
import aix.study.res.domain.YouTubeResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import aix.study.res.service.ResourceService;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@RestController
public class YouTubeController {
    
    private final ResourceService service;
    
    /**
     * Constructor
     * 
     * @param service
     */      
    @Autowired
    public YouTubeController(ResourceService service) {
        this.service = service;
    }   
    
    /**
     * Creates a Resource
     * 
     * @param resource
     * 
     * @return Map<String,String>
     */    
    @RequestMapping(value="/resource/utube", method=POST)
    public @ResponseBody Map<String,String> createResource(@RequestBody YouTubeResource resource) {
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "FAILED");
        
        try {
            Errors.Code code = this.service.create(resource);
            response.put("code", code.toString());
            response.put("description", Errors.getInfo(code));
            if(code.equals(Errors.Code.RES_NO_ERROR)) {
                response.put("status", "SUCCESS");
            }
            response.put("id", resource.getId());
        } catch(Exception e) {
            response.put("code", Errors.Code.RES_SYS_ERROR.toString());
            response.put("description", Errors.getInfo(Errors.Code.RES_SYS_ERROR) + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * Update a Resource
     * 
     * @param resource
     * 
     * @return Map<String,String>
     */    
    @RequestMapping(value="/resource/utube", method=PUT)
    public @ResponseBody Map<String,String> updateResource(@RequestBody YouTubeResource resource) {
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "FAILED");
        
        try {
            Errors.Code code = this.service.update(resource);
            response.put("code", code.toString());
            response.put("description", Errors.getInfo(code));
            if(code.equals(Errors.Code.RES_NO_ERROR)) {
                response.put("status", "SUCCESS");
            }
        } catch(Exception e) {
            response.put("code", Errors.Code.RES_SYS_ERROR.toString());
            response.put("description", Errors.getInfo(Errors.Code.RES_SYS_ERROR) + e.getMessage());
        }
        
        response.put("id", resource.getId());
        return response;
    }   
}
