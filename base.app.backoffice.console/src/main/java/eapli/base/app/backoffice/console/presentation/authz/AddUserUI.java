/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.authz;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.DateOfBirth;
import eapli.base.profile.domain.TaxPayerNumber;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.usermanagement.application.AddUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * UI for adding a user to the application.
 *
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    private final ProfileController profileController = new ProfileController();
    Scanner sc = new Scanner(System.in);

    @Override
    protected boolean doShow() {


        SystemUser createdUser = null;

        // FIXME avoid duplication with SignUpUI. reuse UserDataWidget from
        final String username = Console.readLine("Username");
        final String password = Console.readLine("Password");
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("E-Mail");

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            createdUser = this.theController.addUser(username, password, firstName, lastName, email, roleTypes);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        }

        System.out.println("ROLE: " + createdUser.roleTypes().iterator().next().toString());


        if(createdUser.roleTypes().iterator().next().toString().equals("TEACHER")){
            System.out.println("Type the acronym for the teacher: ");
            String acronym = sc.next();
            System.out.println("Type the tax payer number: ");
            String number = sc.next();
            System.out.println("Type the day of birth: ");
            int day = sc.nextInt();
            System.out.println("Type the month of birth: ");
            int month = sc.nextInt();
            System.out.println("Type the year of birth: ");
            int year = sc.nextInt();

            TaxPayerNumber taxPayerNumber = null;
            DateOfBirth dateOfBirth = null;

            try {
                taxPayerNumber = TaxPayerNumber.from(number);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }

            try {
                dateOfBirth = DateOfBirth.from(day, month, year);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }


            profileController.createTeacherProfile(createdUser, null, taxPayerNumber, dateOfBirth, acronym);

            System.out.println("Teacher created sucessfully!");

        } else if(createdUser.roleTypes().iterator().next().toString().equals("STUDENT")){
            System.out.println("Type the mecanographic number: ");
            String mecanographicValue = sc.next();
            System.out.println("Type the tax payer number: ");
            String number = sc.next();
            System.out.println("Type the day of birth: ");
            int day = sc.nextInt();
            System.out.println("Type the month of birth: ");
            int month = sc.nextInt();
            System.out.println("Type the year of birth: ");
            int year = sc.nextInt();

            TaxPayerNumber taxPayerNumber = null;
            DateOfBirth dateOfBirth = null;
            MecanographicNumber mecanographicNumber = null;

            try {
                taxPayerNumber = TaxPayerNumber.from(number);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }

            try {
                dateOfBirth = DateOfBirth.from(day, month, year);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }

            try {
                mecanographicNumber = new MecanographicNumber(mecanographicValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            profileController.createStudentProfile(createdUser, null, taxPayerNumber, dateOfBirth, mecanographicNumber);

            System.out.println("Student created sucessfully!");

        } else if(createdUser.roleTypes().iterator().next().toString().equals("ADMIN")){
            System.out.println("Type the tax payer number: ");
            String number = sc.next();
            System.out.println("Type the day of birth: ");
            int day = sc.nextInt();
            System.out.println("Type the month of birth: ");
            int month = sc.nextInt();
            System.out.println("Type the year of birth: ");
            int year = sc.nextInt();

            TaxPayerNumber taxPayerNumber = null;
            DateOfBirth dateOfBirth = null;

            try {
                taxPayerNumber = TaxPayerNumber.from(number);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }

            try {
                dateOfBirth = DateOfBirth.from(day, month, year);
            } catch (BusinessRuleException e) {
                throw new RuntimeException(e);
            }


            profileController.createAdminProfile(createdUser, null, taxPayerNumber, dateOfBirth);

            System.out.println("Admin created sucessfully!");
        }



        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
