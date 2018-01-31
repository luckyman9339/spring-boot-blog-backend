package com.shaked.blog.model;

import com.arangodb.springframework.annotation.Document;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by USER1 on 1/26/2018.
 */
@Document
@Validated
public class Blog implements Serializable {
    @Id
    private String id;

    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String body;
    private DateTime created;
    private int voteCount;

    public Blog() {
        created = new DateTime();
        voteCount = 0;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                ", voteCount=" + voteCount +
                '}';
    }
}
