package com.getir.mail;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;

public class Mail  extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        rest()
        .get("send").to("direct:mr");



        from("direct:mr")
        .process(e->{
            e.getIn().setBody("Message");
            e.getIn().setHeader("to", "whois");
            e.getIn().setHeader("subject", "Subject");
        })
        .toD(
                        "smtp://"
                                + "{{notification.mail.uri}}"
                                + "?username="
                                + "{{notification.mail.username}}"
                                + "&password="
                                + "{{notification.mail.password}}"
                                + "&from="
                                + "{{notification.mail.from}}"
                                + "&to="
                                + "${header.to}"
                                + "&cc="
                                + "${header.cc}"
                                + "&subject="
                                + "${header.subject}"
                                + "&contentType="
                                + "{{notification.mail.content-type}}"
                                + "&mail.smtp.starttls.enable=true")
                                .log("mail basariyla gonderildi");

        
    }

    
}
