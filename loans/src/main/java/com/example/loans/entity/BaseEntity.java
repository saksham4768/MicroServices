package com.example.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base entity class that provides auditing fields for all entities.
 * This class is marked as MappedSuperclass to allow its fields to be inherited by entity classes.
 * It includes automatic auditing of create/update timestamps and user information.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {

    /**
     * The date and time when the entity was created.
     * Automatically set when the entity is first persisted.
     */
    @CreatedDate
    @Column(name ="created_at")
    private LocalDateTime createdAt;

    /**
     * The date and time when the entity was last modified.
     * Automatically updated when the entity is modified.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The user who created the entity.
     * Automatically set to the current user when the entity is first persisted.
     */
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The user who last modified the entity.
     * Automatically updated to the current user when the entity is modified.
     */
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
