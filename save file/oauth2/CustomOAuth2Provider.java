// package com.vote.vote.oauth2;

// import org.springframework.security.oauth2.client.registration.ClientRegistration;
// import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
// import org.springframework.security.oauth2.core.AuthorizationGrantType;

// public enum CustomOAuth2Provider{
    
//     KAKAO {
//         @Override
//         public ClientRegistration.Builder getBuilder(String registrationId){
//             ClientRegistration.Builder builder = getBuilder(registrationId,ClientAuthenticationMethod.POST,DEFAULT_LOGIN_REDIRECT_URL)
//             .scope("profile")
//             .authorizationUri("https://kauth.kakao.com/oauth/authorize")
//             .tokenUri("https://kauth.kakao.com/oauth/token")
//             .userInfoUri("https://kapi.kakao.com/v2/user/me")
//             .userNameAttributeName("id")
//             .clientName("Kakao");
//             return builder;
//         }
//     };
//     private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/auth/oauth2/code/{registrationId}";

//     protected final ClientRegistration.Builder getBuilder(
//         String registrationId, ClientAuthenticationMethod method, String redirectUri
//     ){
//         ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId)
//         .clientAuthenticationMethod(method)
//         .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//         .redirectUriTemplate(redirectUri);
//         return builder;
//     }
//     public abstract ClientRegistration.Builder getBuilder(String registrationId);
//     // GOOGLE {

// 	// 	@Override
// 	// 	public Builder getBuilder(String registrationId) {
// 	// 		ClientRegistration.Builder builder = getBuilder(registrationId,
// 	// 				ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
// 	// 		builder.scope("openid", "profile", "email");
// 	// 		builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
// 	// 		builder.tokenUri("https://www.googleapis.com/oauth2/v4/token");
// 	// 		builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
// 	// 		builder.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo");
// 	// 		builder.userNameAttributeName(IdTokenClaimNames.SUB);
// 	// 		builder.clientName("Google");
// 	// 		return builder;
// 	// 	}
// 	// },
// }