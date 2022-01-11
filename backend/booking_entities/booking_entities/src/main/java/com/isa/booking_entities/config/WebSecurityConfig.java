package com.isa.booking_entities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.isa.booking_entities.security.TokenUtils;
import com.isa.booking_entities.security.auth.RestAuthenticationEntryPoint;
import com.isa.booking_entities.security.auth.TokenAuthenticationFilter;
import com.isa.booking_entities.services.CustomUserDetailsService;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Service used for reading application users data
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	// Handler for returning 401 when a client with invalid username and password
	// tries to access the resource
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	// Registering the authentication manager which will do the user authentication
	// for us
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Definition of instructions for the authentication manager
	// Defining which service should the authentication manager use to extract user
	// for authentication data
	// Defining which encoder should be used to encode the password from the user's
	// request so the resulting hash can be compared with the hash from the database
	// using the bcrypt algorithm
	// (the password in the database is not in plain text)
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Injection of TokenUtils class implementation so we can use it's methods for
	// JWT in TokenAuthenticationFilteru
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// komunikacija izmedju klijenta i servera je stateless posto je u pitanju REST aplikacija
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				// sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				// svim korisnicima dopusti da pristupe putanjama /auth/**, (/h2-console/** ako se koristi H2 baza) i /api/foo
				.authorizeRequests().antMatchers("/auth/**").permitAll().antMatchers("/h2-console/**").permitAll().antMatchers("/api/foo").permitAll()
				.antMatchers("/auth/confirm_account/*").permitAll()
				// za svaki drugi zahtev korisnik mora biti autentifikovan
				.anyRequest().authenticated().and()
				// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
				.cors().and()

				// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
						BasicAuthenticationFilter.class);
		// zbog jednostavnosti primera
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/auth/registration");
		web.ignoring().antMatchers(HttpMethod.PUT, "/auth/confirm_account/*");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}
}
