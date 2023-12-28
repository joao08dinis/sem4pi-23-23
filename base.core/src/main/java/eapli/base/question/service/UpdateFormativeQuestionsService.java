package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.*;
import eapli.base.question.repository.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class UpdateFormativeQuestionsService {

    private final QuestionRepository questionRepository = PersistenceContext.repositories().questions();

    public Matching updateMatchingQuestion(Matching question, String statement, Set<String> table1, Set<String> table2, double quotation,Difficulty difficulty,Answer answer) {

        Matching newQuestion = question.clone();

        newQuestion.update(statement, table1, table2, quotation,difficulty,answer);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public ShortAnswer updateShortQuestion(ShortAnswer question, String statement, Answer answer, double quotation,Difficulty difficulty) {

        ShortAnswer newQuestion = question.clone();

        newQuestion.update(statement, answer, quotation,difficulty);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public Numerical updateNumericQuestion(Numerical question, String statement, Answer answer, double quotation,Difficulty difficulty) {

        Numerical newQuestion = question.clone();

        newQuestion.update(statement, answer, quotation,difficulty);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public TrueOrFalse updateTrueFalseQuestion(TrueOrFalse question, String statement, Answer answer, double quotation,Difficulty difficulty) {

        TrueOrFalse newQuestion = question.clone();

        newQuestion.update(statement, answer, quotation,difficulty);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public MultipleChoice updateMultipleChoiceQuestion(MultipleChoice question, String statement, LinkedHashSet<String> options, Answer answer, double quotation,Difficulty difficulty) {

        MultipleChoice newQuestion = question.clone();

        newQuestion.update(statement, options, answer, quotation,difficulty);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public MissingWords updateMissingWordsQuestion(MissingWords question, String statement, LinkedHashSet<Answer> answers, double quotation, Difficulty difficulty) {

        MissingWords newQuestion = question.clone();

        newQuestion.update(statement, answers, quotation,difficulty);

        questionRepository.remove(question);

        questionRepository.save(newQuestion);

        return newQuestion;
    }

    public Matching selectMatchingQuestion(Long questionId) {
        return (Matching) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public ShortAnswer selectShortQuestion(Long questionId) {
        return (ShortAnswer) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public Numerical selectNumericQuestion(Long questionId) {
        return (Numerical) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public TrueOrFalse selectTrueFalseQuestion(Long questionId) {
        return (TrueOrFalse) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public MultipleChoice selectMultipleChoiceQuestion(Long questionId) {
        return (MultipleChoice) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public MissingWords selectMissingWordsQuestion(Long questionId) {
        return (MissingWords) questionRepository.ofIdentity(questionId).orElseThrow();
    }

    public Iterable<Question> findAllMatchingQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }

    public Iterable<Question> findAllShortQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }

    public Iterable<Question> findAllNumericQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }

    public Iterable<Question> findAllTrueFalseQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }

    public Iterable<Question> findAllMultipleChoiceQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }

    public Iterable<Question> findAllMissingWordsQuestionsForAutomaticExams() {
        return questionRepository.findAll();
    }
}
