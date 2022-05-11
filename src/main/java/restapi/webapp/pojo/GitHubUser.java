package restapi.webapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a domain object (POJO - Plain Old Java Object) representing a GitHub user with specific fields
 * field: id, name, blog, email, public_repos
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//take only the fields declared in this class and ignore al other POJO fields
public class GitHubUser {
    private Long id;
    private String name;
    private String blog;
    private String email;
    private Integer public_repos;

    @Override
    public String toString() {
        return "GitHubUser{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", blog='" + getBlog() + '\'' +
                ", public_repos=" + getPublic_repos() +
                '}';
    }
}

