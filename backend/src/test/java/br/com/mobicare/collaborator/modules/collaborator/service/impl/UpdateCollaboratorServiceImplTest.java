package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.JavaPlenoIsraelVieiraApplication;
import br.com.mobicare.collaborator.builder.CollaboratorBuilder;
import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.UpdateCollaboratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest(
        classes = JavaPlenoIsraelVieiraApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UpdateCollaboratorServiceImplTest {
    @MockBean
    private CollaboratorRepository repository;

    @Autowired
    private UpdateCollaboratorService service;

    @Test
    void shouldDeleteCollaborator() {
        final var collaborator = CollaboratorBuilder.builder().build();
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(collaborator));
        when(this.repository.save(any(Collaborator.class))).thenReturn(collaborator);

        this.service.delete(1);

        verify(this.repository, times(1)).findById(anyInt());
        verify(this.repository, times(1)).save(any(Collaborator.class));
    }

    @Test
    void shouldReturnNotFoundExceptionWhenDeleteCollaborator() {
        when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

        final var exception = assertThrows(
                NotFoundException.class,
                () -> this.service.delete(1),
                "Deve retornar um NotFoundException"
        );

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Nenhum colaborador cadastrado para este ID!"));

        verify(this.repository, times(1)).findById(anyInt());
        verify(this.repository, times(0)).save(any(Collaborator.class));
    }
}
