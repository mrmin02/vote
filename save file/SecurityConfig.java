// package com.vote.vote.config;
// public class SecurityConfig {
    

// import java.util.ArrayList;
// import java.util.List;

// import com.vote.vote.O2.CustomOAuth2Provider;
// import com.vote.vote.O2.CustomOAuth2UserService;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.oauth2.client.registration.ClientRegistration;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
// import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
// import org.springframework.web.client.RestTemplate;
// @Configuration
// @EnableWebSecurity 
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//     @Override  
// 	protected void configure(HttpSecurity http) throws Exception{   
		
// 		http
// 		.authorizeRequests()    
// 			.antMatchers("/auth","/oauth2/**","/").permitAll()
// 			.antMatchers("/css/**").permitAll() //프론트 권한
// 			.antMatchers("/vendor/**").permitAll() //프론트 권한
// 			.antMatchers("/js/**").permitAll() // 프론트 권한
// 			.antMatchers("/img/**").permitAll() // 프론트 권한
// 			.antMatchers("/").permitAll() // 모든 권한을 줌.=로그인 필요 없음.     
// 			.antMatchers("/auth/register").permitAll()
// 			.antMatchers("/auth/oauth2/**").permitAll()
// 			// .antMatchers("/posts/**").hasRole("USER")
// 			.antMatchers("/home").hasRole("USER")
// //			.antMatchers("/").hasAnyRole("USER","ADMIN")
// 			// user 권한만 접근 가능.     
// 			.antMatchers("/logout").permitAll()     
// 			.anyRequest().authenticated() // 로그인 체크함.     
// 			.and()    
// 		.formLogin()     
// 			.loginPage("/auth") // 이 줄을 지우면 스프링이 제공하는 폼이 출력됨.     
// 			.permitAll()     
// 			.successHandler(successHandler())     
// 			.and()    
// 		.logout()    
// 			.logoutUrl("/logout")    
// 			.logoutSuccessUrl("/")
// 			.invalidateHttpSession(true)    
// 			.and()   
// 		.csrf() 
// 			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())    
// 			.and()


// 		.oauth2Login() 
// 			.userInfoEndpoint()
// 			.userService(new CustomOAuth2UserService()) // 네이버 USER INFO의 응답을 처리하기 위한 설정 
// 			.and() 
// 		.defaultSuccessUrl("/") 
// 			.failureUrl("/") 
// 			.and() 
// 		.exceptionHandling() 
// 		.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth"));

			
// 		// .oauth2Login();
// 		// oauth2Login -> oauth2Login.userInfoEndpoint(
// 			// userInfoEndpoint -> userInfoEndpoint.customUserType(KakaoOAuth2User.class, "kakao"))
// 		// 	.loginPage("/auth") // 이 줄을 지우면 스프링이 제공하는 폼이 출력됨.     
// 		// 	.permitAll()     
// 		// 	.successHandler(successHandler())
// 		// 	.and();  
// 	} 

// 	@Bean
// 	public ClientRegistrationRepository clientRegistrationRepository(
// 		@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
// 		String kakaoClientId,
// 		@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
// 		String kakaoClientSecret){
// 			List<ClientRegistration> registrations = new ArrayList<>();
// 			registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
// 			.clientId(kakaoClientId)
// 			.clientSecret(kakaoClientSecret)
// 			.jwkSetUri("temp")
// 			.build());

// 			System.out.println("------------clientRegistrationRepository-------------");
// 			return new InMemoryClientRegistrationRepository(registrations);
// 		}
// 	// @Bean
// 	// public ClientRegistrationRepository clientRegistrationRepository(
// 	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
// 	// 	String kakaoClientId,
// 	// 	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
// 	// 	String kakaoClientSecret){
// 	// 		List<ClientRegistration> registrations = new ArrayList<>();
// 	// 		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
// 	// 		.clientId(kakaoClientId)
// 	// 		.clientSecret(kakaoClientSecret)
// 	// 		.jwkSetUri("temp")
// 	// 		.build());

// 	// 		System.out.println("------------clientRegistrationRepository-------------");
// 	// 		return new InMemoryClientRegistrationRepository(registrations);
// 	// 	}
// 	@Bean
//     public ClientRegistrationRepository clientRegistrationRepository(
//             OAuth2ClientProperties oAuth2ClientProperties,
//             @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId,
//             @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret)
//             // @Value("${custom.oauth2.naver.client-id}") String naverClientId,
// 			// @Value("${custom.oauth2.naver.client-secret}") String naverClientSecret) 
// 			{

// 			List<ClientRegistration> registrations = new ArrayList<>();
// 			registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
// 			.clientId(kakaoClientId)
// 			.clientSecret(kakaoClientSecret)
// 			.jwkSetUri("temp")
// 			.build());				
//         // List<ClientRegistration> registrations = oAuth2ClientProperties
//         //         .getRegistration().keySet().stream()
//         //         .map(client -> getRegistration(oAuth2ClientProperties, client))
//         //         .filter(Objects::nonNull)
//         //         .collect(Collectors.toList());

//         // registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
//         //             .clientId(kakaoClientId)
//         //             .clientSecret(kakaoClientSecret)
//         //             .jwkSetUri("temp")
//         //             .build());

//         // registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
//         //         .clientId(naverClientId)
//         //         .clientSecret(naverClientSecret)
//         //         .jwkSetUri("temp")
//         //         .build());
//         return new InMemoryClientRegistrationRepository(registrations);
//     }
// 	@Bean
// 	public RestTemplate restTemplate() {
//     return new RestTemplate();
// }


// 	public AuthenticationSuccessHandler successHandler() {
// 		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
// 		// System.out.println("성공");
// 		return handler;
// 	}

// }
// }