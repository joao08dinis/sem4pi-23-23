package eapli.base.app.user.console.presentation.myuser;

import eapli.base.exam.controller.ExamController;
import eapli.base.exam.domain.Exam;
import eapli.base.exam.domain.Section;
import eapli.base.question.domain.*;
import eapli.base.question.repository.MissingWordsRepository;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TakeAutomaticFormativeExamUI extends AbstractUI {

    private final ExamController theController = new ExamController();

    @Override

    protected boolean doShow() {
        List<Exam> exams= theController.getAll();

        if(exams.isEmpty()){
            System.out.println("Does not exist any exam created!");
            return true;
        }
        System.out.println("Which exam do you want to choose?");
        boolean isValidOption = false;
        int i=1;
        int option=0;
        do {
            try {
                for (Exam exam:exams) {
                    System.out.printf("Exam [%d] -> Id:%d Title:%s\n",i,exam.getId(),exam.getTitle());
                    i++;
                }
                option = Console.readInteger("Option(Choose the exam ID): ");
                System.out.println();
                if (option>0 || option <exams.size()){
                    isValidOption=true;
                }else{
                    System.out.println("Invalid Option!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred on select the meeting: " + e.getMessage());
            }
        }while (!isValidOption);

        Exam chosenExam=null;
        for (Exam exam : exams) {
            if (exam.getId()==option) {
                chosenExam = exam;
                break;
            }
        }
        Iterable<Question> allQuestions= theController.getAllQuestions();
        List<ShortAnswer> shortAnswerQuestions=theController.getShortAnswerQuestions();
        List<Matching> matchingQuestions=theController.getMatchingQuestions();
        List<TrueOrFalse> trueOrFalseQuestions=theController.getTrueOrFalseQuestions();
        List<MultipleChoice> multipleChoiceQuestions=theController.getMultipleChoiceQuestions();
        List<MissingWords> missingWordsQuestions=theController.getMissingWordsQuestions();
        List<Numerical> numericalQuestions=theController.getNumericalQuestions();
        System.out.printf("Exam: %s Teacher: %s\n",chosenExam.getTitle(),chosenExam.getTeacher());
        for (int j=0;j<chosenExam.getSections().size();j++) {
            for (Section section:chosenExam.getSections()){
                System.out.printf("Section: %s\n",section.getDescription());
                Set<Question> questions=section.getQuestions();
                for (Question question:questions) {
                    if (question instanceof TrueOrFalse){
                        System.out.printf("True Or False Question(s)\n");
                        for (TrueOrFalse trueOrFalseQuestion : trueOrFalseQuestions) {
                            if (Objects.equals(question.getId(), trueOrFalseQuestion.getId())) {
                                System.out.printf("Question: %s\n", trueOrFalseQuestion.getStatement());
                                final String answer = Console.readLine("Answer:");
                            }
                        }
                    } else if (question instanceof ShortAnswer) {
                        System.out.printf("Short Answer Question(s)\n");
                        for (ShortAnswer shortAnswerQuestion : shortAnswerQuestions) {
                            if (Objects.equals(question.getId(), shortAnswerQuestion.getId())) {
                                System.out.printf("Question: %s\n", shortAnswerQuestion.getStatement());
                                final String answer = Console.readLine("Answer:");
                            }
                        }
                    } else if (question instanceof Matching) {
                        System.out.printf("Matching Question(s)\n");
                        for (Matching matchingQuestion : matchingQuestions) {
                            if (Objects.equals(question.getId(), matchingQuestion.getId())) {
                                System.out.printf("Question: %s\n", matchingQuestion.getStatement());
                                Set<String> matchingTableSet=matchingQuestion.getTable1();
                                for (String matchingTable: matchingTableSet) {
                                    System.out.printf("Table to Match: %s\n",matchingTable.toString());
                                    final String answer = Console.readLine("Matching Table:");
                                }
                            }
                        }
                    } else if (question instanceof MultipleChoice) {
                        System.out.printf("Multiple Choice Question(s)\n");
                        for (MultipleChoice multipleChoiceQuestion : multipleChoiceQuestions) {
                            if (Objects.equals(question.getId(), multipleChoiceQuestion.getId())) {
                                System.out.printf("Question: %s\n", multipleChoiceQuestion.getStatement());
                                Set<String> multipleChoicesSet=multipleChoiceQuestion.getOptions();
                                for (String multipleChoice: multipleChoicesSet) {
                                    System.out.printf("Choice: %s\n",multipleChoice.toString());
                                }

                               final String answer = Console.readLine("Answer:");
                            }
                        }
                    } else if (question instanceof MissingWords) {
                        System.out.printf("Missing Word Question(s)\n");
                        for (MissingWords missingWordsQuestion : missingWordsQuestions) {
                            if (Objects.equals(question.getId(), missingWordsQuestion.getId())) {
                                System.out.printf("Question: %s\n", missingWordsQuestion.getStatement());
                                int size=missingWordsQuestion.getAnswers().size();
                                for (int k = 0; k <size ; k++) {
                                    String prompt = String.format("Missing Word %d:", k+1);
                                    final String answer = Console.readLine(prompt);
                                }

                            }
                        }
                    } else if (question instanceof Numerical) {
                        System.out.printf("Numerical Question(s)\n");
                        for (Numerical numericalQuestion : numericalQuestions) {
                            if (Objects.equals(question.getId(), numericalQuestion.getId())) {
                                System.out.printf("Question: %s\n", numericalQuestion.getStatement());
                                final String answer = Console.readLine("Answer:");
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Take Automatic Formative Exam";
    }

}
