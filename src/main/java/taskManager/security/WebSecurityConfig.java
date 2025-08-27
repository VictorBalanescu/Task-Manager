package taskManager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication()
	        .dataSource(dataSource)
	        .usersByUsernameQuery(
	            "SELECT username, password, true FROM credentials WHERE username = ?")
	        .authoritiesByUsernameQuery(
	            "SELECT username, role FROM credentials WHERE username = ?");
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		.csrf().and().cors().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET,"/","/register","/css/**","/error-404").permitAll() //tutti possono acccedere a queste pagine
		.requestMatchers(HttpMethod.POST,"/login","/register").permitAll() //tutti possono mandare richieste post a queste pagine
		.requestMatchers("/home").authenticated()
		//.requestMatchers(HttpMethod.POST,"/admin/**").hasAnyAuthority(ADMIN);
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/login").permitAll()
		.defaultSuccessUrl("/home",true)
		.failureUrl("/login?error=true")
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.clearAuthentication(true).permitAll();
		return httpSecurity.build();
	}

}
