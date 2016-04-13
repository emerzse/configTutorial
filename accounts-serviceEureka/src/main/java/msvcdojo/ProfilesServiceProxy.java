package msvcdojo;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("profiles")//profiles-service
public interface ProfilesServiceProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/profiles/{key}")
    ResponseEntity<Profile> getProfile(@PathVariable("key") String key);
}
