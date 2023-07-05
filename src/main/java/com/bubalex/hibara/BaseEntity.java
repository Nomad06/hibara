package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

/**
 * The base implementation of a persisted entity.
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericField(sortable = Sortable.NO, aggregable = Aggregable.YES, searchable = Searchable.YES)
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_deleted")
    private Boolean deleted;

    /**
     * The lifecycle hook for the entity insertion (should be extended in subtypes).
     */
    @PrePersist
    public void onInsert() {
        this.createdAt = Instant.now().truncatedTo(ChronoUnit.MICROS);
        this.updatedAt = Instant.now().truncatedTo(ChronoUnit.MICROS);
        this.deleted = false;
    }

    /**
     * The lifecycle hook for the entity modification (should be extended in subtypes).
     */
    @PreUpdate
    public void onUpdate() {
        if (this.createdAt == null) {
            onInsert();
        } else {
            this.updatedAt = Instant.now().truncatedTo(ChronoUnit.MICROS);
        }
    }

    /**
     * The lifecycle hook for the entity deletion (should be extended in subtypes).
     */
    @PreRemove
    public void onDelete() {
        this.deleted = true;
    }

    /**
     * Equals and hashCode are overridden in a specific way: if two objects belong to the same entity class and their
     * ids are equal - then they are considered equal. Otherwise they are different.
     * The purpose is to make objects, which do not yet have an identifier, different from each other
     * (with @EqualsAndHashCode they are considered identical). This behavior allows to automatically save Sets of new
     * nested entities. Without this fix MapStruct mappers lose all child entities except one, because all new child
     * entities have the same null identifier.
     *
     * @param o object to compare
     * @return equality result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (id == null || o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
