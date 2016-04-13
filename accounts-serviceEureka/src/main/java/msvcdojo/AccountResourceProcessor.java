package msvcdojo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountResourceProcessor implements ResourceProcessor<Resource<Account>>{
	ProfilesClient profilesClient;
	ProfilesServiceProxy profilesServiceProxy;
     
	
//    @Autowired
//    public AccountResourceProcessor(ProfilesClient profilesClient) {
//        this.profilesClient = profilesClient;
//    }

    @Autowired
    public AccountResourceProcessor(ProfilesClient profilesClient , ProfilesServiceProxy profilesServiceProxy) {
        this.profilesClient = profilesClient;
        this.profilesServiceProxy = profilesServiceProxy;
    }
    
	@Override
	public Resource<Account> process(Resource<Account> accountResource) {
		Account account = accountResource.getContent();

        URI profileUri = this.profilesClient.getProfileUri(account);

        if (null != profileUri) {
        	ResponseEntity<Profile> profile = this.profilesServiceProxy.getProfile(account.getUsername());
        	if (null != profile)
        	    accountResource.getContent().updateWithProfileData(profile.getBody());
        	
            Link profileLink = new Link(profileUri.toString(), "profile");
            accountResource.add(profileLink);
        }
        return accountResource;
	}
}
