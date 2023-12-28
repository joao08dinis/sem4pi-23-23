package eapli.base.profile.controller;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.grade.domain.Grade;
import eapli.base.profile.domain.*;
import eapli.base.profile.service.ProfileService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@UseCaseController
public class ProfileController {

    private final ProfileService profileService = new ProfileService();

    public Profile createTeacherProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth, String acronym){

        TeacherProfile profile = new TeacherProfile(id, user, dateOfBirth, taxPayerNumber, acronym);

        profileService.addNewProfile(profile);

        return profile;
    }

    public Profile createStudentProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth, MecanographicNumber mecanographicNumber){

        StudentProfile profile = new StudentProfile(id, user, dateOfBirth, taxPayerNumber, mecanographicNumber);

        profileService.addNewProfile(profile);

        return profile;
    }

    public Profile createAdminProfile(SystemUser user, Long id, TaxPayerNumber taxPayerNumber, DateOfBirth dateOfBirth){

        AdminProfile profile = new AdminProfile(id, user, dateOfBirth, taxPayerNumber);

        profileService.addNewProfile(profile);

        return profile;
    }

    public void addGrade(Grade grade, StudentProfile studentProfile){
        studentProfile.getGrades().add(grade);
        profileService.addNewProfile(studentProfile);
    }

    public Optional<Profile> getUserProfile(SystemUser user){
        return profileService.getProfileFromDatabase(user);
    }


    public List<Profile> getTeacherProfiles(){
        return profileService.getTeacherProfiles();
    }

    public List<Profile> getStudentProfiles(){
        return profileService.getStudentProfiles();
    }

    public List<Profile> getAdminProfiles(){
        return profileService.getAdminProfiles();
    }

}
