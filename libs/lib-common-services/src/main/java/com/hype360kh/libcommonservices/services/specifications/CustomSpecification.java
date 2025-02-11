package com.hype360kh.libcommonservices.services.specifications;

import jakarta.annotation.Nonnull;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomSpecification<E> implements Specification<E> {

  private final SearchCriteria criteria;

  public CustomSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(@Nonnull Root<E> root, CriteriaQuery<?> query,
      @Nonnull CriteriaBuilder builder) {
    switch (criteria.getOperation()) {
      case EQUALITY:
        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      case GREATER_THAN:
        return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
      case LESS_THAN:
        return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
      case LIKE:
        return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
      default:
        return null;
    }
  }
}