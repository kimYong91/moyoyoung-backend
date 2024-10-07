package org.community.moyoyoung.samgak0.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Supplier;

import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.MyUserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

//Author : MinU Bak
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService userService;
    private final Validator validator;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody(required=true) MyUser user) {
        return handleUserOperation(() -> {
            user.setDisabled(false);
            checkViolations(user);
            userService.createUser(user);
            try {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .location(new URI(String.format("/users/%d", user.getId())))
                        .body(new SuccessDataResponse(user.getId()));
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("An error occurred: " + e.getMessage()));
            }
        });
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id", required = true) Long id) {
        try {
            MyUser user = userService.getUserById(id)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User with ID " + id + " not found."));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id", required=true) Long id,
            @RequestBody MyUser user) {
        return handleUserOperation(() -> {
            if (id == null) {
                throw new IllegalArgumentException("ID paramter is required.");
            }

            MyUser existingUser = userService.getUserById(id).orElseThrow(() -> new NoSuchElementException("User not found."));

            log.error(user.toString());
            if (user.getUsername() != null || user.getName() != null || user.getPhoneNumber() != null || user.getDisabled() != null) {
                throw new IllegalArgumentException("Only nickname and password fields can be updated.");
            }

            if (user.getPassword() == null && user.getNickname() == null) {
                throw new IllegalArgumentException("At least one of nickname or password must be provided.");
            }

            if (user.getNickname() != null) {
                existingUser.setNickname(user.getNickname());
            }

            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }

            existingUser.setId(id);

            checkViolations(existingUser);

            userService.updateUser(existingUser);
            return ResponseEntity.ok(new SuccessResponse());
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id", required=true) Long id) {
        return handleUserOperation(() -> {
            
            try {
                boolean isDeleted = userService.deleteUser(id);
                
                if (isDeleted) {
                    return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ErrorResponse("User with ID " + id + " not found."));
                }
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
            }
        });
    }

    @GetMapping("/check/nickname")
    public ResponseEntity<?> checkNickname(@RequestParam(name="nickname", required=true) String nickname) {
        return handleUserOperation(() -> {
            if (nickname == null || nickname.isEmpty()) {
                throw new IllegalArgumentException("nickname paramter is required.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResponse(userService.checkByNickname(nickname)));
        });
    }

    @GetMapping("/check/username")
    public ResponseEntity<?> checkUsername(@RequestParam(name="username", required=true) String username) {
        return handleUserOperation(() -> {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("username paramter is required.");
            }
            log.error("username = " + username);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessDataResponse(userService.checkByUsername(username)));
        });
    }


    private void checkViolations(MyUser existingUser) {
        Set<ConstraintViolation<MyUser>> violations = validator.validate(existingUser);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<MyUser> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
        if (!userService.validatePassword(existingUser.getPassword())) {
            throw new IllegalArgumentException("Data integrity violation: Validation failed: Password must be 8+ chars, with upper, lower, and special.");
        }
    }

    private ResponseEntity<?> handleUserOperation(Supplier<ResponseEntity<?>> operation) {
        try {
            return operation.get();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Error: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Data integrity violation: " + e.getMessage()));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    public record CheckUsernameRequest(String username) {
    }

    public record CheckNicknameRequest(String nickname) {
    }

    public record ErrorResponse(boolean success, String errorMessage) {
        public ErrorResponse(String errorMessage) {
            this(false, errorMessage);
        }
    }

    public record SuccessResponse(boolean success) {
        public SuccessResponse() {
            this(true);
        }
    }

    public record SuccessDataResponse(boolean success, Object data) {
        public SuccessDataResponse(Object data) {
            this(true, data);
        }
    }
}
