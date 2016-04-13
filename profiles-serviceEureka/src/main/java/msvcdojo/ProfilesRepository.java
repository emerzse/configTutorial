package msvcdojo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProfilesRepository  extends MongoRepository<Profile, String> {
	
    @Query("{ '_id' : ?0 }")
    Profile findByKey(@Param("key") String key);

    Profile findByFullName(@Param("address") String address);
}
