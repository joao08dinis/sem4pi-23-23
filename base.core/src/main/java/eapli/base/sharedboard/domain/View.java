package eapli.base.sharedboard.domain;


import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class View implements DomainEntity<Long> {

    public static View from (Long id, File file) throws BusinessRuleException {
        try {
            Preconditions.nonNull(file);
        } catch (Exception e) {
            throw new BusinessRuleException(e);
        }

        return new View(id,file);
    }





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private File file;






    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((View) other).getId();
    }


    @Override
    public Long identity() {
        return this.getId();
    }
}

