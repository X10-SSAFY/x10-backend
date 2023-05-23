package com.ssafy.xten.model.service;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.xten.model.dto.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class LoginService {

    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    public LoginService(Environment env) {
        this.env = env;
    }

    public User socialLogin(String code, String registrationId) {
        log.info("======================================================");
        String accessToken = getAccessToken(code, registrationId);
        JsonNode userResourceNode = getUserResource(accessToken, registrationId);

        User user = new User();
        log.info("userResource = {}", user);
        switch (registrationId) {
            case "google": {
            	if(accessToken!=null) {
            		System.out.println("토큰 : "+accessToken);
            	}
            	//user.setId(userResourceNode.get("id").asText());
            	user.setEmail(userResourceNode.get("email").asText());
            	user.setNickname(userResourceNode.get("name").asText());
                break;
            } case "kakao": {
            	if(accessToken!=null) {
            		System.out.println("토큰 : "+accessToken);
            	}
            	//user.setId(userResourceNode.get("id").asText());
            	user.setEmail(userResourceNode.get("kakao_account").get("email").asText());
                user.setNickname(userResourceNode.get("kakao_account").get("profile").get("nickname").asText());
                break;
            } case "naver": {
             	if(accessToken!=null) {
            		System.out.println("토큰 : "+accessToken);
            	}
            	if(userResourceNode==null) {
            		System.out.println("얘가 범인이다");
            	}
            	//user.setId(userResourceNode.get("response").get("id").asText());
            	user.setEmail(userResourceNode.get("response").get("email").asText());
            	user.setNickname(userResourceNode.get("response").get("nickname").asText());
                break;
            } default: {
                throw new RuntimeException("UNSUPPORTED SOCIAL TYPE");
            }
        }
        log.info("id = {}", user.getId());
        log.info("email = {}", user.getEmail());
        log.info("nickname {}", user.getNickname());
        log.info("======================================================");
        return user;
    }

    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");
        System.out.println(registrationId);
        System.out.println("여기"+resourceUri);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
