package eapli.base.profile.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.repository.ProfileRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository repository = PersistenceContext.repositories().profiles();

    public Profile addNewProfile(Profile profile){

        return repository.save(profile);
    }

    public Optional<Profile> getProfileFromDatabase(SystemUser user){

        Optional<Profile> profile = repository.findProfileByUser(user);
        return profile;

    }


    public List<Profile> getTeacherProfiles(){
        return repository.getTeacherProfiles();
    }


    public List<Profile> getStudentProfiles(){
        return repository.getStudentProfiles();
    }

    public List<Profile> getAdminProfiles(){
        return repository.getAdminProfiles();
    }


}
