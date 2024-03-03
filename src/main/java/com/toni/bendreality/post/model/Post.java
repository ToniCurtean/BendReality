package com.toni.bendreality.post.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toni.bendreality.BaseEntity;
import com.toni.bendreality.CreatedByListener;
import com.toni.bendreality.comment.model.Comment;
import com.toni.bendreality.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Post")
@Table(name="posts")
@EntityListeners(CreatedByListener.class)
public class Post extends BaseEntity {

    @Size(min=2,max=100)
    @Column(name="title")
    private String title;

    @Size(min=2,max=5000)
    @Column(name="content")
    private String content;

    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="updated_at",nullable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;


    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "post",
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments=new ArrayList<>();

}
