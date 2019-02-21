
package aix.study.res.dao.nosql;

import aix.study.res.model.Resource;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public interface ResourceDao extends MongoRepository<Resource, String> {
    
    public List<Resource> findByAuthor(String name);
    
    public Optional<Resource> findByUrlKey(String key);
}