package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@ComponentScan(basePackages={"com.cgsinc.consumer.controller","demo"})
public class ResourceServiceAppConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServiceAppConsumerApplication.class, args);
    }
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
    @Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private SecurityProperties security;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/home").access("hasRole('USER')").anyRequest().fullyAuthenticated()
			.and()
			    .formLogin().loginPage("/loginpage").failureUrl("/loginfail").permitAll()
			    .usernameParameter("j_username").passwordParameter("j_password")		
			.and()
			    .logout().logoutSuccessUrl("/login?logout")
			.and()
			    .csrf(); 	
			
			
			
			
			
			/*http.authorizeRequests().anyRequest().fullyAuthenticated().
			antMatchers("/hello").access("hasRole('USER')").
			and().formLogin()
					.loginPage("/loginpage").usernameParameter("name").passwordParameter("password").permitAll()
					;*/
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin")
					.roles("ADMIN").and().withUser("user").password("user")
					.roles("USER");
		}

	}
}
