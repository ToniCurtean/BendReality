package com.toni.bendreality.mastervision.model;


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
@Entity(name="MasterVision")
@Table(name="master_visions")
@EntityListeners(CreatedByListener.class)
public class MasterVision extends BaseEntity {

    @Column(name="master_vision")
    @Size(min=1,max = 20000)
    private String masterVision;

    @Column(name="identity_design")
    @Size(min=1,max = 20000)
    private String identityDesign;

    @Column(name="bucket_list")
    @Size(min=1,max = 20000)
    private String bucketList;

    @Column(name="lifestyle_design")
    @Size(min=1,max = 20000)
    private String lifestyleDesign;

    @Column(name="material_desires")
    @Size(min=1,max = 20000)
    private String materialDesires;

    @Column(name="north_star_g1")
    @Size(min=3,max=300)
    private String northStarGoalOne;

    @Column(name="north_star_g2")
    @Size(min=3,max=300)
    private String northStarGoalTwo;

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
