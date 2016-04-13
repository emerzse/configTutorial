package msvcdojo;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.AbstractResource;
import org.springframework.util.Assert;

public class PhotoResource extends AbstractResource{
	
    private final Photo photo;

    public PhotoResource(Photo photo) {
        Assert.notNull(photo, "Photo must not be null");
        this.photo = photo;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.photo.getInputStream();
    }

    @Override
    public long contentLength() throws IOException {
        return -1;
    }
}
