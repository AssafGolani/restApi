package restapi.webapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
/*
Spring uses Jackson for serialization/deserialization.
it needs a default constructor for its ObjectMapper class.
 */
public class Post {
    @Id @GeneratedValue
    private Long id;
    private String content;
    private String title;
    private LocalDateTime creationTime;

    // @JsonIgnore - marshall JSON field exposure to user
    @OneToOne private UserProfile creator;
    @JsonIgnore
    public Post(String title, String content, UserProfile profile){
        this.creationTime = LocalDateTime.now();
        this.title = title;
        this.content = content;
        this.creator = profile;
    }
}

