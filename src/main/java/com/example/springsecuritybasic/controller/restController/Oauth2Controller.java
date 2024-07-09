package com.example.springsecuritybasic.controller.restController;

import com.example.springsecuritybasic.service.GitHubClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Oauth2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);

    @Autowired
    private GitHubClientService gitHubClientService;

    /*@GetMapping("/")
    private String getTeste(OAuth2AuthenticationToken token) {
        logger.info("principal " + token.getPrincipal().toString());
        logger.info("token " + token);

        return "secure.html";
    }*/

    @GetMapping("/")
    public String index(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("logging token");
        logger.info("{}", oAuth2AuthorizedClient.getAccessToken().getTokenValue());

        String teste = gitHubClientService.testGitHubClient(oAuth2AuthorizedClient.getAccessToken().getTokenValue());

        logger.info("teste: {}", teste);

        return "secure.html";
    }
}
