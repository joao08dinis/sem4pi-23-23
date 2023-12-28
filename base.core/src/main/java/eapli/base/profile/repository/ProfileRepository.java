package eapli.base.profile.repository;

import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends DomainRepository<Long, Profile> {

    Optional<Profile> findProfileByUser(SystemUser user);

    List<Profile> getTeacherProfiles();

    List<Profile> getStudentProfiles();

    List<Profile> getAdminProfiles();


}
