package com.haytech.demo_referralmanagement.model.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BaseEntity {

    protected Long creationDate = System.currentTimeMillis();
    protected Long deletionDate;
    protected boolean deleted = false;

}
