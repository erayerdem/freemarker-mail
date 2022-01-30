package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.freemarker.FreemarkerConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

public class ExampleResource extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        rest().get("hello").to("direct:x");


        from("direct:x").transform(constant("hello world12"))
                .transform(constant(Map.of("name","samet","surname","erdem")))
                .to("freemarker://hello/email1.ftl?contentCache=true&templateUpdateDelay=60")
                .log("${body}");
    }
}