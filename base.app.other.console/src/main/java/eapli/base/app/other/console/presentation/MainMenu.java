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
package eapli.base.app.other.console.presentation;

import eapli.base.app.other.console.authz.*;
import eapli.base.Application;
import eapli.base.profile.controller.ProfileController;
import eapli.base.profile.domain.TeacherProfile;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
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

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;

    private static final String RETURN = "Return ";

    private static final int SCHEDULE_CLASS = 1;

    private static final int SCHEDULE_EXTRAORDINARY_CLASS=3;

    private static final int CLASS_MENU = 1;
    private static final int UPDATE_CLASS = 2;
    private static final int SCHEDULE_MEETING = 1;
    private static final int SERVER_SHARED_BOARD=7;
    private static final int UNDO_POST_IT=8;

    private static final int BOARD_MENU = 9;

    private static final int LIST_PARTICIPANTS = 4;
    private static final int CANCEL_MEETING=2;
    private static final int VIEW_COURSES = 1;

    private static final int VIEW_COURSES_EXAM = 2;

    private static final int MEETINGS=2;
    private static final int SHARED_BOARD=3;
    private static final int COURSES = 4;

    private static final int ACCEPT_REJECT_MEETING=3;

    private static final int EXAMS = 5;
    private static final int CREATE_EXAM=1;
    private static final int ADD_QUESTIONS=2;
    private static final int UPDATE_QUESTIONS=3;
    private static final int CREATE_AUTO_EXAM=4;
    private static final int VIEW_GRADES = 5;
    private static final int CREATE_MANUAL_EXAM=5;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int SALES_OPTION = 7;

    private static final int RECHARGE_USER_CARD_OPTION = 1;

    private static final int CREATE_SHARED_BOARD = 1;
    private static final int POST_SHARED_BOARD=2;
    private static final int SHARE_SHAREDBOARD = 3;
    private static final int ARCHIVE_SHAREDBOARD = 4;
    private static final int VIEW_HISTORY = 5;
    private static final int CHANGE_POST=6;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final Menu menu;
    private final MenuRenderer renderer;
    ProfileController profileController = new ProfileController();
    TeacherProfile currentUserProfile = (TeacherProfile) profileController.getUserProfile(authz.session().get().authenticatedUser()).get();

    public MainMenu() {
        menu = buildMainMenu();
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        System.out.println("USER INFO:");
        System.out.println("ID: " + currentUserProfile.getId());
        System.out.println("ACRONYM: " + currentUserProfile.getAcronym());
        System.out.println("TAX NR: " + currentUserProfile.getTaxPayerNumber().toString());
        System.out.println("DATE OF BIRTH: " + currentUserProfile.getDateOfBirth().toString());


        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        final Menu mySharedBoardMenu = buildingSharedBoardMenu();
        final Menu classMenu = buildingClassMenu();
        final Menu coursesMenu = buildCoursesMenu();
        final Menu examsMenu =buildingExamMenu();
        final Menu meetingsMenu=buildMeetingMenu();
        mainMenu.addSubMenu(CLASS_MENU, classMenu);
        mainMenu.addSubMenu(MEETINGS,meetingsMenu);
        mainMenu.addSubMenu(SHARED_BOARD, mySharedBoardMenu);
        mainMenu.addSubMenu(COURSES, coursesMenu);
        mainMenu.addSubMenu(EXAMS, examsMenu);

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildingSharedBoardMenu() {
        final Menu menu = new Menu("SharedBoard >");
        menu.addItem(CREATE_SHARED_BOARD, "Create a SharedBoard", new CreateSharedBoardAction());
        menu.addItem(POST_SHARED_BOARD,"Make a post in a SharedBoard", new PostSharedBoardAction());
        menu.addItem(SHARE_SHAREDBOARD, "Share a SharedBoard", new SharedBoardAction());
        menu.addItem(ARCHIVE_SHAREDBOARD, "Archive a SharedBoard", new ArchiveBoardAction());
        menu.addItem(VIEW_HISTORY, "View history of a SharedBoard", new ViewHistorySharedBoardAction());
        menu.addItem(CHANGE_POST,"Change a post",new ChangePostSharedBoardAction());
        menu.addItem(SERVER_SHARED_BOARD,"Server SharedBoard",new HttpServerAction());
        menu.addItem(UNDO_POST_IT,"Undo a Post-It",new UndoPostItAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }

    private Menu buildingClassMenu() {
        final Menu menu = new Menu("Classes >");
        menu.addItem(SCHEDULE_CLASS, "Schedule Class", new ScheduleClassAction());
        menu.addItem(UPDATE_CLASS, "Update Class Schedule", new UpdateClassScheduleAction());
        menu.addItem(SCHEDULE_EXTRAORDINARY_CLASS, "Schedule extraordionary Class", new ScheduleExtraordinaryAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");
        menu.addItem(VIEW_COURSES, "View Courses" , new ViewCoursesAction());
        menu.addItem(VIEW_COURSES_EXAM, "View Courses Exams", new ViewCoursesExamAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }


    private Menu buildingExamMenu() {
        final Menu menu = new Menu("Exam >");
        menu.addItem(CREATE_EXAM, "Create an Exam", new CreateExamAction()); //TO IMPLEMENT
        menu.addItem(ADD_QUESTIONS, "Add Questions", new AddQuestionsAction());
        menu.addItem(UPDATE_QUESTIONS, "Update Questions", new UpdateQuestionsAction());
        menu.addItem(CREATE_AUTO_EXAM, "Create an Automatic Exam", new AddAutomaticFormativeAction());
        menu.addItem(VIEW_GRADES, "View Course Grades", new ViewGradesAction());
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }

    private Menu buildMeetingMenu(){
        final Menu menu=new Menu("Meetings >");
        menu.addItem(SCHEDULE_MEETING,"Schedule Meeting",new ScheduleMeetingAction());
        menu.addItem(CANCEL_MEETING,"Cancel Meeting",new CancelMeetingAction());
        menu.addItem(ACCEPT_REJECT_MEETING,"Accept or Reject meeting",new AcceptMeetingRequestAction());
        menu.addItem(LIST_PARTICIPANTS,"List Participants in my Meeting", new ListParticipantsInMeetingAction());
        menu.addItem(EXIT_OPTION,RETURN,Actions.SUCCESS);
        return menu;
    }



}
