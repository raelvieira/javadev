package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.exceptions.BadRequestException;
import br.com.mobicare.collaborator.models.Blacklist;
import br.com.mobicare.collaborator.modules.collaborator.repository.BlacklistRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.ImportBlacklistService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ImportBlacklistServiceImpl implements ImportBlacklistService {
    private final RestTemplate restTemplate;

    private final BlacklistRepository blacklistRepository;

    public ImportBlacklistServiceImpl(RestTemplate restTemplate, BlacklistRepository blacklistRepository) {
        this.restTemplate = restTemplate;
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    @Transactional
    @Async
    public void execute() {
        final var blacklist = this.getBlacklist();

        if(blacklist != null) {
            blacklistRepository.saveAll(blacklist);
        }
    }

    private List<Blacklist> getBlacklist() {
        final var exchange = this.restTemplate.exchange(
                "/blacklist",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Blacklist>>() {
                }
        );

        if(exchange.getStatusCodeValue() == 200) {
            return exchange.getBody();
        }

        throw new BadRequestException("Houve um problema para requisitar a importação da Blacklist");
    }
}
