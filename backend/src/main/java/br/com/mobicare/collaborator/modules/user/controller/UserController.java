package br.com.mobicare.collaborator.modules.user.controller;

import br.com.mobicare.collaborator.modules.user.controller.json.request.UserRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.UserResponse;
import br.com.mobicare.collaborator.modules.user.service.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }


    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request,
                                                 UriComponentsBuilder uriBuilder) {

        UserResponse user = createUserService.execute(request);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return created(uri).body(user);
    }
}