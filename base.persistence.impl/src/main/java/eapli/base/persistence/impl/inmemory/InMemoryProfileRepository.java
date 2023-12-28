package eapli.base.persistence.impl.inmemory;

import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.profile.repository.ProfileRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryProfileRepository extends InMemoryDomainRepository<Profile, Long> implements ProfileRepository {

    public InMemoryProfileRepository() {
    }

    public InMemoryProfileRepository(Function<? super Profile, Long> identityGenerator) {
        super(identityGenerator);
    }

    @Override
    public Optional<Profile> findProfileByUser(SystemUser user) {
        return this.matchOne(profile -> profile.getUser().equals(user));
    }


    @Override
    public List<Profile> getTeacherProfiles() {
        Iterator<Profile> iterator = this.match(profile -> profile instanceof TeacherProfile).iterator();
        List<Profile> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        return list;
    }



    @Override
    public List<Profile> getStudentProfiles() {
        Iterator<Profile> iterator = this.match(profile -> profile instanceof StudentProfile).iterator();
        List<Profile> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }

    @Override
    public List<Profile> getAdminProfiles() {
            Iterator<Profile> iterator = this.match(profile -> profile instanceof AdminProfile).iterator();
            List<Profile> list = new ArrayList<>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }

            return list;
    }



    @Override
    public Optional<Profile> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(Long entityId) {

    }


}
