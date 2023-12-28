package eapli.base.sharedboard.domain;
import eapli.base.shared.domain.BusinessRuleException;
import eapli.framework.domain.model.DomainEntity;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class AuditLog implements DomainEntity<Long> {

    public static AuditLog from (Long id, Set<Modification> auditLog) throws BusinessRuleException {
        return new AuditLog(id, auditLog);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Modification> auditLogs;

    @Override
    public boolean sameAs(Object other) {
        return this.getId().equals(((AuditLog) other).getId());
    }

    @Override
    public Long identity() {
        return this.getId();
    }
}
