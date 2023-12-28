package eapli.base.persistence.impl.jpa;

import eapli.base.profile.domain.AdminProfile;
import eapli.base.profile.domain.Profile;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.base.profile.repository.ProfileRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.*;

public class JpaProfileRepository extends JpaAutoTxRepository<Profile, Long, Long> implements ProfileRepository {

    public JpaProfileRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaProfileRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaProfileRepository(TransactionalContext tx) {
        super(tx, "id");
    }



    @Override
    public Optional<Profile> findProfileByUser(SystemUser user) {
        return this.matchOne("e.user.email.email = '" + user.email().toString() + "'");

    }


    @Override
    public List<Profile> getTeacherProfiles() {
        List<Profile> teacherProfiles = new ArrayList<>();
        for (Profile profile : this.findAll()) {
            if (profile instanceof TeacherProfile){
                teacherProfiles.add(profile);
            }
        }
        return teacherProfiles;
    }


    @Override
    public List<Profile> getStudentProfiles() {
        List<Profile> studentProfiles = new ArrayList<>();
        for (Profile profile : this.findAll()) {
            if (profile instanceof StudentProfile){
                studentProfiles.add(profile);
            }
        }
        return studentProfiles;
    }

    @Override
    public List<Profile> getAdminProfiles() {
        List<Profile> adminProfiles = new ArrayList<>();
        for (Profile profile : this.findAll()) {
            if (profile instanceof AdminProfile){
                adminProfiles.add(profile);
            }
        }
        return adminProfiles;
    }




}
