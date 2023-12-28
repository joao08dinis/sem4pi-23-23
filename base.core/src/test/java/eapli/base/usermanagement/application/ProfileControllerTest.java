package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.course.domain.Course;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.*;
import eapli.base.profile.repository.ProfileRepository;
import eapli.base.profile.service.ProfileService;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileControllerTest {

    private ProfileController controller = new ProfileController();
    private ProfileService service = new ProfileService();

    @BeforeEach
    public void setUp() {

    }
    /*
    @Test
    @DisplayName("Test if its creating teacher profiles sucessfully")
    public void testCreateTeacherProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        MecanographicNumber mecanographicNumber = new MecanographicNumber("1211511");
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        TeacherProfile profile = (TeacherProfile) controller.createTeacherProfile(null, null, number, date, "abc");
        assertEquals("abc", profile.getAcronym());
    }

    @Test
    @DisplayName("Test if its creating student profiles sucessfully")
    public void testCreateStudentProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        MecanographicNumber mecanographicNumber = new MecanographicNumber("1211511");
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        StudentProfile profile = (StudentProfile) controller.createStudentProfile(null, null, number, date, mecanographicNumber);
        assertEquals("1211511", profile.getMecanographicNumber().toString());
    }

    @Test
    @DisplayName("Test if its creating admin profiles sucessfully")
    public void testCreateAdminProfiles() throws Exception {

        TaxPayerNumber number = null;
        DateOfBirth date = null;
        try {
            number = TaxPayerNumber.from("123456789");
            date = DateOfBirth.from(1,2,2000);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        AdminProfile profile = (AdminProfile) controller.createAdminProfile(null, null, number, date);
        assertEquals(1, profile.getDateOfBirth().getDay());
    }

     */

}
