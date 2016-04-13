package msvcdojo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//discovery comunication -begin
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.DiscoveryClient;
//discovery comunication -end

@Component
public class ProfilesClient {
	//private final DiscoveryClient discoveryClient; //discovery comunication
	private final LoadBalancerClient loadBalancer;
	
	//discovery comunication -begin
//    @Autowired
//    public ProfilesClient(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }
	//discovery comunication -end
	
    @Autowired
    public ProfilesClient(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }
   
    public URI getProfileUri(Account account) {

    	ServiceInstance instance = loadBalancer.choose("profiles");
        if (instance == null)
            return null;

        return UriComponentsBuilder.fromHttpUrl( (instance.isSecure() ? "https://" : "http://") +
                instance.getHost() + ":" + instance.getPort() + "/profiles/{key}")
                .buildAndExpand(account.getUsername()).toUri();
        
    }

    // ribbon communcation - begin 
//    public ResponseEntity<Profile> getProfile(URI profileUri) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForEntity(profileUri, Profile.class);
 //   }
    // ribbon communcation - end
    
      //discovery comunication -begin
        //        InstanceInfo instance = discoveryClient.getNextServerFromEureka(
//                "profiles", false);//profiles-service
//
//        String url = instance.getHomePageUrl();
//
//        return UriComponentsBuilder.fromHttpUrl( url + "/profiles/{key}")
//                .buildAndExpand(account.getUsername()).toUri();
      //discovery comunication -end
//    }
}
