package br.com.mobicare.collaborator.modules.collaborator.validators;

import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
public class ValidatorOverSixtyFive implements Validator {

    private final CollaboratorRepository repository;

    private Validator next;

    public ValidatorOverSixtyFive(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean execute(Collaborator collaborator) {
        if (collaborator.getAge() > 65) {
            final var byOverSixtyFive = this.repository.findCountAllByBirthdayBefore(LocalDate.now().minusYears(66));
            final var allCollaborators = this.repository.findCountAll();

            if(allCollaborators <= 0) {
                return false;
            }

            long percentage = ((byOverSixtyFive + 1) * 100) / allCollaborators;

            return percentage <= 20;
        }

        return next.execute(collaborator);
    }

    @Override
    public void setNextValidator(Validator next) {
        this.next = next;
    }
}
