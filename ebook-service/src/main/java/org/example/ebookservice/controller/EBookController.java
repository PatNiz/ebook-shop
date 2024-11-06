package org.example.ebookservice.controller;

import org.example.ebookservice.EBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ebook-service")
@CrossOrigin("*")
public class EBookController {

    private final String instanceId;

    public EBookController(@Value("${spring.application.name}:${spring.cloud.client.hostname}:${application.instance.unique-id}") String instanceId) {
        this.instanceId = instanceId;
    }

    @GetMapping("/instanceId")
    public String get() {
        return "My id: " + instanceId;
    }

    @GetMapping()
    public  List<EBook> getEBooks(){
        return List.of(
                new EBook("Java knowledge", 20, "Patryk Niziołek", "https://in.bpbonline.com/cdn/shop/products/1021_Front_1600x.jpg?v=1625292682", "https://buy.stripe.com/test_4gw4iZfXEaR5f726oo"),
                new EBook("Reactknowledge", 20, "Patryk Niziołek", "https://static01.helion.com.pl/global/okladki/vbig/reawpr.jpg", "https://buy.stripe.com/test_6oE7vb26O2kz3ok145")
        );
    }


}
