package br.com.mobicare.collaborator.modules.collaborator.service;

import br.com.mobicare.collaborator.models.Collaborator;

public interface RegisterValidator {
    boolean execute(Collaborator collaborator);
}
