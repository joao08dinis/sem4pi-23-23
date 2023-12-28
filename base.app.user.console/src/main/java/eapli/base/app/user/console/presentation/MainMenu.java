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
package eapli.base.app.user.console.presentation;

import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.app.user.console.presentation.myuser.*;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.StudentProfile;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends ClientUserBaseUI {


    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int VIEW_COURSES = 1;
    private static final int REQUEST_ENROLLMENT_COURSE = 2;

    private static final int COURSES_MENU = 2;
    private static final int EXIT_OPTION = 0;

    private static final int SHARED_BOARD=3;
    private static final int SCHEDULE_MEETING=1;

    private static final int EXAMS=6;
    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final int CREATE_SHARED_BOARD=1;
    private static final int POST_SHARED_BOARD=2;

    private static final int SHARE_SHAREDBOARD = 3;
    private static final int ARCHIVE_SHAREDBOARD = 4;

    private static final int VIEW_HISTORY = 5;

    private static final int CHANGE_POST=6;
    private static final int MEETING_MENU=5;
    private static final int LIST_EXAMS_OPTION=3;
    private static final int ACCEPT_REJECT_MEETING=3;
    private static final int CANCEL_MEETING=2;

    private static final int LIST_PARTICIPANTS=4;


    private static final int TAKE_EXAM = 1;
    private static final int VIEW_GRADES = 2;
    private static final int TAKE_FORMATIVE_EXAM = 3;
    private static final int TAKE_AUTOMATIC_FORMATIVE_EXAM=4;

    private static final int GRADES = 7;
    private static final int LIST_GRADES = 2;
    private static final int SERVER_SHARED_BOARD=7;
    private static final int UNDO_POST_IT=8;


    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();

    ProfileController profileController = new ProfileController();
    StudentProfile currentUserProfile = (StudentProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

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
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        System.out.println("USER INFO:");
        System.out.println("ID: " + currentUserProfile.getId());
        System.out.println("MEC NR: " + currentUserProfile.getMecanographicNumber().toString());
        System.out.println("TAX NR: " + currentUserProfile.getTaxPayerNumber().toString());
        System.out.println("DATE OF BIRTH: " + currentUserProfile.getDateOfBirth().toString());
        final Menu myUserMenu = new MyUserMenu();
        final Menu coursesMenu = buildCoursesMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        mainMenu.addSubMenu(COURSES_MENU , coursesMenu);
        final Menu mySharedBoardMenu = buildingSharedBoardMenu();
        mainMenu.addSubMenu(SHARED_BOARD, mySharedBoardMenu);
        final Menu meetingMenu = buildMeetingMenu();
        mainMenu.addSubMenu(MEETING_MENU,meetingMenu);
        final Menu myExamMenu = buildingExamMenu();
        mainMenu.addSubMenu(EXAMS,myExamMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));


        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildingSharedBoardMenu() {
        final Menu menu = new Menu("SharedBoard >");
        menu.addItem(CREATE_SHARED_BOARD, "Create a SharedBoard", new CreateSharedBoardAction());
        menu.addItem(POST_SHARED_BOARD,"Make a post in a SharedBoard", new PostSharedBoardAction());
        menu.addItem(SHARE_SHAREDBOARD, "Share a SharedBoard", new ShareSharedBoardAction());
        menu.addItem(ARCHIVE_SHAREDBOARD, "Archive a SharedBoard" , new ArchiveBoardAction());
        menu.addItem(VIEW_HISTORY, "View History of a SharedBoard", new ViewHistorySharedBoardAction());
        menu.addItem(CHANGE_POST,"Change a post",new ChangePostSharedBoardAction());
        menu.addItem(SERVER_SHARED_BOARD,"Server SharedBoard",new HttpServerAction());
        menu.addItem(UNDO_POST_IT,"Undo a Post-It",new UndoPostItAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");
        menu.addItem(VIEW_COURSES, "View my Courses", new ViewCoursesAction());
        menu.addItem(REQUEST_ENROLLMENT_COURSE, "Request Enrollment in a Course", new AddStudentRegistrationAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }
    private Menu buildMeetingMenu() {
        final Menu menu = new Menu("Meetings >");
        menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingAction());
        menu.addItem(CANCEL_MEETING, "Cancel Meeting", new CancelMeetingAction());
        menu.addItem(ACCEPT_REJECT_MEETING, "Accept or Reject meeting", new AcceptMeetingRequestAction());
        menu.addItem(LIST_PARTICIPANTS, "List Participants in my Meeting", new ListParticipantsInMeetingAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }

    private Menu buildingExamMenu() {
        final Menu menu = new Menu("Exam >");
        menu.addItem(TAKE_EXAM, "Take an Exam", new TakeExamAction());      //To implement
        menu.addItem(LIST_GRADES, "List Grades", new ListGradesAction());
        menu.addItem(LIST_EXAMS_OPTION,"List Exams",new ListExamsAction());
        menu.addItem(TAKE_AUTOMATIC_FORMATIVE_EXAM,"Take Automatic Formative Exam",new TakeAutomaticFormativeExamAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }
}
