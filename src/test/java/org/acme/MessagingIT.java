package org.acme;

import lombok.SneakyThrows;
import lombok.extern.jbosslog.JBossLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.*;

import javax.jms.*;

@JBossLog
public class MessagingIT {
    ConnectionFactory connectionFactory;

    @SneakyThrows
    public MessagingIT() {
        connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    }

    @Test
    public void happyPath() throws JMSException {
        System.out.println("I am here");
        log.info("Happy path starting..");
        ActiveMQQueue topic = new ActiveMQQueue("MY.TOPIC");

        Connection conn = connectionFactory.createConnection();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /*
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                System.out.println("Received: " + ((ActiveMQTextMessage)message).getText());
            }
        });
         */

        MessageProducer producer = session.createProducer(topic);

        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText("Hello World");
        producer.send(message);

        session.close();
        conn.close();
    }
}
