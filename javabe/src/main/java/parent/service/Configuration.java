package parent.service;


import parent.exceptions.BadConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Singleton
public class Configuration {


    private static Configuration instance;
    private Properties properties;

    private Configuration() {
    }

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
            Properties properties = new Properties();
            String confFile = System.getProperty("application.file");
            try {
                properties.load(new FileInputStream(confFile));
            } catch (IOException e) {
                throw new BadConfigurationException("The file was not found at location: " + confFile);
            }
            instance.properties = properties;
        }
        return instance;
    }

    public String getKeyCloakURL() {
        return properties.getProperty("keycloak.url");
    }

    public String getKeyCloakRealm(){
        return properties.getProperty("keycloak.realm");
    }

    public String getKeyCloakClientID(){
        return properties.getProperty("keycloak.client.id");
    }

    public String getKeyCloakSecretKey(){
        return properties.getProperty("keycloak.client.secret.key");
    }

}