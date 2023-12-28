package eapli.base.app.other.console.authz;

import eapli.base.course.domain.Course;
import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.*;
import eapli.base.question.domain.*;
import eapli.base.question.domain.Difficulty;
import eapli.base.usermanagement.application.AddFormativeQuestionsController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CreateExamUI extends AbstractUI {

    ExamController examController = new ExamController();
    AddFormativeQuestionsController theController = new AddFormativeQuestionsController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {

        System.out.println("Choose the course you want to create an Exam for:");
        Course course = getCourse();

        if (course != null) {

            String path = Console.readLine("Insert the file path of the exam");

            String title = null;
            String statement = null;
            TypeOfQuestion typeOfQuestion = null;
            Answer answer = null;
            int quotation = 0;
            int difficulty = 0, difficultySection = 0;
            OpenDate openDate = null;
            CloseDate closeDate = null;
            String textualDescription = null;
            LinkedHashSet<String> setA = new LinkedHashSet<>();
            LinkedHashSet<String> setB = new LinkedHashSet<>();
            LinkedHashSet<Answer> answerLinkedHashSet = new LinkedHashSet<>();
            LinkedHashSet<String> options = new LinkedHashSet<>();
            LinkedHashSet<Section> sections = new LinkedHashSet<>();

            try {
                if (examController.examValidation(path)) {
                    int numSections = countSections(path);

                    try {
                        Scanner scanner = new Scanner(new File(path));
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine().trim();
                            if (line.startsWith("Title:")) {
                                title = line.split(":")[1].trim();
                            } else if (line.startsWith("Section ID:")) {

                                for (int i = 0; i < numSections; i++) {
                                    LinkedHashSet<Question> questions = new LinkedHashSet<>();
                                    line = scanner.nextLine().trim();
                                    line = scanner.nextLine().trim();
                                    if (line.startsWith("Textual Description:")) {
                                        textualDescription = line.split(":")[1].trim();
                                    }
                                    line = scanner.nextLine().trim();
                                    if (line.startsWith("Difficulty:")) {
                                        difficultySection = Integer.parseInt(line.split(":")[1].trim());
                                    }
                                    line = scanner.nextLine().trim();
                                    if (line.startsWith("Limit of Questions:")) {
                                        int limit = Integer.parseInt(line.split(":")[1].trim());
                                    }
                                    line = scanner.nextLine().trim(); //ler Questions:

                                    int numQuestions = countQuestions(path, i + 1);
                                    for (int j = 0; j < numQuestions; j++) {
                                        line = scanner.nextLine().trim(); //ler linha
                                        line = scanner.nextLine().trim(); //ler Question
                                        line = scanner.nextLine().trim(); //ler ID:
                                        line = scanner.nextLine().trim();
                                        if (line.startsWith("Statement:")) {
                                            statement = line.split(":")[1].trim();
                                        }
                                        line = scanner.nextLine().trim();
                                        if (line.startsWith("Type of Question:")) {
                                            String typeOfQuestions = line.split(":")[1].trim().toUpperCase();
                                            if (typeOfQuestions.equals(TypeOfQuestion.MATCHING.toString())) {
                                                typeOfQuestion = TypeOfQuestion.MATCHING;
                                                String l = scanner.nextLine().trim();
                                                if (l.startsWith("Table A")) {
                                                    l = scanner.nextLine().trim();
                                                    while (l.startsWith("Statement")) {
                                                        setA.add(l.split(":")[1]);
                                                        l = scanner.nextLine().trim();
                                                    }
                                                    l = scanner.nextLine().trim();
                                                }
                                                if (l.startsWith("Table B")) {
                                                    l = scanner.nextLine().trim();
                                                    while (l.startsWith("Statement")) {
                                                        setB.add(l.split(":")[1]);
                                                        l = scanner.nextLine().trim();
                                                    }
                                                }
                                                line = scanner.nextLine();
                                                if (line.startsWith("Answer:")) {
                                                    String aux = String.format("%s: %s: %s: %s: %s",line.split(":")[1].trim(),line.split(":")[2].trim(),line.split(":")[3].trim(),line.split(":")[4].trim(),line.split(":")[5].trim());
                                                    answer = new Answer(aux);
                                                }

                                            } else if (typeOfQuestions.equals("MISSING WORDS")) {
                                                typeOfQuestion = TypeOfQuestion.MISSING_WORDS;
                                                line = scanner.nextLine().trim();
                                                line = scanner.nextLine().trim();
                                                while (line.startsWith("Answer")) {
                                                    answerLinkedHashSet.add(new Answer(line.split(":")[1].trim()));
                                                    line = scanner.nextLine().trim();
                                                }
                                            } else if (typeOfQuestions.equals(TypeOfQuestion.NUMERICAL.toString())) {
                                                typeOfQuestion = TypeOfQuestion.NUMERICAL;
                                                line = scanner.nextLine().trim();
                                                if (line.startsWith("Answer:")) {
                                                    answer = new Answer(line.split(":")[1].trim());
                                                }
                                            } else if (typeOfQuestions.equals("SHORT ANSWER")) {
                                                typeOfQuestion = TypeOfQuestion.SHORT_ANSWER;
                                                line = scanner.nextLine().trim();
                                                if (line.startsWith("Answer:")) {
                                                    answer = new Answer(line.split(":")[1].trim());
                                                }
                                            } else if (typeOfQuestions.equals("MULTIPLE CHOICE")) {
                                                typeOfQuestion = TypeOfQuestion.MULTIPLE_CHOICE;
                                                line = scanner.nextLine().trim();
                                                line = scanner.nextLine().trim();
                                                while (line.startsWith("Option")) {
                                                    line = line.substring(0,line.length()-1);
                                                    options.add(line.split(":")[1].trim());
                                                    line = scanner.nextLine().trim();
                                                }
                                                line = scanner.nextLine().trim();
                                                if (line.startsWith("Answer:")) {
                                                    answer = new Answer(line.split(":")[1].trim());
                                                }

                                            } else if (typeOfQuestions.equals("TRUE OR FALSE")) {
                                                typeOfQuestion = TypeOfQuestion.TRUE_FALSE;
                                                line = scanner.nextLine().trim();
                                                if (line.startsWith("Answer:")) {
                                                    answer = new Answer(line.split(":")[1].trim());
                                                }
                                            }
                                            line = scanner.nextLine().trim();
                                            if (line.startsWith("Quotation:")) {
                                                quotation = Integer.parseInt(line.split(":")[1].trim());
                                            }
                                            line = scanner.nextLine().trim();
                                            if (line.startsWith("Difficulty:")) {
                                                difficulty = Integer.parseInt(line.split(":")[1].trim());
                                            }
                                            switch (typeOfQuestion) {
                                                case MATCHING:
                                                    Matching matching = new Matching(statement, setA, setB, quotation, false, new Difficulty(difficulty), answer);
                                                    questions.add(matching);
                                                    break;
                                                case MISSING_WORDS:
                                                    MissingWords missingWords = new MissingWords(statement, answerLinkedHashSet, quotation, false, new Difficulty(difficulty));
                                                    questions.add(missingWords);
                                                    break;
                                                case NUMERICAL:
                                                    Numerical numerical = new Numerical(statement, answer, quotation, false, new Difficulty(difficulty));
                                                    questions.add(numerical);
                                                    break;
                                                case SHORT_ANSWER:
                                                    ShortAnswer shortAnswer = new ShortAnswer(statement, answer, quotation, false, new Difficulty(difficulty));
                                                    questions.add(shortAnswer);
                                                    break;
                                                case MULTIPLE_CHOICE:
                                                    MultipleChoice multipleChoice = new MultipleChoice(statement, options, answer, quotation, false, new Difficulty(difficulty));
                                                    questions.add(multipleChoice);
                                                    break;
                                                case TRUE_FALSE:
                                                    TrueOrFalse trueOrFalse = new TrueOrFalse(statement, answer, quotation, false, new Difficulty(difficulty));
                                                    questions.add(trueOrFalse);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    Section section = new Section(null, new TextualDescription(textualDescription), questions, new eapli.base.exam.domain.Difficulty(difficultySection));
                                    sections.add(section);
                                    line = scanner.nextLine().trim();
                                    line = scanner.nextLine().trim();
                                    line = scanner.nextLine().trim();
                                }
                            } else if (line.startsWith("Open Date:")) {
                                String auxOpenDate = line.split(":")[1].trim();
                                int day = Integer.parseInt(auxOpenDate.split("/")[0].trim());
                                int month = Integer.parseInt(auxOpenDate.split("/")[1].trim());
                                String auxYear = auxOpenDate.split("/")[2].trim();
                                int year = Integer.parseInt(auxYear.split(" ")[0].trim());
                                int hour = Integer.parseInt(auxYear.split(" ")[1].trim());
                                int minutes = Integer.parseInt(line.split(":")[2].trim());

                                openDate = new OpenDate(day, month, year, hour, minutes);
                            } else if (line.startsWith("Close Date:")) {
                                String auxCloseDate = line.split(":")[1].trim();
                                int day = Integer.parseInt(auxCloseDate.split("/")[0].trim());
                                int month = Integer.parseInt(auxCloseDate.split("/")[1].trim());
                                String auxYear = auxCloseDate.split("/")[2].trim();
                                int year = Integer.parseInt(auxYear.split(" ")[0].trim());
                                int hour = Integer.parseInt(auxYear.split(" ")[1].trim());
                                int minutes = Integer.parseInt(line.split(":")[2].trim());

                                closeDate = new CloseDate(day, month, year, hour, minutes);
                            }
                            setA.clear();
                            setB.clear();
                            answerLinkedHashSet.clear();
                            options.clear();
                        }
                        scanner.close();
                        Exam exam = examController.addExam(title, course, sections, openDate, closeDate,path);
                        examController.saveExam(exam);
                        System.out.println();
                        System.out.println("Your exam was successfully created!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Your exam is not valid!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }


    public Course getCourse(){
        List<Course> courses = examController.getCourseService();
        int option = -1;

        if(courses.isEmpty()){
            System.out.println("Does not exist any course created!");
            return null;
        }

        boolean isValidOption = false;
        do {
            try {
                for (int i = 0; i <  courses.size(); i++) {
                    System.out.printf("[%d] -> Id:%d Title:%s\n",i+1,courses.get(i).getId(),courses.get(i).getTitle());
                }
                option = Console.readInteger("Option: ");
                System.out.println();
                if (option>0 || option <courses.size()){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the course: " + e.getMessage());
            }
        }while (!isValidOption);

        return courses.get(option-1);

    }

    public static int countSections(String path){

        int cont = 0;

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Section ID:")) {
                    cont++;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return cont;
    }

    public static int countQuestions(String path, int section){

        int cont = -1;

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Section ID: "+section)) {
                    line = scanner.nextLine().trim();
                    while (scanner.hasNextLine()) {
                        if (line.startsWith("Section ID:")){
                            break;
                        }
                        line = scanner.nextLine().trim();
                        if (line.startsWith("Question"))
                            cont++;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return cont;
    }

    @Override
    public String headline() {
        return "Create an Exam";
    }
}
