package msvcdojo;

public class Profile {
	
private String key;
private String fullName;
private Integer photoCount;

public void setKey(String key) { this.key = key; }
public void setFullName(String fullName) { this.fullName = fullName; }
public void addPhotoCount(Integer photoCount) { this.photoCount = photoCount; }

	public String getKey() { return key; }
	public String getFullName() {
	    return fullName;
	}
	public Integer getPhotoCount() { return photoCount; }
}
