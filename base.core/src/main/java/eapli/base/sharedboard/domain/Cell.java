package eapli.base.sharedboard.domain;




import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class Cell implements DomainEntity<Long> {


    public static Cell from (Long id, CellState cellState, SharedBoardColumns column, SharedBoardRows sharedBoardRows, Post post) throws BusinessRuleException {
        try {
            Preconditions.nonNull(cellState);
            Preconditions.nonNull(column);
            Preconditions.nonNull(sharedBoardRows);

        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new Cell(id, cellState,column, sharedBoardRows,post);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private CellState cellState;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SharedBoardColumns column;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SharedBoardRows sharedBoardRows;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Post post;


    public void setPost(Post post) {
        this.post = post;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Cell) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }

    public Post getPost() {
        return post;
    }
}

