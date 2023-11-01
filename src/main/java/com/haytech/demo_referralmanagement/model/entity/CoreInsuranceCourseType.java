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
@Table(name = "core_insurance_course_type")
@Where(clause = "deleted = false")
public class CoreInsuranceCourseType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 256)
    private String name;

    @Column(name = "coreName")
    private boolean coreName;

    @OneToMany(mappedBy = "core_insurance_course_type",cascade = CascadeType.ALL)
    private List<InsuranceCourseType> insuranceCourseTypeList;
}
