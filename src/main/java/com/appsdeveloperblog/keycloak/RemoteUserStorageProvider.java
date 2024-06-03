package com.appsdeveloperblog.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class RemoteUserStorageProvider implements UserLookupProvider, CredentialInputValidator, UserStorageProvider {
	private KeycloakSession session;
	private ComponentModel model;
	private UsersApiLegacyService usersService;

	public RemoteUserStorageProvider(KeycloakSession session, ComponentModel model,
			UsersApiLegacyService usersService) {
		this.session = session;
		this.model = model;
		this.usersService = usersService;
	}
 
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel getUserById(RealmModel realm, String id) {
		StorageId storageId = new StorageId(id);
		String email = storageId.getExternalId();
		return getUserByUsername(realm, email);
	}

	@Override
	public UserModel getUserByUsername(RealmModel realm, String username) {
		UserModel returnValue = null;
		
		User user = usersService.getUserByUserName(username);
		
		if(user!=null) {
			returnValue = new UserAdapter(session, realm, model, user);
		}
		
		return null;
	}

	@Override
	public UserModel getUserByEmail(RealmModel realm, String email) {
		return getUserByUsername(realm, email);
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		// TODO Auto-generated method stub
		return false;
	}

}
