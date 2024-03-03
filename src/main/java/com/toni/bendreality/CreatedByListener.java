package com.toni.bendreality;

import com.toni.bendreality.exceptions.BusinessValidationException;
import com.toni.bendreality.user.model.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;
import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserRole;

@Slf4j
public class CreatedByListener {

    @PrePersist
    public void prePopulateCreatedBy(Object entity){
        if(entity instanceof BaseEntity){
            log.trace("is in pre propulate user id");
            setCreatedBy((BaseEntity) entity,getCurrentUserId());
        }
    }

    @PreUpdate
    @PreRemove
    public void preventModificationOfForeignEntity(Object entity){
        if(entity instanceof User)
            return;
        if((entity instanceof BaseEntity)){
            if(!Objects.equals(getCurrentUserId(),((BaseEntity) entity).getCreatedBy()) && !Objects.equals(getCurrentUserRole(),"ADMIN")){
                throw new BusinessValidationException("Cannot modify " + entity.getClass().getSimpleName() +
                        " because it was not created by you!");
            }
        }else{
        throw new UnsupportedOperationException("Cannot modify/delete an entity that does not extend BaseEntity!");
        }
    }


    @SneakyThrows
    private void setCreatedBy(BaseEntity entity,Integer userId){
        Field field=BaseEntity.class.getDeclaredField("createdBy");
        field.setAccessible(true);
        field.set(entity,userId);
        log.trace("am populat fieldul entitatii created by cu user id-ul {}",userId);
    }

}
