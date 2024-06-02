package com.appsdeveloperblog.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.UserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.storage.adapter.AbstractUserAdapter;

public class UserAdapter extends AbstractUserAdapter {
	
	private final User user;
	
	public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, User user) {
		super(session, realm, storageProviderModel);
		this.user = user;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	@Override
	public String getFirstName() {
		return user.getFirstName();
	}
	
	@Override
	public String getLastName() {
		return user.getLastName();
	}
	
	@Override
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public SubjectCredentialManager credentialManager() {
		return new UserCredentialManager(session, realm, this);
	}

}
