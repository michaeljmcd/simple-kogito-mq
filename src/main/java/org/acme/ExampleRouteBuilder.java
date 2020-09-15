package org.acme;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Map;

public class ExampleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        ActiveMQComponent amq = new ActiveMQComponent();
        //amq.setBrokerURL("vm://localhost?broker.persistent=false");
        amq.setBrokerURL("tcp://localhost:61616");
        amq.setUsername("admin");
        amq.setPassword("admin");

        getContext().addComponent("amq", amq);

        from("activemq:queue:MY.QUEUE")
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .log("Got something");
    }
}
