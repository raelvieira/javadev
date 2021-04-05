package br.com.mobicare.collaborator.modules.collaborator.controller;

import br.com.mobicare.collaborator.modules.collaborator.controller.json.request.CollaboratorRequest;
import br.com.mobicare.collaborator.modules.collaborator.controller.json.response.CollaboratorResponse;
import br.com.mobicare.collaborator.modules.collaborator.service.FindCollaborator;
import br.com.mobicare.collaborator.modules.collaborator.service.ImportBlacklistService;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterCollaboratorService;

import br.com.mobicare.collaborator.modules.collaborator.service.UpdateCollaboratorService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorController {

    private final RegisterCollaboratorService registerCollaborator;
    private final ImportBlacklistService importBlacklistService;
    private final FindCollaborator findCollaborator;
    private final UpdateCollaboratorService updateCollaboratorService;

    public CollaboratorController(RegisterCollaboratorService registerCollaborator,
                                  ImportBlacklistService importBlacklistService, FindCollaborator findCollaborator,
                                  UpdateCollaboratorService updateCollaboratorService) {
        this.registerCollaborator = registerCollaborator;
        this.importBlacklistService = importBlacklistService;
        this.findCollaborator = findCollaborator;
        this.updateCollaboratorService = updateCollaboratorService;
    }

    @GetMapping("/import-blacklist")
    public ResponseEntity<Object> importBlacklist() {
        importBlacklistService.execute();
        return ok().build();
    }

    @PostMapping
    @CacheEvict(value = "collaboratorList", allEntries = true)
    public ResponseEntity<CollaboratorResponse> register(@RequestBody @Valid CollaboratorRequest request,
                                                         UriComponentsBuilder uriBuilder) {
        final var response = this.registerCollaborator.execute(request);
        URI uri = uriBuilder.path("/collaborator/").buildAndExpand(response.getId()).toUri();
        return created(uri).body(response);
    }

    @GetMapping
    @Cacheable(value = "collaboratorList")
    public List<CollaboratorResponse> findAllBySector(){
        return this.findCollaborator.findAllBySector();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorResponse> collaboratorById(@PathVariable("id") Integer id) {
        return ok(this.findCollaborator.findById(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CollaboratorResponse> collaboratorByCpf(@PathVariable("cpf") String cpf) {
        return ok(this.findCollaborator.findByCpf(cpf));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "collaboratorList", allEntries = true)
    public ResponseEntity<Object> deleteCollaborator(@PathVariable("id") Integer id) {
        this.updateCollaboratorService.delete(id);
        return ok().build();
    }
}
