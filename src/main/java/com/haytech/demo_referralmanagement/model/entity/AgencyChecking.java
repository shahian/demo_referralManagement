package com.haytech.demo_referralmanagement.model.entity;


import com.haytech.demo_referralmanagement.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "agency_checking")
@Where(clause = "deleted = false")
public class AgencyChecking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "is_done")
    private boolean isDone;
    @Column(name = "is_unwilling")
    private boolean isUnwilling;
    @ManyToOne()
    @JoinColumn(name = "fanavaran_policy_Id")
    private FanavaranPolicy fanavaranPolicy;
    @ManyToOne()
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @ManyToOne()
    @JoinColumn(name = "checking_type_id")
    private CheckingType checkingType;
}
