package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.extern.jbosslog.JBossLog;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@JBossLog
@Named("ruleProcessor")
@RegisterForReflection
@ApplicationScoped
public class RuleProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("I am here. Got: " + exchange.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        final NotificationMessage message = mapper.readValue(exchange.getMessage().getBody().toString(),
                NotificationMessage.class);

        log.info("Parsed: " + message);
    }
}
