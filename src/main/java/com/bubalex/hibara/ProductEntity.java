package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.generator.EventType;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.util.*;

@Getter
@Setter
@Entity
@Indexed
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
public class ProductEntity extends BaseTenantEntity {

    @Generated(event = EventType.INSERT)
    @Column(name = "order_number", insertable = false, updatable = false)
    @GenericField(valueBridge = @ValueBridgeRef(type = OrderNumberValueBridge.class))
    private Integer orderNumber;

    @FullTextField
    @Column(name = "name")
    private String name;

    @FullTextField
    @Column(name = "description")
    private String description;

    @Column(name = "is_enabled")
    private Boolean enabled;

    @Column(name = "dependent_product_id")
    private UUID dependentProductId;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductRevisionEntity> productRevisions = new ArrayList<>();

}
