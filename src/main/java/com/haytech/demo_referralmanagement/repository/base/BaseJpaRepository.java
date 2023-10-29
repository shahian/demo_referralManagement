package com.haytech.demo_referralmanagement.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findById(ID id);

    default void likeFilter(Root<T> root, List<Predicate> predicates,CriteriaBuilder criteriaBuilder,String input, String fieldName){
        if (!StringUtils.isEmpty(input))
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + input.toLowerCase() + "%"));
    }

    default void equalFilter(Root<T> root, List<Predicate> predicates,CriteriaBuilder criteriaBuilder,Object input, String fieldName){
        if (!StringUtils.isEmpty(input))
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(fieldName)), input));
    }
}
