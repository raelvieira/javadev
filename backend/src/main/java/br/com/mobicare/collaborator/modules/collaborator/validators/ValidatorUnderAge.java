package br.com.mobicare.collaborator.modules.collaborator.validators;

import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public class ValidatorUnderAge implements Validator {

    private final CollaboratorRepository repository;

    private Validator next;

    public ValidatorUnderAge(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean execute(Collaborator collaborator) {
        if(collaborator.getAge() < 18) {
            final var idSector = collaborator.getSector().getId();

            final var byOverEighteen = this.repository
                    .findCountAllByBirthdayAfterAndSector(idSector, LocalDate.now().minusYears(18));
            final var allBySector =  this.repository.findCountCollaboratorsBySector(idSector);

            if(allBySector <= 0) {
                return false;
            }

            long percentage = ((byOverEighteen + 1) * 100) / allBySector;

            return percentage <= 20;
        }

        return next.execute(collaborator);
    }

    @Override
    public void setNextValidator(Validator next) {
        this.next = next;
    }
}
