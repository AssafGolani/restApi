package restapi.webapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"id","title","greeting"})
public class PostDTO {
    @JsonIgnore
    private final Post post;

    public String getTitle(){
        return this.post.getTitle();
    }

    public String getContent(){
        return this.post.getContent();
    }

    public String greetUser(){
        return "This is a Post DTO object";
    }
}
