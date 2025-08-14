package com.example.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of Spring's AuditorAware interface.
 * Provides the current auditor (user) for auditing purposes.
 * In this implementation, a fixed auditor value "CARDS_MS" is used.
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    
    /**
     * Returns the current auditor (user) for auditing purposes.
     * In this implementation, a fixed value "CARDS_MS" is returned.
     * In a real application, this would typically return the current authenticated user's ID.
     *
     * @return An Optional containing the current auditor's identifier
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}
