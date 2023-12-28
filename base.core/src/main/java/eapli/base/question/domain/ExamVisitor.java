package eapli.base.question.domain;

import eapli.base.question.domain.ANTLRExam.ExamANTLRBaseVisitor;
import eapli.base.question.domain.ANTLRExam.ExamANTLRParser;
import eapli.framework.io.util.Console;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ExamVisitor extends ExamANTLRBaseVisitor<String> {
    List<String> answers = new ArrayList<>();
    int grade=0;

    public int getGrade(){
        return grade;
    }

    @Override
    public String visitExam(ExamANTLRParser.ExamContext ctx) {
        // Visit the exam_body rule
        return visitExam_body(ctx.exam_body());
    }

    @Override
    public String visitExam_body(ExamANTLRParser.Exam_bodyContext ctx) {
        int identifier = Integer.parseInt(ctx.exam_id.getText());
        String title = ctx.exam_title.getText();
        String openDate = ctx.open_date.getText();
        String closeDate = ctx.close_date.getText();

        Set<String> sections = new HashSet<>();

        for (ExamANTLRParser.SectionContext sectionContext : ctx.section()) {
           sections.add(visitSection(sectionContext));
        }

        String res = "Exam ID: " + identifier + "\nTitle: " + title + "\nSections: " + sections + "\nOpen Date: " + openDate + "\nClose Date: " + closeDate + "\n";

        answers.add("\n");
        System.out.println();
        System.out.println("Grade : "+ grade);

        return res;
    }

    @Override
    public String visitSection(ExamANTLRParser.SectionContext ctx) {
        int identifier = Integer.parseInt(ctx.section_id.getText());
        String description = ctx.section_des.getText();
        String difficulty = ctx.section_difficulty.getText();
        String limit = ctx.limit.getText();
        Set<String> questions = new HashSet<>();
        for (ExamANTLRParser.QuestionContext questionContext : ctx.question()) {
            questions.add(visitQuestion(questionContext));
        }

        return "Section ID: " + identifier + "\nDescription: " + description + "\nDifficulty: " + difficulty + "\nLimit: " + limit + "\nQuestions: " + questions + "\n";
    }

    @Override
    public String visitQuestion(ExamANTLRParser.QuestionContext ctx) {
        int identifier = Integer.parseInt(ctx.question_id.getText());
        String statement = ctx.question_statement.getText();
        int difficulty = Integer.parseInt(ctx.ques_difficulty.getText());
        int quotation = Integer.parseInt(ctx.ques_quotation.getText());


        System.out.println("Statement : "+ statement);
        String typeQuestion = ctx.body().type_question.getText();

        if(visitBodyAndCorrect(ctx.body())){
            grade += quotation;
        }

        return "Question ID: " + identifier + "\nStatement: " + statement + "\nType Of Question: " + typeQuestion + "\nDifficulty: " + difficulty + "\nQuotation: " + quotation + "\n";

    }

    public boolean visitBodyAndCorrect(ExamANTLRParser.BodyContext ctx){
        String answer = null;
        String correctAnswer = null;
        int cotation=0;

        String typeQuestion = ctx.type_question.getText();
        System.out.println("Type Of Question : "+ typeQuestion);
        switch (typeQuestion){
            case ("Matching"):
                return visitMatchingCorrect(ctx.matching());
            case ("True or False"):
                answer = Console.readLine("Answer(True/False) : ");
                correctAnswer = visitTrue_false(ctx.true_false());
                answers.add(answer);
                return answer.equals(correctAnswer);
            case ("Missing Words"):
                return visitMissing_wordsCorrect(ctx.missing_words());
            case ("Multiple Choice"):
                int i=0;
                for (ExamANTLRParser.Multiple_choice_optionContext option :ctx.multiple_choice().multiple_choice_option()) {
                    System.out.println(option.getText());
                    i++;
                }
                answer = Console.readLine("Answer : ");
                correctAnswer = visitMultiple_choice(ctx.multiple_choice());
                answers.add(answer);
                return answer.equals(correctAnswer);
            case ("Numerical"):
                answer = Console.readLine("Answer : ");
                correctAnswer = visitNumerical(ctx.numerical());
                answers.add(answer);
                return answer.equals(correctAnswer);
            case ("Short Answer"):
                answer = Console.readLine("Answer : ");
                answers.add(answer);
                correctAnswer = visitShort_awnser(ctx.short_awnser());
                return answer.equals(correctAnswer);
            default:
                break;

        }
        answers.add(answer);
        return false;
    }

    @Override
    public String visitTrue_false(ExamANTLRParser.True_falseContext ctx) {
        return ctx.trueOrFalse_answer.getText();
    }

    public boolean visitMissing_wordsCorrect(ExamANTLRParser.Missing_wordsContext ctx) {
        int i=-1;
        Map<Integer,String> map = new TreeMap<>();
        Map<Integer,String> resMap = new TreeMap<>();

        for (ExamANTLRParser.Missing_answerContext missingAnswerContext : ctx.missing_answer()){
            i++;
            String word = Console.readLine("Answer : ");
            map.put(i,word);
            String answer = String.format("Answer : " + word);
            answers.add(answer);

            String resWord = ctx.missing_answer(i).miss_answer.getText();
            resMap.put(i,resWord);

        }
        return map.equals(resMap);
    }

    @Override
    public String visitMissing_answer(ExamANTLRParser.Missing_answerContext ctx) {
        return ctx.miss_answer.getText();
    }

    @Override
    public String visitNumerical(ExamANTLRParser.NumericalContext ctx) {
        return ctx.num_answer.getText();
    }

    @Override
    public String visitShort_awnser(ExamANTLRParser.Short_awnserContext ctx) {
        return ctx.short_answer.getText();
    }

    @Override
    public String visitMultiple_choice(ExamANTLRParser.Multiple_choiceContext ctx) {
        return ctx.multiple_choice_answer.getText();
    }

    @Override
    public String visitMultiple_choice_option(ExamANTLRParser.Multiple_choice_optionContext ctx) {
        return ctx.multiple_option.getText();
    }


    public boolean visitMatchingCorrect(ExamANTLRParser.MatchingContext ctx) {
        int i=-1;
        Map<String,String> map = new TreeMap<>();
        Map<String,String> resMap = new TreeMap<>();
        i=0;
        for (ExamANTLRParser.Matching_tokenAContext matchingA : ctx.matching_tokenA()) {
            i++;
            System.out.println("Table A " + i + ": " + matchingA.match_answer.getText());
        }
        i =0;
        for (ExamANTLRParser.Matching_tokenBContext matchingB : ctx.matching_tokenB()) {
            i++;
            System.out.println("Table B " + i + ": " + matchingB.match_answer.getText());
        }

        i=-1;
        for (ExamANTLRParser.Matching_answersContext matchingContext : ctx.matching_answers()){
            System.out.println("\nAnswer:");
            String tableA = Console.readLine("Table A : ");
            String tableB = Console.readLine("Table B : ");
            map.put(tableA,tableB);
            String answer = String.format("Table A : " + tableA + " Table B : " + tableB);
            answers.add(answer);

            i++;
            String currentTableA = ctx.matching_answers(i).tableA_ans.getText();
            String currentTableB = ctx.matching_answers(i).tableB_ans.getText();
            resMap.put(currentTableA,currentTableB);

        }
        return map.equals(resMap);
    }

}
