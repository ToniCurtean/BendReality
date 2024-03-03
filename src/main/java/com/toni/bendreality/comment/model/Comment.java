package com.toni.bendreality.comment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toni.bendreality.BaseEntity;
import com.toni.bendreality.CreatedByListener;
import com.toni.bendreality.post.model.Post;
import com.toni.bendreality.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Comment")
@Table(name = "comments")
@EntityListeners(CreatedByListener.class)
public class Comment extends BaseEntity {

    @Size(min = 2, max = 5000)
    @Column(name = "text")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
