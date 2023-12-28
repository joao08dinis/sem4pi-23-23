package eapli.base.app.other.console.authz;

import eapli.base.question.domain.Answer;
import eapli.base.question.domain.Difficulty;
import eapli.base.question.domain.Question;
import eapli.base.usermanagement.application.AddFormativeQuestionsController;
import eapli.framework.io.util.Console;

import java.util.*;

public class AddFormativeQuestionsUI extends AbstractList {

    private AddFormativeQuestionsController theController = new AddFormativeQuestionsController();

    protected boolean doShow() {
        Question question = null;
        String statement;
        LinkedHashSet<Answer> answers;
        int total;
        LinkedHashSet<String> tableA, tableB, options;

        System.out.println("1 - Matching\n2 - Missing Words\n3 - Multiple Choice\n4 - Numerical\n5 - Short Answer\n6 - True or False");
        int questionType = Console.readInteger("Question Type:");
        switch (questionType) {
            case 1:
                statement = getStatement();
                tableA = new LinkedHashSet<>();
                tableB = new LinkedHashSet<>();
                total = Console.readInteger("- Total associations:");
                for (int i = 0; i < total; i++) {
                    System.out.println("--Association -> " + i);
                    tableA.add(Console.readLine("-- Table1:"));
                    tableB.add(Console.readLine("-- Table2:"));
                }

                String answer = Console.readLine("Answer(Association seperated by '-'): ");

                question = theController.addMatching(statement, tableA, tableB, getQuotation(),getDifficulty(),new Answer(answer));
                break;

            case 2:
                total = Console.readInteger("- Total Missing Words:");
                statement = getStatement();
                answers = new LinkedHashSet<>();
                for (int i = 0; i < total; i++) {
                    answers.add(new Answer(Console.readLine("-- Answer" + i + ":")));
                }

                question = theController.addMissingWords(statement, answers, getQuotation(),getDifficulty());
                break;

            case 3:
                statement = getStatement();
                total = Console.readInteger("- Total options:");
                options = new LinkedHashSet<>();
                for (int i = 0; i < total; i++) {
                    System.out.println("-- Option -> " + i);
                    options.add(Console.readLine("-- Option:"));
                }

                question = theController.addMultipleChoice(statement, options, getAnswer(), getQuotation(),getDifficulty());
                break;

            case 4:
                question = theController.addNumerical(getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;

            case 5:
                question = theController.addShortAnswer(getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;

            case 6:
                question = theController.addTrueOrFalse(getStatement(), getAnswer(), getQuotation(),getDifficulty());
                break;

        }
        System.out.println("-> Question with ID " + question.identity() + " was created");

        return false;
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
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
