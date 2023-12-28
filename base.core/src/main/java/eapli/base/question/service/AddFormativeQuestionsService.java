package eapli.base.question.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.question.domain.*;
import eapli.base.question.repository.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class AddFormativeQuestionsService {
    private final QuestionRepository questionRepository = PersistenceContext.repositories().questions();

    public Matching addMatchingQuestion(String statement, Set<String> table1, Set<String> table2, double quotation,Difficulty difficulty,Answer answer) {

        Matching question = new Matching(statement,table1,table2,quotation,true,difficulty,answer);

        return this.questionRepository.save(question);
    }

    public ShortAnswer addShortQuestion(String statement, Answer answer, double quotation,Difficulty difficulty) {

        ShortAnswer question = new ShortAnswer(statement,answer,quotation,true,difficulty);

        return this.questionRepository.save(question);
    }

    public Numerical addNumericQuestion(String statement, Answer answer, double quotation,Difficulty difficulty) {

        Numerical question = new Numerical(statement,answer,quotation,true,difficulty);

        return this.questionRepository.save(question);
    }

    public TrueOrFalse addTrueFalseQuestion(String statement, Answer answer, double quotation,Difficulty difficulty) {

        TrueOrFalse question = new TrueOrFalse(statement,answer,quotation,true,difficulty);

        return this.questionRepository.save(question);
    }

    public MultipleChoice addMultipleChoiceQuestion(String statement, LinkedHashSet<String> options, Answer answer, double quotation, Difficulty difficulty) {

        MultipleChoice question = new MultipleChoice(statement,options,answer,quotation,true,difficulty);

        return this.questionRepository.save(question);
    }

    public MissingWords addMissingWordsQuestion(String statement, LinkedHashSet<Answer> answers, double quotation,Difficulty difficulty) {

        MissingWords question = new MissingWords(statement,answers,quotation,true,difficulty);

        return this.questionRepository.save(question);
    }
}
