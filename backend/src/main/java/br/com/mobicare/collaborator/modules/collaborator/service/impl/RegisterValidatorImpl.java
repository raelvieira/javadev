package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterValidator;
import br.com.mobicare.collaborator.modules.collaborator.validators.Validator;
import br.com.mobicare.collaborator.modules.collaborator.validators.ValidatorOverSixtyFive;
import br.com.mobicare.collaborator.modules.collaborator.validators.ValidatorUnderAge;
import br.com.mobicare.collaborator.modules.collaborator.validators.WithoutValidator;
import org.springframework.stereotype.Service;

@Service
public class RegisterValidatorImpl implements RegisterValidator {

    private final CollaboratorRepository repository;

    public RegisterValidatorImpl(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(Collaborator collaborator) {
        Validator underAge = new ValidatorUnderAge(repository);
        Validator overSixtyFive = new ValidatorOverSixtyFive(repository);
        Validator withoutValidator = new WithoutValidator();

        underAge.setNextValidator(overSixtyFive);
        overSixtyFive.setNextValidator(withoutValidator);

        return underAge.execute(collaborator);
    }

}
