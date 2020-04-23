package com.ptak.Organizer.Moonshine.Api;

import com.ptak.Organizer.Moonshine.Model.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/production")
public class ProductionController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Value("${moonshiner-application.name}")
    private  String moonshinerApplicationName;

    @RequestMapping("/all")
    public List<Production> getProductions(){

       List<Production> allProductions = Arrays.asList( restTemplate.getForObject("http://"+moonshinerApplicationName+"/production/all", Production[].class));

       return allProductions;
    }

    @RequestMapping("/one")
    public Production getProduction(){

        Production production =  restTemplate.getForObject("http://"+moonshinerApplicationName+"/production/1", Production.class);

        return production;
    }

    @RequestMapping("/oneAsynch")
    public Production getAsynchProduction(){
        Production production= webClientBuilder.build()
                .get()
                .uri("http://"+moonshinerApplicationName+"/production/1")
                .retrieve()
                .bodyToMono(Production.class)
                .block();

        return production;
    }

    @RequestMapping("/allAsynch")
    public List<Production> getAllAsynchProduction(){
        List<Production> productions= Arrays.asList(webClientBuilder.build()
                .get()
                .uri("http://"+moonshinerApplicationName+"/production/all")
                .retrieve()
                .bodyToMono(Production[].class)
                .block());
//test
        return productions;
    }
}
