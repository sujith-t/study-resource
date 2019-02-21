
package aix.study.res.dao.nosql;

import aix.study.res.model.YouTube;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
public interface YouTubeDao extends MongoRepository<YouTube, String> {
    
    @Query("{url : ?0}")
     public List<YouTube> findByUrl(String url);
}
