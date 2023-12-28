package eapli.base.app.other.console.authz;

import eapli.base.question.domain.*;
import eapli.base.usermanagement.application.UpdateFormativeQuestionsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class UpdateFormativeQuestionsUI extends AbstractUI {
    private UpdateFormativeQuestionsController theController = new UpdateFormativeQuestionsController();

    @Override
    protected boolean doShow() {
        Long id;
        Question question = null;
        String statement;
        LinkedHashSet<Answer> answers;
        int total;
        LinkedHashSet<String> tableA, tableB, options;

        System.out.println("1 - Matching\n2 - Missing Words\n3 - Multiple Choice\n4 - Numerical\n5 - Short Answer\n6 - True or False");
        int questionType = Console.readInteger("Question Type:");
        switch (questionType) {
            case 1:
                Iterable<Question> itMatch = theController.findAllMatchingQuestionsForAutomaticExams();
                for (Question q : itMatch) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                Matching matchingQuestionObj = theController.selectMatchingQuestion(id);

                statement = Console.readLine("- Question:");
                tableA = new LinkedHashSet<>();
                tableB = new LinkedHashSet<>();
                total = Console.readInteger("- Total associations:");
                for (int i = 0; i < total; i++) {
                    System.out.println("-- Association -> " + i);
                    tableA.add(Console.readLine("-- Table1:"));
                    tableB.add(Console.readLine("-- Table2:"));
                }
                String ans = Console.readLine("Answer(Associations separated by '-'):");
                question = theController.updateMatchingQuestion(matchingQuestionObj, statement, tableA, tableB, getQuotation(),getDifficulty(),new Answer(ans));
                break;

            case 2:
                Iterable<Question> itMissWords = theController.findAllMissingWordsQuestionsForAutomaticExams();
                for (Question q : itMissWords) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                MissingWords missingWordsQuestionObj = theController.selectMissingWordsQuestion(id);

                total = Console.readInteger("- Total Missing Words:");
                statement = getStatement();
                answers = new LinkedHashSet<>();
                for (int i = 0; i < total; i++) {
                    answers.add(new Answer(Console.readLine("-- Answer_" + i + ":")));
                }
                question = theController.updateMissingWordsQuestion(missingWordsQuestionObj, statement, answers, getQuotation(),getDifficulty());
                break;

            case 3:
                Iterable<Question> itMultiChoice = theController.findAllMultipleChoiceQuestionsForAutomaticExams();
                for (Question q : itMultiChoice) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                MultipleChoice multipleChoiceQuestionObj = theController.selectMultipleChoiceQuestion(id);
                statement = getStatement();
                total = Console.readInteger("- Total options:");
                options = new LinkedHashSet<>();
                for (int i = 0; i < total; i++) {
                    System.out.println("-- Option -> " + i);
                    options.add(Console.readLine("-- Option:"));
                }

                question = theController.updateMultipleChoiceQuestion(multipleChoiceQuestionObj, statement, options, getAnswer(), getQuotation(),getDifficulty());
                break;

            case 4:
                Iterable<Question> itNum = theController.findAllNumericQuestionsForAutomaticExams();
                for (Question q : itNum) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                Numerical numericQuestionObj = theController.selectNumericQuestion(id);

                question = theController.updateNumericQuestion(numericQuestionObj, getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;

            case 5:
                Iterable<Question> itShort = theController.findAllShortQuestionsForAutomaticExams();
                for (Question q : itShort) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                ShortAnswer shortQuestionObj = theController.selectShortQuestion(id);

                question = theController.updateShortQuestion(shortQuestionObj, getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;

            case 6:
                Iterable<Question> itTF = theController.findAllTrueFalseQuestionsForAutomaticExams();
                for (Question q : itTF) {
                    System.out.println("- Id: " + q.identity());
                }
                id = Console.readLong("- Input Question ID:");
                TrueOrFalse trueFalseQuestionObj = theController.selectTrueFalseQuestion(id);

                question = theController.updateTrueFalseQuestion(trueFalseQuestionObj, getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;
        }
        System.out.println("-> Question with ID " + question.identity() + " was updated");

        return false;
    }

    public int getId(){
        return 0;
    }

    public String getStatement(){
        return Console.readLine("- Question:");
    }

    public Answer getAnswer(){
        return new Answer(Console.readLine("- Answer:"));
    }

    public Difficulty getDifficulty(){
        return new Difficulty(Console.readInteger("- Difficulty:"));
    }

    public double getQuotation(){
        return Console.readDouble("- Quotation");
    }

    @Override
    public String headline() {
        return null;
    }
}
