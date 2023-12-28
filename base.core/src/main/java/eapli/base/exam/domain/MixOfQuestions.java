package eapli.base.exam.domain;
import eapli.base.question.domain.Question;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class MixOfQuestions {
    private String name;
    private List<Question> questions;



    public Set<Question> getQuestions(Set<Question> questions, int limitQuestions, int difficulty) {
        Set<Question> randomQuestions = new HashSet<>();
        Random random = new Random();
        Question question = null;
        
        for (int i = 0; i <= limitQuestions; i++) {
            int randomIndex = random.nextInt(questions.size());
            Iterator<Question> it = questions.iterator();
            int cont=0;
            while (it.hasNext()){
                cont++;
                if(cont + 1 == randomIndex){
                    question = it.next();
                    break;
                }
            }

            if (!randomQuestions.contains(question) && question.getDifficulty().getDifficulty() == difficulty) {
                randomQuestions.add(question);
            }
        }

        return randomQuestions;
    }

}
