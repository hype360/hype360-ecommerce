package com.hype360kh.serviceprofile.service;


import com.hype360kh.libcommonservices.services.AbstractCrudService;
import com.hype360kh.serviceprofile.model.dto.UserDto;
import com.hype360kh.serviceprofile.model.entity.UserEntity;
import com.hype360kh.serviceprofile.model.entity.UserEntity_;
import com.hype360kh.serviceprofile.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import security.password.PasswordGenerator;
import security.password.PasswordManager;

/**
 * Service class for managing users.
 */
@Service
@Slf4j
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

  public UserDto addUser(UserDto userDto) {
    throwIfAlreadyExisted(UserEntity_.EMAIL, userDto.getEmail());
    // Generate random password.
    final var generatedPassword = PasswordGenerator.builder()
        .withLength(12)
        .withDigits(true)
        .withUppercase(true)
        .withLowercase(true)
        .withSymbols(true)
        .build().generate();

    // Generate encrypted password using JWT token
    final var encryptedPassword = PasswordManager.getInstance().hashPassword(generatedPassword);
    userDto.setPassword(encryptedPassword);
    return create(userDto);
  }

  public UserDto updateUser(UserDto userDto) {
    UserDto existedUser = getByIdOrThrow(userDto.getId());
    existedUser.setEmail(userDto.getEmail());
    UserEntity updatedUser = repository.save(dtoToEntity(existedUser));
    return entityToDto(updatedUser);
  }
}