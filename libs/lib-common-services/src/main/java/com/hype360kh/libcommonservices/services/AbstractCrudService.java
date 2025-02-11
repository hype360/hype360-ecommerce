package com.hype360kh.libcommonservices.services;

import com.hype360kh.libcommonservices.services.specifications.SearchCriteria;
import com.hype360kh.libcommonservices.services.specifications.SpecificationBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Abstract service class providing CRUD operations.
 *
 * @param <E>  the entity type
 * @param <D>  the DTO type
 * @param <ID> the ID type
 * @param <R>  the repository type
 */
@RequiredArgsConstructor
public abstract class AbstractCrudService<E, D, ID, R extends JpaRepository<E, ID> & JpaSpecificationExecutor<E>> {

  protected final R repository;
  protected final ModelMapper modelMapper;
  private final Class<D> dtoClass;
  private final Class<E> entityClass;

  public List<D> getAll() {
    return repository.findAll().stream()
        .map(entity -> modelMapper.map(entity, dtoClass))
        .collect(Collectors.toList());
  }

  public List<D> getAll(Specification<E> spec) {
    return repository.findAll(spec).stream()
        .map(entity -> modelMapper.map(entity, dtoClass))
        .collect(Collectors.toList());
  }

  public List<D> getAll(List<SearchCriteria> criteriaList) {
    SpecificationBuilder<E> builder = new SpecificationBuilder<>();
    criteriaList.forEach(
        criteria -> builder.with(criteria.getKey(), criteria.getOperation(), criteria.getValue(),
            criteria.isOrPredicate()));
    Specification<E> spec = builder.build();
    return getAll(spec);
  }

  public Optional<D> getById(ID id) {
    return repository.findById(id)
        .map(entity -> modelMapper.map(entity, dtoClass));
  }

  public D create(D dto) {
    E entity = modelMapper.map(dto, entityClass);
    entity = repository.save(entity);
    return modelMapper.map(entity, dtoClass);
  }

  public Optional<D> update(ID id, D dto) {
    return repository.findById(id)
        .map(existingEntity -> {
          modelMapper.map(dto, existingEntity);
          E updatedEntity = repository.save(existingEntity);
          return modelMapper.map(updatedEntity, dtoClass);
        });
  }

  public void delete(ID id) {
    repository.deleteById(id);
  }
}