package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.JavaPlenoIsraelVieiraApplication;
import br.com.mobicare.collaborator.builder.BlacklistBuilder;
import br.com.mobicare.collaborator.builder.CollaboratorBuilder;
import br.com.mobicare.collaborator.builder.CollaboratorRequestBuilder;
import br.com.mobicare.collaborator.exceptions.BadRequestException;
import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.BlacklistRepository;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterCollaboratorService;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(
        classes = JavaPlenoIsraelVieiraApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RegisterCollaboratorServiceImplTest {

    @MockBean
    private CollaboratorRepository repository;

    @MockBean
    private BlacklistRepository blacklistRepository;

    @MockBean
    private RegisterValidator registerValidator;

    @Autowired
    private RegisterCollaboratorService service;

    @Test
    void shouldRegisterCollaboratorWithSuccess() {
        final var collaborator = CollaboratorBuilder.builder().build();
        final var request = CollaboratorRequestBuilder.builder().build();

        when(this.blacklistRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(this.registerValidator.execute(any(Collaborator.class))).thenReturn(true);
        when(this.repository.save(any(Collaborator.class))).thenReturn(collaborator);

        final var response = this.service.execute(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getEmail(), response.getEmail());

        verify(this.blacklistRepository, times(1)).findByCpf(anyString());
        verify(this.registerValidator, times(1)).execute(any(Collaborator.class));
        verify(this.repository, times(1)).save(any(Collaborator.class));
    }

    @Test
    void shouldReturnBadRequestExceptionWhenRegisterCollaborator() {
        final var blacklist = BlacklistBuilder.builder().build();
        final var request = CollaboratorRequestBuilder.builder().build();

        when(this.blacklistRepository.findByCpf(anyString())).thenReturn(Optional.of(blacklist));

        final var exception = assertThrows(
                BadRequestException.class,
                () -> this.service.execute(request),
                "Deve retornar um BadRequestException"
        );

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Este Colaborador está incluso na Blacklist!"));

        verify(this.blacklistRepository, times(1)).findByCpf(anyString());
        verify(this.registerValidator, times(0)).execute(any(Collaborator.class));
        verify(this.repository, times(0)).save(any(Collaborator.class));
    }

    @Test
    void shouldReturnBadRequestExceptionWhenRegisterCollaboratorIsNotValid() {
        final var request = CollaboratorRequestBuilder.builder().build();

        when(this.blacklistRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(this.registerValidator.execute(any(Collaborator.class))).thenReturn(false);

        final var exception = assertThrows(
                BadRequestException.class,
                () -> this.service.execute(request),
                "Deve retornar um BadRequestException"
        );

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Este colaborador não atende os requisitos de idade da empresa!"));

        verify(this.blacklistRepository, times(1)).findByCpf(anyString());
        verify(this.registerValidator, times(1)).execute(any(Collaborator.class));
        verify(this.repository, times(0)).save(any(Collaborator.class));
    }
}
