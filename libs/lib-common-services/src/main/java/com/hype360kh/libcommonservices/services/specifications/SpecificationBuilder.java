package com.hype360kh.libcommonservices.services.specifications;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder<E> {

  private final List<SearchCriteria> params = new ArrayList<>();

  public SpecificationBuilder<E> with(String key, SearchOperation operation, Object value,
      boolean orPredicate) {
    params.add(new SearchCriteria(key, operation, value, orPredicate));
    return this;
  }

  public Specification<E> build() {
    if (params.isEmpty()) {
      return null;
    }

    Specification<E> result = new CustomSpecification<>(params.get(0));

    for (int i = 1; i < params.size(); i++) {
      SearchCriteria criteria = params.get(i);
      result = criteria.isOrPredicate() ?
          Specification.where(result).or(new CustomSpecification<>(criteria)) :
          Specification.where(result).and(new CustomSpecification<>(criteria));
    }

    return result;
  }
}