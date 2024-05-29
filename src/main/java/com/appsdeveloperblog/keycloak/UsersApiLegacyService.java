package com.appsdeveloperblog.keycloak;

import org.jboss.logging.Logger;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.models.KeycloakSession;

public class UsersApiLegacyService {

	private static final Logger LOGGER = Logger.getLogger(UsersApiLegacyService.class);

	private final KeycloakSession session;

	public UsersApiLegacyService(KeycloakSession session) {
		this.session = session;
	}

	public User getUserByUserName(String username) {

		User user = null;

		try {
			user = SimpleHttp.doGet("http://localhost:8099/users/" + username, session).asJson(User.class);
		} catch (Exception ex) {
			LOGGER.error("Error fetching user " + username + " from external service: " + ex.getMessage(), ex);
		}

		return user;
	}

}
