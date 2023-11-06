package com.haytech.demo_referralmanagement.model.entity;


import com.haytech.demo_referralmanagement.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "fanavaran_policy")
@Where(clause = "deleted = false")
public class FanavaranPolicy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    //
    @Column(name = "policy_id", nullable = false)
    private Integer policyId;

    @Column(name = "insurance_number", nullable = false, length = 10)
    private String insuranceNumber;

    @Column(name = "begin_date", nullable = false)
    private Long beginDate;

    @Column(name = "end_date", nullable = false)
    private Long endDate;

    @Column(name = "issue_date", nullable = false)
    private Long issueDate;

    @Column(name = "fk_insurance_course_type_id", nullable = false)
    private Integer fkInsuranceCourseTypeId;

    @Column(name = "gid", nullable = false, length = 256)
    private String gid;

    @Column(name = "personnel_id", nullable = false, length = 256)
    private String personnelId;

    @Column(name = "complete_insurance_number", nullable = false, length = 20)
    private String completeInsuranceNumber;

    @Column(name = "agency_name", nullable = false)
    private Byte agencyName;
    //
    @Column(name = "mational_code")
    private String nationalCode;
    @OneToMany(mappedBy = "fanavaranPolicy",cascade = CascadeType.ALL)
    private List<AgencyChecking> agencyCheckings;

}
