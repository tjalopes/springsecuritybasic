package com.example.springsecuritybasic.service;

import com.example.springsecuritybasic.controller.restController.Oauth2Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubClientService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubClientService.class);

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public String testGitHubClient(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            /* try this url in postman, use the header X-GitHub-Api-Version: 2022-11-28, and add the token to the
            authorization as a bearer token (this token can be retrieved in the controller), change to the wanted user */
            return restTemplate.getForObject("https://api.github.com/users/hugoalexandremf/repos", String.class);
        } catch (HttpStatusCodeException e) {
            logger.info("Error: ", e);
            throw new RuntimeException();
        }
    }
}
