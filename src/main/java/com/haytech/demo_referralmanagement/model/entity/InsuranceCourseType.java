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
@Table(name = "insurance_course_type")
@Where(clause = "deleted = false")
public class InsuranceCourseType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name",length = 50)
    private String name;
    @Column(name = "code",length = 8)
    private String code;
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne()
    @JoinColumn(name = "core_insurance_course_type_id")
    private CoreInsuranceCourseType coreInsuranceCourseType;
}
