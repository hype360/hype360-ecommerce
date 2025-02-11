package com.hype360kh.libcommonservices.services.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {

  private String key;
  private SearchOperation operation;
  private Object value;
  private boolean orPredicate;
}