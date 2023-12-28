/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import java.util.HashSet;
import java.util.Set;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.DateOfBirth;
import eapli.base.profile.domain.TaxPayerNumber;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {
    ProfileController profileController = new ProfileController();

    @Override
    public boolean execute() {
        registerAdmin("admin", TestDataConstants.PASSWORD1, "Jane", "Doe Admin",
                "jane.doe@email.local");
        TaxPayerNumber number = null;
        TaxPayerNumber number1 = null;
        DateOfBirth date = null;
        DateOfBirth date1 = null;
        MecanographicNumber mecanographicNumber = new MecanographicNumber("1211511");
        try {
             number = TaxPayerNumber.from("123456789");
             number1 = TaxPayerNumber.from("123456788");
             date = DateOfBirth.from(1,2,2000);
             date1 = DateOfBirth.from(2,3,2003);
        } catch (BusinessRuleException e) {
            throw new RuntimeException(e);
        }
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.TEACHER);
        final Set<Role> roles1 = new HashSet<>();
        roles1.add(BaseRoles.STUDENT);
        SystemUser user = registerUser("pam", "Abc123", "Pedro", "Campos", "pc@gmail.com", roles);
        profileController.createTeacherProfile(user,null, number, date, "PAM");
        SystemUser user1 = registerUser("student", "Abc123", "Paulo", "Silva", "ps@gmail.com", roles1);
        profileController.createStudentProfile(user1,null, number1, date1, mecanographicNumber);




        return true;
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
