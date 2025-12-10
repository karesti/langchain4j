package dev.langchain4j.agentic.scope.infinispan;

import java.util.Optional;
import java.util.Set;
import dev.langchain4j.agentic.scope.AgenticScopeKey;
import dev.langchain4j.agentic.scope.AgenticScopeSerializer;
import dev.langchain4j.agentic.scope.AgenticScopeStore;
import dev.langchain4j.agentic.scope.DefaultAgenticScope;
import org.infinispan.client.hotrod.RemoteCache;

public class InfinispanAgenticScopeStore implements AgenticScopeStore {

    private final RemoteCache<AgenticScopeKey, String> scopes;
    public InfinispanAgenticScopeStore(final RemoteCache<AgenticScopeKey, String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public boolean save(AgenticScopeKey key, DefaultAgenticScope agenticScope) {

        scopes.put(key, AgenticScopeSerializer.toJson(agenticScope));
        return true;
    }

    @Override
    public Optional<DefaultAgenticScope> load(AgenticScopeKey key) {
        return Optional.ofNullable(scopes.get(key))
                .map(s -> AgenticScopeSerializer.fromJson(s));
    }

    @Override
    public boolean delete(AgenticScopeKey key) {
        return scopes.remove(key) != null;
    }

    @Override
    public Set<AgenticScopeKey> getAllKeys() {
        return scopes.keySet();
    }
}
