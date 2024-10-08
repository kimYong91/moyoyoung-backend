package org.community.moyoyoung.samgak0.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import org.community.moyoyoung.dto.MyUserDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.MyUserService;
import org.modelmapper.ModelMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

//Author : MinU Bak
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService userService;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody(required = true) CreateRequest request) {
        return handleUserOperation(() -> {
            if (!userService.checkByUsername(request.username)) {
                throw new IllegalArgumentException("Username is already in use.");
            }
            if (!userService.checkByPhoneNumber(request.phoneNumber)) {
                throw new IllegalArgumentException("Phone number is already in use.");
            }
            if (!userService.checkByNickname(request.nickname)) {
                throw new IllegalArgumentException("Nickname is already in use.");
            }

            MyUser user = new MyUser(
                    null,
                    request.username,
                    request.nickname,
                    request.password,
                    request.name,
                    request.phoneNumber,
                    false,
                    null,
                    new ArrayList<>());

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
            MyUserDTO userDTO = userService.getUserById(id)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            return ResponseEntity.ok(userDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User with ID " + id + " not found."));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id", required = true) Long id,
            @RequestBody UpdateRequest updateRequest) {
        return handleUserOperation(() -> {
            if (id == null) {
                throw new IllegalArgumentException("ID parameter is required.");
            }

            MyUser existingUser = userService.getUserById(id)
                    .map(user -> modelMapper.map(user, MyUser.class))
                    .orElseThrow(() -> new NoSuchElementException("User not found."));

            if (updateRequest == null) {
                throw new IllegalArgumentException("At least one of nickname or password must be provided.");
            }

            if (updateRequest.nickname.isPresent()) {
                existingUser.setNickname(updateRequest.nickname.get());
            }

            if (updateRequest.password.isPresent()) {
                existingUser.setPassword(updateRequest.password.get());
            }

            checkViolations(existingUser);

            userService.updateUser(existingUser);
            return ResponseEntity.ok(new SuccessResponse());
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id", required = true) Long id) {
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

    @PostMapping("/check/resetCheckPassword")
    public ResponseEntity<?> resetCheckPassword(@RequestBody(required = true) ResetCheckRequest request) {
        return handleUserOperation(() -> {
            Optional<MyUserDTO> userDTO = userService.getUserByUsernamePhoneNumberAndName(request.username, request.phoneNumber, request.name);
            return ResponseEntity.ok(new SuccessDataResponse(userDTO.isPresent()));
        });
    }

    @PostMapping("/check/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody(required = true) ResetRequest request) {
        return handleUserOperation(() -> {
            MyUserDTO userDTO = userService
                    .getUserByUsernamePhoneNumberAndName(request.username, request.phoneNumber, request.name)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            MyUser user = modelMapper.map(userDTO, MyUser.class);
            user.setPassword(request.password);
            checkViolations(user);
            userService.updateUser(user);
            return ResponseEntity.ok(new SuccessResponse());
        });
    }

    @GetMapping("/check/findId")
    public ResponseEntity<?> findIdByPhoneAndName(@RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "phoneNumber", required = true) String phoneNumber) {
        return handleUserOperation(() -> {
            MyUserDTO userDTO = userService.getUserByPhoneNumberAndName(phoneNumber, name)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", userDTO.getId());
            responseData.put("username", userDTO.getUsername());
            return ResponseEntity.ok(new FindIdResponse(responseData));
        });
    }

    @GetMapping("/check/nickname")
    public ResponseEntity<?> checkNickname(@RequestParam(name = "nickname", required = true) String nickname) {
        return handleUserOperation(() -> {
            if (nickname == null || nickname.isEmpty()) {
                throw new IllegalArgumentException("nickname parameter is required.");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessDataResponse(userService.checkByNickname(nickname)));
        });
    }

    @GetMapping("/check/username")
    public ResponseEntity<?> checkUsername(@RequestParam(name = "username", required = true) String username) {
        return handleUserOperation(() -> {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("username parameter is required.");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessDataResponse(userService.checkByUsername(username)));
        });
    }

    @GetMapping("/check/phoneNumber")
    public ResponseEntity<?> checkPhone(@RequestParam(name = "phoneNumber", required = true) String phoneNumber) {
        return handleUserOperation(() -> {
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new IllegalArgumentException("phoneNumber parameter is required.");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessDataResponse(userService.checkByPhoneNumber(phoneNumber)));
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
            throw new IllegalArgumentException(
                    "Data integrity violation: Validation failed: Password must be 8+ chars, with upper, lower, and special.");
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

    public record ResetRequest(String username, String phoneNumber, String name, String password) {
    }

    public record ResetCheckRequest(String username, String phoneNumber, String name) {
    }

    public record CreateRequest(String username, String nickname, String password, String name, String phoneNumber) {
    }

    public record UpdateRequest(Optional<String> nickname, Optional<String> password) {
    }

    public record FindIdResponse(boolean success, Map<String, Object> data) {
        public FindIdResponse(Map<String, Object> data) {
            this(true, data);
        }
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
