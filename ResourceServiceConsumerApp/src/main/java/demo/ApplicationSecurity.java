package demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
//@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	
	/*public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home").hasRole("USER")
				.anyRequest().fullyAuthenticated().and()
				.formLogin().loginPage("/login").failureUrl("/loginfail").usernameParameter("name").passwordParameter("password").permitAll().and()
				.logout().logoutSuccessUrl("/login?logout");
		//http.authorizeRequests().antMatchers("/home").hasRole("USER").anyRequest().fullyAuthenticated();
	}
*/
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN").and().withUser("user").password("user")
				.roles("USER");
			
	}
	
	/*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/public/**").permitAll()
                .antMatchers("/homepage").hasAuthority("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/loginpage")
                .failureUrl("/login?error")
                .usernameParameter("'j_username'").passwordParameter("j_password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .rememberMe();
    }*/
}



