package com.hype360kh.servicecatalog.service;

import com.hype360kh.servicecatalog.model.dto.UserDto;
import com.hype360kh.servicecatalog.model.entity.UserEntity;
import com.hype360kh.servicecatalog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 */
@Service
public class UserService extends
    AbstractCrudService<UserEntity, UserDto, Long, UserRepository> {

  /**
   * Constructor for UserService.
   *
   * @param userRepository the user repository
   * @param modelMapper    the model mapper
   */
  public UserService(UserRepository userRepository, ModelMapper modelMapper) {
    super(userRepository, modelMapper, UserDto.class, UserEntity.class);
  }
}