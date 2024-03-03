package com.toni.bendreality.weeklymapping.model;

import com.toni.bendreality.BaseEntity;
import com.toni.bendreality.CreatedByListener;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="WeeklyMapping")
@Table(name="weekly_mappings")
@EntityListeners(CreatedByListener.class)
public class WeeklyMapping extends BaseEntity{


    @Size(min=2,max=300)
    @Column(name="vision_objective_one",nullable = false)
    private String visionObjOne;

    @Size(min=2,max=300)
    @Column(name="vision_objective_two",nullable = false)
    private String visionObjTwo;

    @Size(min=2,max=300)
    @Column(name="vision_objective_three",nullable = false)
    private String visionObjThree;

    @Size(min=2,max=300)
    @Column(name="mindset_obj",nullable = false)
    private String mindsetObj;

    @Size(min=2,max=300)
    @Column(name="body_obj",nullable = false)
    private String bodyObj;

    @Size(min=2,max=300)
    @Column(name="social_obj",nullable = false)
    private String socialObj;

    @Column(name="created_at",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="updated_at",nullable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

}
