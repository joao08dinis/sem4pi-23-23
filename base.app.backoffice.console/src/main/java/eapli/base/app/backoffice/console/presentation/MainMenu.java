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
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.authz.*;
import eapli.base.app.backoffice.console.presentation.authz.importer.ImportStudentsAction;
import eapli.base.app.backoffice.console.presentation.authz.importer.ImportStudentsUI;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int COURSE_OPTION = 3;
    private static final int LIST_PROFILES_OPTION = 3;
    private static final int DEACTIVATE_USER_OPTION = 4;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 5;


    //Courses
    private static final int CREATE_COURSE_OPTION = 1;

    private static final int OPEN_CLOSE_ENROLLEMENTS_COURSE_OPTION = 2;

    private static final int OPEN_CLOSE_COURSES_OPTION = 3;

    private static final int SET_TEACHERS_COURSE = 4;

    private static final int ACCEPT_ENROLLMENTS = 5;

    private static final int IMPORT_STUDENTS = 6;

    //Shared Board

    private static final int CREATE_SHARED_BOARD=1;

    private static final int VIEW_HISTORY = 5;



    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;

    private static  final int SHARED_BOARD=4;
    private static  final int MEETING=5;
    private static final int SETTINGS_OPTION = 6;
    private static final int SCHEDULE_MEETING=1;
    private static final int LIST_PARTICIPANTS = 3;
    private static final int CANCEL_MEETING=2;
    private static final String SEPARATOR_LABEL = "--------------";

    private static final int POST_SHARED_BOARD=2;
    private static final int SHARE_SHAREDBOARD = 3;
    private static final int ARCHIVE_SHAREDBOARD = 4;
    private static final int CHANGE_POST=6;
    private static final int SERVER_SHARED_BOARD=7;
    private static final int UNDO_POST_IT=8;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu courseMenu = buildCoursesMenu();
            mainMenu.addSubMenu(COURSE_OPTION, courseMenu);
            final Menu sharedboardmenu = buildSharedBoardMenu();
            mainMenu.addSubMenu(SHARED_BOARD,sharedboardmenu);
            final Menu meetingmenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETING,meetingmenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildSharedBoardMenu() {
        final Menu menu=new Menu("SharedBoard >");
        menu.addItem(CREATE_SHARED_BOARD, "Create a SharedBoard", new CreateSharedBoardAction());
        menu.addItem(POST_SHARED_BOARD,"Make a post in a SharedBoard", new PostSharedBoardAction());
        menu.addItem(SHARE_SHAREDBOARD, "Share a SharedBoard", new SharedBoardAction());
        menu.addItem(ARCHIVE_SHAREDBOARD, "Archive a SharedBoard", new ArchiveBoardAction());
        menu.addItem(VIEW_HISTORY, "View history of a SharedBoard", new ViewHistorySharedBoardAction());
        menu.addItem(CHANGE_POST,"Change a post",new ChangePostSharedBoardAction());
        menu.addItem(SERVER_SHARED_BOARD,"Server SharedBoard",new HttpServerAction());
        menu.addItem(UNDO_POST_IT,"Undo a Post-It",new UndoPostItAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);


        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserAction());
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(LIST_PROFILES_OPTION, "List Profiles", new ListProfilesAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");

        menu.addItem(CREATE_COURSE_OPTION, "Create Course", new AddCourseAction());
        menu.addItem(OPEN_CLOSE_ENROLLEMENTS_COURSE_OPTION, "Open/Close Course Enrollments",new OpenCloseCourseEnrollementsAction());
        menu.addItem(OPEN_CLOSE_COURSES_OPTION, "Open/Close Course",new OpenCloseCourseAction());
        menu.addItem(SET_TEACHERS_COURSE, "Set Teachers of a Course", new AddTeacherRegistrationAction());
        menu.addItem(ACCEPT_ENROLLMENTS, "Accept/Deny course enrollments", new AcceptAction());
        menu.addItem(IMPORT_STUDENTS, "Enroll Students using csv file", new ImportStudentsAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMeetingMenu(){
        final Menu menu=new Menu("Meetings >");
        menu.addItem(SCHEDULE_MEETING,"Schedule Meeting",new ScheduleMeetingAction());
        menu.addItem(CANCEL_MEETING,"Cancel Meeting",new CancelMeetingAction());
        menu.addItem(LIST_PARTICIPANTS,"List Participants in my Meeting", new ListParticipantsInMeetingAction());
        menu.addItem(EXIT_OPTION,RETURN_LABEL,Actions.SUCCESS);
        return menu;
    }




}
