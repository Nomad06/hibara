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

@Table(name = "employee")
@Where(clause = "is_deleted = false")
@Entity
@Setter
@Getter
@OnDelete(action = OnDeleteAction.CASCADE)
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class EmployeeEntity extends UserEntity {

    @Generated(event = EventType.INSERT)
    @SequenceGenerator(name = "employee_order_number_seq", sequenceName = "employee_order_number_seq", allocationSize = 1)
    @Column(name = "order_number", insertable = false, updatable = false)
    private Integer orderNumber;

    @Column(name = "consultation_languages")
    private String consultationLanguages = "EN";

    /**
     * The job title of the employee.
     */
    @Column(name = "job_title")
    private String jobTitle;

    /**
     * The fax number of the user.
     */
    @Column(name = "fax_number")
    private String faxNumber;

    /**
     * The bar number of the user.
     */
    @Column(name = "bar_number")
    private String barNumber;

    /**
     * The licensing authority of the user.
     */
    @Column(name = "licensing_authority")
    private String licensingAuthority;


    /**
     * The toggle user id of the employee.
     */
    @Column(name = "toggl_user_id")
    private Integer togglUserId;

    /**
     * The toggle token of the employee.
     */
    @Column(name = "toggl_token")
    private String togglToken;

}
