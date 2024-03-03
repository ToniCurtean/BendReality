package com.toni.bendreality.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.toni.bendreality.BaseEntity;
import com.toni.bendreality.CreatedByListener;
import com.toni.bendreality.comment.model.Comment;
import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.post.model.Post;
import com.toni.bendreality.weeklymapping.model.WeeklyMapping;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(name="users")
@EntityListeners(CreatedByListener.class)
public class User extends BaseEntity {

    @Size(min=2,max=30)
    @Column(name="first_name",nullable = false)
    private String firstName;

    @Size(min=2,max=30)
    @Column(name="last_name",nullable = false)
    private String lastName;

    @Size(min=10,max=30)
    @Column(name="phone_number",nullable = false)
    private String phoneNumber;

    @Size(min=10,max=50)
    @Column(unique = true,name="email",nullable = false)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private DailyAction dailyAction;

    @OneToOne(mappedBy="user",cascade =CascadeType.ALL,
            fetch = FetchType.LAZY,optional = false)
    @PrimaryKeyJoinColumn
    private WeeklyMapping weeklyMapping;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Post> createdPosts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private UserRole role;
}
