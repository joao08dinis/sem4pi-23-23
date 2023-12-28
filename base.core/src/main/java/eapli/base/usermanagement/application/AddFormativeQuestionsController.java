package eapli.base.usermanagement.application;

import eapli.base.question.domain.*;
import eapli.base.question.service.AddFormativeQuestionsService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.LinkedHashSet;
import java.util.Set;

@UseCaseController
public class AddFormativeQuestionsController {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final AddFormativeQuestionsService addSvc = new AddFormativeQuestionsService();

    public Matching addMatching(String question, Set<String> table1, Set<String> table2, double quotation,Difficulty difficulty,Answer answer) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addMatchingQuestion(question, table1, table2, quotation,difficulty,answer);
    }

    public ShortAnswer addShortAnswer(String question, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addShortQuestion(question, answer, quotation,difficulty);
    }

    public Numerical addNumerical(String question, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addNumericQuestion(question, answer, quotation,difficulty);
    }

    public TrueOrFalse addTrueOrFalse(String question, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addTrueFalseQuestion(question, answer, quotation,difficulty);
    }

    public MultipleChoice addMultipleChoice(String question, LinkedHashSet<String> options, Answer answer, double quotation,Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addMultipleChoiceQuestion(question, options, answer, quotation,difficulty);
    }

    public MissingWords addMissingWords(String sentence, LinkedHashSet<Answer> answers, double quotation, Difficulty difficulty) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return addSvc.addMissingWordsQuestion(sentence, answers, quotation,difficulty);
    }
}
