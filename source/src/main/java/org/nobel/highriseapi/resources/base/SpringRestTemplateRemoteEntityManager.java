package org.nobel.highriseapi.resources.base;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.nobel.highriseapi.entities.Party;
import org.nobel.highriseapi.entities.Recording;
import org.nobel.highriseapi.entities.base.Entity;
import org.nobel.highriseapi.mapper.HighriseTypeMatcher;
import org.nobel.highriseapi.mapper.PartyConverter;
import org.nobel.highriseapi.mapper.RecordingConverter;
import org.nobel.highriseapi.mapper.SimpleXmlHttpMessageConverter;
import org.nobel.highriseapi.resources.base.EntityResource.UserCredentials;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class SpringRestTemplateRemoteEntityManager implements RemoteEntityManager {

    private static SpringRestTemplateRemoteEntityManager instance;

    public static SpringRestTemplateRemoteEntityManager getInstance() {
        if (instance == null) {
            instance = new SpringRestTemplateRemoteEntityManager();
        }
        return instance;
    }

    private final RestTemplate restTemplate;

    public SpringRestTemplateRemoteEntityManager() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(getXmlConverter());
    }

    public Entity createEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity) {
        HttpHeaders requestHeaders = createAuthorizationHeader(userCredentials);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(entity, requestHeaders);
        ResponseEntity<? extends Entity> result = restTemplate.postForEntity(fullResourceUrl, requestEntity,
                entity.getClass());
        return result.getBody();
    }

    public <E> E getEntity(String fullResourceUrl, UserCredentials userCredentials, Class<E> entityClass) {
        HttpHeaders requestHeaders = createAuthorizationHeader(userCredentials);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

        ResponseEntity<E> responseEntity = restTemplate.exchange(fullResourceUrl, HttpMethod.GET, requestEntity,
                entityClass);

        return responseEntity.getBody();
    }

    public void updateEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity) {

    }

    private HttpHeaders createAuthorizationHeader(final UserCredentials userCredentials) {
        return new HttpHeaders() {
            {
                String auth = userCredentials.user + ":" + userCredentials.password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

    private SimpleXmlHttpMessageConverter getXmlConverter() {
        SimpleXmlHttpMessageConverter xmlConverter = null;

        try {
            Registry registry = new Registry();
            Serializer serializer = new Persister(new RegistryStrategy(registry), new HighriseTypeMatcher());
            registry.bind(Party.class, new PartyConverter(serializer));
            registry.bind(Recording.class, new RecordingConverter(serializer));
            xmlConverter = new SimpleXmlHttpMessageConverter(serializer);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return xmlConverter;
    }

}
