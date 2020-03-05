package com.vote.vote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override  
	protected void configure(HttpSecurity http) throws Exception{   
		http    
		.authorizeRequests()    
			.antMatchers("/css/**").permitAll() //프론트 권한
			.antMatchers("/vendor/**").permitAll() //프론트 권한
			.antMatchers("/js/**").permitAll() // 프론트 권한
			.antMatchers("/img/**").permitAll() // 프론트 권한
			.antMatchers("/").permitAll() // 모든 권한을 줌.=로그인 필요 없음.     
			.antMatchers("/auth/register").permitAll()
			// .antMatchers("/posts/**").hasRole("USER")
			.antMatchers("/home").hasRole("USER")
//			.antMatchers("/").hasAnyRole("USER","ADMIN")
			// user 권한만 접근 가능.     
			.antMatchers("/logout").permitAll()     
			.anyRequest().authenticated() // 로그인 체크함.     
			.and()    
		.formLogin()     
			.loginPage("/auth") // 이 줄을 지우면 스프링이 제공하는 폼이 출력됨.     
			.permitAll()     
			.successHandler(successHandler())     
			.and()    
		.logout()    
			.logoutUrl("/logout")    
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)    
			.and()   
		.csrf() 
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())    
		;  
	} 
	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		// System.out.println("성공");
		return handler;
	}
}