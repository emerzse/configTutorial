package msvcdojo;

import java.util.ArrayList;
import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="profiles")
public class Profile {
	 public Profile() {
	    }

	    @Id
	    private String id;
	    @Indexed
	    private String fullName;
	    private List<String> photos;

	    public void setId(String id) {
	        this.id = id;
	    }
	    public void setKey(String key) {
	        this.id = key;
	    }
	    public void setFullName(String fullName) {
	        this.fullName = fullName;
	    }
	    public void addPhotoReference(String photoId) {
	        this.photosList().add(photoId);
	    }

	    public String getKey() {
	        return id;
	    }
	    public String getFullName() {
	        return fullName;
	    }
	    public Integer getPhotoCount() { return this.photosList().size(); }
	    public List<String> photosList() {
	        if (this.photos == null)
	            this.photos = new ArrayList<>();
	        return this.photos;
	    }

	    @Override
	    public String toString() {
	        return String.format(
	                "Customer[id=%s, fullName='%s']",
	                id, fullName);
	    }
}
