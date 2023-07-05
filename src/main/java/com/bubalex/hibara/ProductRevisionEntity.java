package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_revision")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE product_revision SET is_deleted = true WHERE id = ?")
public class ProductRevisionEntity extends BaseTenantEntity {

    @Column(name = "published_at")
    private Instant publishedAt;

    @Builder.Default
    @Column(name = "message")
    private String message = "Initial revision";

    @Column(name = "is_dependents_allowed")
    private boolean dependentsAllowed;

    @Column(name = "is_petitioners_allowed")
    private boolean petitionersAllowed;

    @IndexedEmbedded
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @Builder.Default
    @OneToMany(mappedBy = "productRevision")
    private List<CaseEntity> cases = new ArrayList<>();

}
