package com.rosenhristov.bank.entity;

import com.rosenhristov.bank.utils.Utils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data

public class BaseEntity {

//    @NotNull(message = "Creation date must not be empty")
    @ApiModelProperty(name = "dateCreated", value = "Date of creation in bank's system",
            dataType = "Date", example = "2020-08-20")
    @Column(name = "created_at")
    protected Date dateCreated;

    @Column(name = "last_update")
    @ApiModelProperty(name = "dateUpdated", value = "Date of last update",
            dataType = "Date", example = "2020-08-20")
    protected Date dateUpdated;

    @PrePersist
    protected void onPrePersist() {
        doPrePersist();
    }

    @PreUpdate
    protected void onPreUpdate() {
        doPreUpdate();
    }

    @PreRemove
    protected void onPreRemove() {
        doPreRemove();
    }

    protected void doPrePersist() {
        this.setDateCreated(Utils.dateNow());
    }

    protected void doPreUpdate() {
        this.setDateUpdated(Utils.dateNow());
    }

    protected void doPreRemove() {
    }

}
