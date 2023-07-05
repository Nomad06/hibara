package com.bubalex.hibara;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * The base implementation of the tenant-virtualized entity.
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseTenantEntity extends BaseEntity {

    @Column(name = "tenant_id")
    private UUID tenantId;

    @Override
    public void onInsert() {
        if (tenantId == null) {
            throw new IllegalArgumentException("Tenant cannot be a null");
        }
        super.onInsert();
    }

    @Override
    public void onUpdate() {
        if (tenantId == null) {
            throw new IllegalArgumentException("Tenant cannot be a null");
        }
        super.onUpdate();
    }

}
