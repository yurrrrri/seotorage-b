package xyz.seotorage.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.seotorage.domain.vo.PagingType;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Content {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_book_id")
    private UserBook userBook;

    @CreatedDate
    @Column(updatable = false)
    private long createDate;

    @LastModifiedDate
    private long modifiedDate;

    private String image; // image URL or path

    @Enumerated(EnumType.STRING)
    private PagingType pagingType;

    private Integer pageNumber;

    @Max(500)
    private String sentence;

    @Max(200)
    private String memo;

    private boolean removed;

}
