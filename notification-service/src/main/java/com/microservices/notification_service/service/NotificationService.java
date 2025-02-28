package com.microservices.notification_service.service;

import com.microservices.notification_service.order.event.OrderPlacedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class NotificationService {

    //@Autowired
    private JavaMailSender javaMailSender;
    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent){
        log.info("Got Message from order-placed topic {}", orderPlacedEvent);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            Hi User,

                            Your order with order number- %s.
                            The product you ordered is- %s. \s
                            Your Order is Successfully Placed.
                            
                            Keep Shopping...!
                            
                            Best Regards
                            Spring Boot Shop
                            """,

                    orderPlacedEvent.getOrderNumber(),
                    orderPlacedEvent.getSkuCode()));

        };
        try {
            Thread.sleep(30000);
            javaMailSender.send(messagePreparator);
            log.info("Order Notification email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

