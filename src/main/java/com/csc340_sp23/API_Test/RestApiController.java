package com.csc340_sp23.API_Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gary Li
 */
@RestController
public class RestApiController {

    /**
     * Hello, World
     *
     * @return "Hello, World"
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }

    /**
     * Get a random phrase that uses tech words and make it at this end point
     *
     * @return techy Json
     */
    @GetMapping("/techy")
    public Object getTechy() {
        try {
            String url = "https://techy-api.vercel.app/api/json?";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            System.out.println(root);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /techy";
        }
    }

    /**
     * Get a random quote from Animechan
     *
     * @return The Aniemchan Json
     */
    @GetMapping("/randomanimequote")
    public Object getRandomAnimeQuote() {
        try {
            String url = "https://animechan.vercel.app/api/random";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            System.out.println(root);

            String quoteAnime = root.get("anime").asText();
            String quoteAuthor = root.get("character").asText();
            String quoteContent = root.get("quote").asText();
            System.out.println("Anime: " + quoteAnime);
            System.out.println("character: " + quoteAuthor);
            System.out.println("Quote: " + quoteContent);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /randomanimequote";
        }
    }
    
    /**
     *Get a random quote by anime title
     * 
     * @param title
     * @return Json random quote by anime title
     */
    @GetMapping("/animequote")
    public Object getAnimeQuote(@RequestParam(value = "title", defaultValue = "Naruto") String title) {
        try {
            String url = "https://animechan.vercel.app/api/random/anime?title=" + title;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            System.out.println(root);

            String quoteAnime = root.get("anime").asText();
            String quoteAuthor = root.get("character").asText();
            String quoteContent = root.get("quote").asText();
            System.out.println("Anime: " + quoteAnime);
            System.out.println("character: " + quoteAuthor);
            System.out.println("Quote: " + quoteContent);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /animequote";
        }
    }
}
