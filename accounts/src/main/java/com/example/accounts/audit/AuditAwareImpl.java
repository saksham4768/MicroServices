package com.example.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor of the application, which is used for auditing purposes.
     * 
     * @return an Optional containing the auditor's identifier (always "ACCOUNTS_MS")
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
