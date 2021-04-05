package br.com.mobicare.collaborator.modules.user.service.impl;

import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.models.User;
import br.com.mobicare.collaborator.modules.user.controller.json.request.UserRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.UserResponse;
import br.com.mobicare.collaborator.modules.user.repository.ProfileRepository;
import br.com.mobicare.collaborator.modules.user.repository.UserRepository;
import br.com.mobicare.collaborator.modules.user.service.CreateUserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    public CreateUserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public UserResponse execute(UserRequest request) {
        final var profile = profileRepository.findByName(request.getProfileName());

        if(profile.isEmpty()) {
            throw new NotFoundException("Nenhum profile encontrado!");
        }

        final User user = request.toModel();
        user.addProfiles(profile);
        return UserResponse.toResponse(this.userRepository.save(user));
    }
}
