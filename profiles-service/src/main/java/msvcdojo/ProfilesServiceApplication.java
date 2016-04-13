package msvcdojo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ProfilesServiceApplication {
	
	// tag::cleanup[]
    @Bean
    CommandLineRunner init(ProfilesRepository profilesRepository) {
        return a -> profilesRepository.deleteAll();
    }
// end::cleanup[]

    @Bean
    public ResourceProcessor<Resource<Profile>> personProcessor() {

        return new ResourceProcessor<Resource<Profile>>() {

            @Override
            public Resource<Profile> process(Resource<Profile> resource) {

                resource.add(linkTo(methodOn(ProfilePhotoController.class).readPhotos(resource.getContent().getKey())).withRel("photos"));

                return resource;
            }
        };
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProfilesServiceApplication.class, args);
    }
}
