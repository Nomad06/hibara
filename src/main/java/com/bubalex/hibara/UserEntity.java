package com.bubalex.hibara;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.time.LocalDate;


@Table(name = "user")
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE visalex.user SET is_deleted = true WHERE id = ?")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseTenantEntity {

    public static final String PATTERN_BIRTHDAY = "yyyy-MM-dd";

    @Setter(AccessLevel.NONE)
    @Column(updatable = false)
    @Formula("concat(first_name,' ',last_name)")
    private String fullName;

    /**
     * Whether the sign in of the user is blocked.
     */
    @Column(name = "is_blocked")
    private boolean blocked = false;

    /**
     * The email of the user. Used for authenticating the user.
     */
    @FullTextField
    @Column(name = "email", unique = true)
    private String email;

    /**
     * The password hash of the user. Used for authenticating the user.
     */
    @Column(name = "password_hash")
    private String passwordHash;

    /**
     * The first name of the user.
     */
    @FullTextField
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the user.
     */
    @FullTextField
    @Column(name = "last_name")
    private String lastName;

    /**
     * The last name of the user.
     */
    @FullTextField
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Date of birth.
     */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * If the user has verified their email. The user can
     * only authenticate themselves, if they verified
     * their email.
     */
    @Column(name = "is_email_verified")
    private boolean emailVerified = false;


    /**
     * If the user has two factor authentication enabled.
     */
    @Column(name = "is_two_fa_enabled")
    private boolean twoFaEnabled = false;

    @FullTextField
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * The failed login attempts.
     */
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts = 0;

    /**
     * The failed verification code attempts.
     */
    @Column(name = "failed_code_verify_attempts")
    private Integer failedCodeVerifyAttempts = 0;

    /**
     * The failed login time.
     */
    @Column(name = "last_login_attempt")
    private Long lastLoginAttempt;

    /**
     * The last failed verification code time.
     */
    @Column(name = "last_code_verify_attempt")
    private Long lastCodeVerifyAttempt;

    /**
     * The last verification code sent  time.
     */
    @Column(name = "last_code_sent_at")
    private Long lastCodeSentAt;

    /**
     * A verification code for the user which can be used
     * to verify the user email, reset their password or
     * authenticate them via two factor authentication.
     * Please reset this after every use for security.
     */
    @Column(name = "verification_code")
    private String verificationCode;

    /**
     * The last seen IP address of the user.
     */
    @Column(name = "last_seen_ip")
    private String lastSeenIp;

    @Column(name = "onboarding_id")
    private Integer onboardingId;

    @Column(name = "uscis_account")
    private String uscisAccount;

    public String[] getPhoneNumberParts() {
        if (!isNumberFillable()) {
            return new String[0];
        }

        String last10digits = phoneNumber.substring(phoneNumber.length() - 10);
        int length = last10digits.length();

        String a = last10digits.substring(length - 10, 3);
        String b = last10digits.substring(length - 7, 6);
        String c = last10digits.substring(length - 4);

        return new String[] {a, b, c};
    }

    private boolean isNumberFillable() {
        return (phoneNumber != null && phoneNumber.length() == 11 && phoneNumber.startsWith("+1"));
    }

}
