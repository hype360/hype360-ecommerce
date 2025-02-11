package com.hype360kh.serviceprofile.controller;

import com.hype360kh.serviceprofile.model.dto.UserDto;
import com.hype360kh.serviceprofile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * GET /api/users : get all users.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of users in body
   */
  @GetMapping
  @Operation(summary = "Get all users", description = "Retrieve a list of all users")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAll();
    return ResponseEntity.ok(users);
  }

  /**
   * GET /api/users/{id} : get the "id" user.
   *
   * @param id the id of the user to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the user, or with status 404 (Not
   * Found)
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getByIdOrThrow(id));
  }

  /**
   * POST /api/users : create a new user.
   *
   * @param userDto the user to create
   * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status
   * 400 (Bad Request) if the user has already an ID
   */
  @PostMapping
  @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto createdUser = userService.create(userDto);
    return ResponseEntity.status(201).body(createdUser);
  }

  /**
   * PUT /api/users/{id} : update an existing user.
   *
   * @param id      the id of the user to update
   * @param userDto the user to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated user, or with status
   * 404 (Not Found)
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update an existing user", description = "Update an existing user with the provided details")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
      @RequestBody UserDto userDto) {
    Optional<UserDto> updatedUser = userService.update(id, userDto);
    return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * DELETE /api/users/{id} : delete the "id" user.
   *
   * @param id the id of the user to delete
   * @return the ResponseEntity with status 204 (NO_CONTENT)
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user", description = "Delete a user by their ID")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}