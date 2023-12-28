package eapli.base.grade.controller;

import eapli.base.exam.domain.Exam;
import eapli.base.grade.domain.Grade;
import eapli.base.grade.domain.TypeOfGrade;
import eapli.base.grade.service.GradeService;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.application.UseCaseController;

import java.util.Set;

@UseCaseController
public class GradeController {
    private final GradeService service = new GradeService();

    public Grade addNewGrade(Exam exam, int grade, TypeOfGrade typeOfGrade) throws BusinessRuleException {
        return Grade.from(null,exam,grade,typeOfGrade);
    }

    public void saveGrade(Grade grade){
        service.addGrade(grade);
    }

    public Set<Grade> getAll(){
        return service.getAll();
    }
}
