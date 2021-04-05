package br.com.mobicare.collaborator.modules.collaborator.validators;

import br.com.mobicare.collaborator.models.Collaborator;

public class WithoutValidator implements Validator{
    @Override
    public boolean execute(Collaborator collaborator) {
        return true;
    }

    @Override
    public void setNextValidator(Validator next) {
        // Do nothing
    }
}
