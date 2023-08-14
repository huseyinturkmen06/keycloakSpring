package com.alibou.keycloak.service;

import com.alibou.keycloak.dto.KeycloakInput;
import com.alibou.keycloak.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
@Data
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //user exist control method
    public String userExistControl(KeycloakInput keycloakInput){
        if(userRepository.existsById(keycloakInput.getClient_id())){
            return keycloakInput.getClient_id();
        }
        else return "Girilen TC kimlik numarasına ait kullanıcı sistemde bulunamadı";

    }

    //user db de bulunabilirse aşağıdaki url e 4 parametre ile istek atan metod
    //http://localhost:8282/auth/realms/Myrealm/protocol/openid-connect/token
    //şimdilik bize token'ı değil, her şeyi döndürecek

    public  String getToken(KeycloakInput keycloakInput) throws IOException, InterruptedException {

        System.out.printf("getToken metoduna girdi");
//        String url = "http://localhost:8181/realms/Huseyin/protocol/openid-connect/token";
      String url = "http://localhost:8282/auth/realms/Myrealm/protocol/openid-connect/token";

        String grant_type = "password";
        String client_id = keycloakInput.getClient_id();
        String username = keycloakInput.getTel_no();
        String password = keycloakInput.getOperator();
        System.out.println("\n"+client_id+"\n"+username+"\n"+password);

        //şimdilik sabit yaptık

        String requestBody = String.format("grant_type=%s&client_id=%s&username=%s&password=%s",
                URLEncoder.encode(grant_type, StandardCharsets.UTF_8),
                URLEncoder.encode(client_id, StandardCharsets.UTF_8),
                URLEncoder.encode(username, StandardCharsets.UTF_8),
                URLEncoder.encode(password, StandardCharsets.UTF_8)
        );

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.body());
        return jsonNode.get("access_token").asText();
//        return response.body();
    }




}
