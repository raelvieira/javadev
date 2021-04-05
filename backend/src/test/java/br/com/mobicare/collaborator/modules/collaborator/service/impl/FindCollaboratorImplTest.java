package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.JavaPlenoIsraelVieiraApplication;
import br.com.mobicare.collaborator.builder.CollaboratorBuilder;
import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.FindCollaborator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(
        classes = JavaPlenoIsraelVieiraApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class FindCollaboratorImplTest {
    @MockBean
    private CollaboratorRepository repository;

    @Autowired
    private FindCollaborator service;

    @Test
    void findAllBySectorWithSuccess() {
        final var collaborator = CollaboratorBuilder.builder().build();
        when(this.repository.findAllGroupBySector()).thenReturn(List.of(collaborator));

        final var response = this.service.findAllBySector();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(this.repository, times(1)).findAllGroupBySector();
    }

    @Test
    void findAllBySectorWithEmpty() {
        when(this.repository.findAllGroupBySector()).thenReturn(Lists.emptyList());

        final var response = this.service.findAllBySector();

        assertNotNull(response);
        assertEquals(0, response.size());
        verify(this.repository, times(1)).findAllGroupBySector();
    }

    @Test
    void findCollaboratorByIdWithSuccess() {
        final var collaborator = CollaboratorBuilder.builder().build();
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(collaborator));

        final var response = this.service.findById(1);

        assertNotNull(response);
        assertEquals(collaborator.getName(), response.getName());

        verify(this.repository, times(1)).findById(anyInt());
    }

    @Test
    void returnNotFoundExceptionWhenFindCollaboratorById() {
        when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

        final var exception = assertThrows(
                NotFoundException.class,
                () -> this.service.findById(1),
                "Deve retornar um NotFoundException"
        );

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Não foi encontrado nenhum colaborador com este ID!"));

        verify(this.repository, times(1)).findById(anyInt());
    }

    @Test
    void findByCpfWithSuccess() {
        final var collaborator = CollaboratorBuilder.builder().build();
        when( this.repository.findByCpf(anyString())).thenReturn(Optional.of(collaborator));

        final var response = this.service.findByCpf("11111111111");

        assertNotNull(response);
        assertEquals(collaborator.getName(), response.getName());

        verify(this.repository, times(1)).findByCpf(anyString());
    }

    @Test
    void returnNotFoundExceptionWhenfindByCpf() {
        when(this.repository.findByCpf(anyString())).thenReturn(Optional.empty());

        final var exception = assertThrows(
                NotFoundException.class,
                () -> this.service.findByCpf("11111111111"),
                "Deve retornar um NotFoundException"
        );

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Não foi encontrado nenhum colaborador com este CPF!"));

        verify(this.repository, times(1)).findByCpf(anyString());
    }
}
