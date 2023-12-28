package eapli.base.sharedboard.domain;


import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class BoardConfig implements DomainEntity<Long> {

    public static BoardConfig from (Long id, int numrows, int columnnum, Set<SharedBoardRows> rows, Set<SharedBoardColumns> column) throws BusinessRuleException {
        try {
            Preconditions.nonNull(rows);
            Preconditions.nonNull(column);

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new BoardConfig(id,numrows,columnnum, rows,column);
    }






    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numrows;

    private int columnnum;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SharedBoardRows> rows;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SharedBoardColumns> column;


    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((BoardConfig) other).getId();
    }

    @Override
    public Long identity() {
        return this.getId();
    }


}