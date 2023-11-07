package com.haytech.demo_referralmanagement.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haytech.demo_referralmanagement.model.base.BaseEntity;
import lombok.*;
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
@Table(name = "checking_type")
@Where(clause = "deleted = false")
public class CheckingType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 30)
    private String name;
    @OneToMany(mappedBy = "checkingType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AgencyChecking> agencyCheckingList;


}
