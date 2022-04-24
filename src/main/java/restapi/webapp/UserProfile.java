package restapi.webapp;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue
    Long id;
    private String name;
    private LocalDateTime creationTime;

    /*
    @OneToMany is an annotation indicating the relationship between UserProfile
    and Post.
    This annotation makes the profile's primary key will be stored as a foreign key int he POSTS SQL table
     */
    @OneToMany(mappedBy = "creator")
    // Eager loading to avoid NullPointerException
    private List<Post> posts =  new ArrayList<>();

    public UserProfile(String name){
        this.creationTime = LocalDateTime.now();
        this.name = name;
    }
}
