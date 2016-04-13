package msvcdojo;

import java.io.IOException;
import java.io.InputStream;

public interface Photo {
	/**
     * @return a new {@link InputStream} containing photo data as a JPEG. The caller is
     * responsible for closing the stream.
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException;
}
