package eapli.base.sharedboard.utils;

import eapli.base.course.domain.*;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.base.sharedboard.domain.SharedBoard;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class BoardLock implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long boardId;

    public LockStatus lockStatus;

    public static BoardLock from (Long id, Long boardId, LockStatus o) throws BusinessRuleException {
        try {
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new BoardLock(id, boardId, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    public Object getLockObject(){
        return this.lockStatus;
    }

    public Long getBoardId() {
        return this.boardId;
    }

    public LockStatus getLockStatus(){
        return this.lockStatus;
    }
}
