package com.ssafy.xten.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.xten.model.dao.UserDao;
import com.ssafy.xten.model.dto.User;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginServiceImpl implements LoginService {

	private final Environment env; // application.properties에서 불러오는 듯
	private final RestTemplate restTemplate = new RestTemplate(); 
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private UserDao userDao;

	public LoginServiceImpl(Environment env) {
		this.env = env;
	}

	public User socialLogin(String code, String registrationId) {
		String accessToken = getAccessToken(code, registrationId);
		JsonNode userResourceNode = getUserResource(accessToken, registrationId);

		User user = new User();
		switch (registrationId) {
		case "google": {
			// user.setId(userResourceNode.get("id").asText());
			user.setEmail(userResourceNode.get("email").asText());
			user.setNickname(userResourceNode.get("name").asText());
			break;
		}
//		case "kakao": {
//			// user.setId(userResourceNode.get("id").asText());
//			user.setEmail(userResourceNode.get("kakao_account").get("email").asText());
//			user.setNickname(userResourceNode.get("kakao_account").get("profile").get("nickname").asText());
//			break;
//		}
//		case "naver": {
//			// user.setId(userResourceNode.get("response").get("id").asText());
//			user.setEmail(userResourceNode.get("response").get("email").asText());
//			user.setNickname(userResourceNode.get("response").get("nickname").asText());
//			break;
//		}
		default: {
			throw new RuntimeException("UNSUPPORTED SOCIAL TYPE");
		}
		}
		log.info("======================================================");
		log.info("userResource = {}", user);
		log.info("accessToken {}", accessToken);
		log.info("userResourceNode {}", userResourceNode);
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

		ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity,
				JsonNode.class);
		JsonNode accessTokenNode = responseNode.getBody();
		return accessTokenNode.get("access_token").asText();
	}

	private JsonNode getUserResource(String accessToken, String registrationId) {
		String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri"); // 유저 정보를 요청하는 주소
		HttpHeaders headers = new HttpHeaders(); 
		headers.set("Authorization", "Bearer " + accessToken); // 헤더에 accessToken 같이 넣어보내야 요청 통과되는듯 
		HttpEntity entity = new HttpEntity(headers); 
		return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
	}

	@Override
	public User normalLogin(String id, String password) {
		User tmp = userDao.selectOne(id);
		if (tmp != null && tmp.getPassword().equals(password))
			return tmp;
		return null;
	}
}
