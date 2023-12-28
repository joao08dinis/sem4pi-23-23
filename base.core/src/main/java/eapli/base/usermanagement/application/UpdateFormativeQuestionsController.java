package eapli.base.usermanagement.application;

import eapli.base.question.domain.*;
import eapli.base.question.service.UpdateFormativeQuestionsService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.LinkedHashSet;
import java.util.Set;

@UseCaseController
public class UpdateFormativeQuestionsController {
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final UpdateFormativeQuestionsService updateSvc = new UpdateFormativeQuestionsService();

    public Matching updateMatchingQuestion(Matching question, String statement, Set<String> table1, Set<String> table2, double quotation,Difficulty difficulty,Answer answer) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateMatchingQuestion(question, statement, table1, table2, quotation,difficulty,answer);
    }

    public ShortAnswer updateShortQuestion(ShortAnswer question, String statement, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateShortQuestion(question, statement, answer, quotation,difficulty);
    }

    public Numerical updateNumericQuestion(Numerical question, String statement, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateNumericQuestion(question, statement, answer, quotation,difficulty);
    }

    public TrueOrFalse updateTrueFalseQuestion(TrueOrFalse question, String statement, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateTrueFalseQuestion(question, statement, answer, quotation,difficulty);
    }

    public MultipleChoice updateMultipleChoiceQuestion(MultipleChoice question, String statement, LinkedHashSet<String> options, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateMultipleChoiceQuestion(question, statement, options, answer, quotation,difficulty);
    }

    public MissingWords updateMissingWordsQuestion(MissingWords question, String statement, LinkedHashSet<Answer> answers, double quotation, Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return updateSvc.updateMissingWordsQuestion(question, statement, answers, quotation,difficulty);
    }

    public Matching selectMatchingQuestion(Long questionId) {
        return (Matching) updateSvc.selectMatchingQuestion(questionId);
    }

    public ShortAnswer selectShortQuestion(Long questionId) {
        return updateSvc.selectShortQuestion(questionId);
    }

    public Numerical selectNumericQuestion(Long questionId) {
        return updateSvc.selectNumericQuestion(questionId);
    }

    public TrueOrFalse selectTrueFalseQuestion(Long questionId) {
        return updateSvc.selectTrueFalseQuestion(questionId);
    }

    public MultipleChoice selectMultipleChoiceQuestion(Long questionId) {
        return updateSvc.selectMultipleChoiceQuestion(questionId);
    }

    public MissingWords selectMissingWordsQuestion(Long questionId) {
        return updateSvc.selectMissingWordsQuestion(questionId);
    }

    public Iterable<Question> findAllMatchingQuestionsForAutomaticExams() {
        return updateSvc.findAllMatchingQuestionsForAutomaticExams();
    }

    public Iterable<Question> findAllShortQuestionsForAutomaticExams() {
        return updateSvc.findAllShortQuestionsForAutomaticExams();
    }

    public Iterable<Question> findAllNumericQuestionsForAutomaticExams() {
        return updateSvc.findAllNumericQuestionsForAutomaticExams();
    }

    public Iterable<Question> findAllTrueFalseQuestionsForAutomaticExams() {
        return updateSvc.findAllTrueFalseQuestionsForAutomaticExams();
    }

    public Iterable<Question> findAllMultipleChoiceQuestionsForAutomaticExams() {
        return updateSvc.findAllMultipleChoiceQuestionsForAutomaticExams();
    }

    public Iterable<Question> findAllMissingWordsQuestionsForAutomaticExams() {
        return updateSvc.findAllMissingWordsQuestionsForAutomaticExams();
    }
}
