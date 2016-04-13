package msvcdojo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/profiles/{key}/photos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfilePhotoController {
	// end::controller[]

    private final ProfilesRepository profilesRepository;
    private final GridFsTemplate fs;

    @RequestMapping(method = RequestMethod.GET)
    Collection<Resource<String>> readPhotos(@PathVariable String key) {

        Profile profile = this.profilesRepository.findOne(key);

        Collection<Resource<String>> coll = new LinkedList<>();

        profile.photosList().forEach(photoId -> {
            Resource<String> resource = new Resource<>(photoId);
            resource.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}/photo").buildAndExpand(photoId).toString(), photoId));

            coll.add(resource);
        });

        return coll;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Resource<Profile> readPhoto(@PathVariable String id) {
        Profile profilePhoto = this.profilesRepository.findOne(id);
        return new Resource<Profile>(profilePhoto);
    }

    @RequestMapping(value = "/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<PhotoResource> readPhotoResource(@PathVariable String id) {
        Photo photo = () -> this.fs.getResource(id).getInputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(new PhotoResource(photo), httpHeaders, HttpStatus.OK);
    }

    // tag::insertPhoto[]
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    ResponseEntity<Resource<Profile>> insertPhoto(@PathVariable String key,
                                                       @RequestParam MultipartFile file) throws IOException {
        Photo photo = file::getInputStream;
        Profile profile = this.profilesRepository.findOne(key);
        String id = key + profile.getPhotoCount();
        try (InputStream inputStream = photo.getInputStream()) {
            this.fs.store(inputStream, id);
        }
        profile.addPhotoReference(id);
        this.profilesRepository.save(profile);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/photo").buildAndExpand(id).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(
                this.readPhoto(key), headers, HttpStatus.CREATED);
    }
    // end::insertPhoto[]

    @Autowired
    public ProfilePhotoController(ProfilesRepository repository, GridFsTemplate gridFileSystem) {
        this.profilesRepository = repository;
        this.fs = gridFileSystem;
    }
}
