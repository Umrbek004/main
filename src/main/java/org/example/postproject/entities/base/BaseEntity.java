package org.example.postproject.entities.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
/**
 * Bu base entity hisoblanadi
 * ya'ni bu class ozi table yaratmaydi lekin shu classdan extend olingan hamma Entity classlarga ozining fieldlarini q'shib beradi
 * bu kodni takror yozmaslik uchun ishlatiladi
 */
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private boolean deleted = false;
}
