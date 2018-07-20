
package aix.study.res.service;

import aix.study.res.Errors;
import aix.study.res.domain.YouTubeResource;
import java.util.List;
import javax.ejb.Local;
import aix.study.res.domain.Resource;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@Local
public interface ResourceService {
    
    public Errors.Code create(YouTubeResource resource);
    
    public Errors.Code update(YouTubeResource resource);
    
    public Resource getResourceById(String id);
    
    public List<Resource> getResourcesByAuthor(String author);
    
    public Resource getResourceByUrlKey(String key);
}
