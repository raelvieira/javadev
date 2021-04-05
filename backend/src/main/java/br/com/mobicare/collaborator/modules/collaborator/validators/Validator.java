package br.com.mobicare.collaborator.modules.collaborator.validators;

import br.com.mobicare.collaborator.models.Collaborator;

public interface Validator {
    boolean execute(Collaborator collaborator);
    void setNextValidator(Validator next);
}
