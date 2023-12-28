package eapli.base.sharedboard.domain;



import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class SharedBoardRows  implements DomainEntity<Long> {


    public static SharedBoardRows from (Long id, SharedBoardTitle rowtSharedBoardTitle, int num) throws BusinessRuleException {
        try {
            Preconditions.nonNull(rowtSharedBoardTitle);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new SharedBoardRows(id, rowtSharedBoardTitle,num);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SharedBoardTitle rowtSharedBoardTitle;

    private int num;

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((SharedBoardRows) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }

    @Override
    public String toString() {
        return "Title=" + rowtSharedBoardTitle +
                "  Num=" + num;
    }
}