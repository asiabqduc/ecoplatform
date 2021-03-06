package net.brilliance.config.social;
/**
 * 
 *//*
package net.vpx.config.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

*//**
 * @author ducbq
 *
 *//*
@Configuration
public class SocialConfig extends SocialConfigurerAdapter{
  @Autowired
  private DataSource dataSource;

  *//**
   * Configures the connection factories for Facebook and Twitter.
   * @param cfConfig
   * @param env
   *//*
  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
      cfConfig.addConnectionFactory(new TwitterConnectionFactory(
              env.getProperty("twitter.consumer.key"),
              env.getProperty("twitter.consumer.secret")
      ));
      cfConfig.addConnectionFactory(new FacebookConnectionFactory(
              env.getProperty("facebook.app.id"),
              env.getProperty("facebook.app.secret")
      ));
  }

  *//**
   * The UserIdSource determines the account ID of the user. The example application
   * uses the username as the account ID.
   *//*
  @Override
  public UserIdSource getUserIdSource() {
      return new AuthenticationNameUserIdSource();
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
      return new JdbcUsersConnectionRepository(
              dataSource,
              connectionFactoryLocator,
              *//**
               * The TextEncryptor object encrypts the authorization details of the connection. In
               * our example, the authorization details are stored as plain text.
               * DO NOT USE THIS IN PRODUCTION.
               *//*
              Encryptors.noOpText()
      );
  }

  *//**
   * This bean manages the connection flow between the account provider and
   * the example application.
   *//*
  @Bean
  public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
      return new ConnectController(connectionFactoryLocator, connectionRepository);
  }
}*/