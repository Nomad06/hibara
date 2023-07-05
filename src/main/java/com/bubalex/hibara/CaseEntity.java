package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.generator.EventType;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Indexed
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "case")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE \"case\" SET is_deleted = true WHERE id = ?")
public class CaseEntity extends BaseTenantEntity {

    @IndexedEmbedded(includePaths = "product.name")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_revision_id", referencedColumnName = "id")
    private ProductRevisionEntity productRevision;



    @Generated(event = EventType.INSERT)
    @Column(name = "order_number", insertable = false, updatable = false)
    @GenericField(valueBridge = @ValueBridgeRef(type = OrderNumberValueBridge.class))
    private Integer orderNumber;

    @IndexedEmbedded(includePaths = {
            "user.firstName",
            "user.lastName",
            "user.phoneNumber",
            "user.email"
    })
    @Builder.Default
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @Column(name = "client_deadline")
    private Instant clientDeadline;

    @Column(name = "government_deadline")
    private Instant governmentDeadline;

    @Column(name = "is_archived")
    private boolean archived;


}
