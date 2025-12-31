package xyz.seotorage.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.seotorage.domain.vo.Mode;
import xyz.seotorage.domain.vo.Theme;

@Entity(name = "nuser")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private Long id;

    private String name;

    @Email
    private String email;

    @CreatedDate
    @Column(updatable = false)
    private long createDate;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    private boolean removed;

}
