package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;
import org.hibernate.generator.EventType;

@Table(name = "client")
@Where(clause = "is_deleted = false")
@Entity
@Setter
@Getter
@OnDelete(action = OnDeleteAction.CASCADE)
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class ClientEntity extends UserEntity {

    @Generated(event = EventType.INSERT)
    @SequenceGenerator(name = "client_order_number_seq", sequenceName = "client_order_number_seq", allocationSize = 1)
    @Column(name = "order_number", insertable = false, updatable = false)
    private Integer orderNumber;

    /**
     * The language of the client.
     */
    @Column(name = "language")
    private String language;

    /**
     * The lead source of the client.
     */
    @Column(name = "lead_source")
    private String leadSource;


    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;
}

