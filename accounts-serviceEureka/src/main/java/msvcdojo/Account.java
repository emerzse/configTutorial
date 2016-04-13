package msvcdojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

	

@Entity
class Account {

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    
    @Transient
    private String fullName;
    @Transient
    private Integer photoCount;

    public String getFullName() {
        return fullName;
    }
    public Integer getPhotoCount() { return photoCount; }

    public void updateWithProfileData(Profile profile) {
        this.fullName = profile.getFullName();
        this.photoCount = profile.getPhotoCount();
    }
    Account() { // JPA only
    }

    public Account(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
