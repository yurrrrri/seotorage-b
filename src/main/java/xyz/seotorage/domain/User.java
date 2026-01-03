package xyz.seotorage.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.seotorage.domain.vo.Mode;
import xyz.seotorage.domain.vo.Theme;

import static xyz.seotorage.domain.vo.Mode.*;
import static xyz.seotorage.domain.vo.Theme.DARK;
import static xyz.seotorage.domain.vo.Theme.LIGHT;

@Entity(name = "nuser")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

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

    public User changeTheme() {
        this.theme = this.theme == LIGHT ? DARK : LIGHT;
        return this;
    }

    public User changeMode() {
        this.mode = this.mode == ALBUM ? LIST : ALBUM;
        return this;
    }

    public User removeUser() {
        this.removed = true;
        return this;
    }

}
