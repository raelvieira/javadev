package br.com.mobicare.collaborator.builder;

import br.com.mobicare.collaborator.models.Blacklist;

import java.time.LocalDateTime;

public class BlacklistBuilder {
    private Blacklist blacklist;

    public static BlacklistBuilder builder() {
        BlacklistBuilder builder = new BlacklistBuilder();
        builder.blacklist = new Blacklist();
        builder.blacklist.setCpf("11111111111");
        builder.blacklist.setCreatedAt(LocalDateTime.now());
        builder.blacklist.setId(1);
        builder.blacklist.setName("Name");

        return builder;
    }

    public Blacklist build() {
        return this.blacklist;
    }
}
