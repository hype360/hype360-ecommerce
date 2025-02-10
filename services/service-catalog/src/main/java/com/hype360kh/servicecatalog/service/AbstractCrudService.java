package com.hype360kh.servicecatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Abstract service class providing CRUD operations.
 *
 * @param <E>  the entity type
 * @param <D>  the DTO type
 * @param <ID> the ID type
 * @param <R>  the repository type
 */
public abstract class AbstractCrudService<E, D, ID, R extends JpaRepository<E, ID>> {

  protected final R repository;
  protected final ModelMapper modelMapper;
  private final Class<D> dtoClass;
  private final Class<E> entityClass;

  /**
   * Constructor for AbstractCrudService.
   *
   * @param repository  the repository
   * @param modelMapper the model mapper
   * @param dtoClass    the DTO class
   * @param entityClass the entity class
   */
  protected AbstractCrudService(R repository, ModelMapper modelMapper, Class<D> dtoClass,
      Class<E> entityClass) {
    this.repository = repository;
    this.modelMapper = modelMapper;
    this.dtoClass = dtoClass;
    this.entityClass = entityClass;
  }

  /**
   * Retrieves all entities and maps them to DTOs.
   *
   * @return a list of DTOs
   */
  public List<D> getAll() {
    return repository.findAll().stream()
        .map(entity -> modelMapper.map(entity, dtoClass))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves an entity by its ID and maps it to a DTO.
   *
   * @param id the ID of the entity
   * @return an optional DTO
   */
  public Optional<D> getById(ID id) {
    return repository.findById(id)
        .map(entity -> modelMapper.map(entity, dtoClass));
  }

  /**
   * Creates a new entity from a DTO.
   *
   * @param dto the DTO
   * @return the created DTO
   */
  public D create(D dto) {
    E entity = modelMapper.map(dto, entityClass);
    entity = repository.save(entity);
    return modelMapper.map(entity, dtoClass);
  }

  /**
   * Updates an existing entity with data from a DTO.
   *
   * @param id  the ID of the entity
   * @param dto the DTO
   * @return an optional updated DTO
   */
  public Optional<D> update(ID id, D dto) {
    return repository.findById(id)
        .map(existingEntity -> {
          modelMapper.map(dto, existingEntity);
          E updatedEntity = repository.save(existingEntity);
          return modelMapper.map(updatedEntity, dtoClass);
        });
  }

  /**
   * Deletes an entity by its ID.
   *
   * @param id the ID of the entity
   */
  public void delete(ID id) {
    repository.deleteById(id);
  }
}