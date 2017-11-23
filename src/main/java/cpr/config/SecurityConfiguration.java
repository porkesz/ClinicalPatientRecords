package cpr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cpr.dao.EmployeRepository;
import cpr.service.CustomEmployeDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = EmployeRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private CustomEmployeDetailsService customEmployeDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customEmployeDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("**/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll();
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
            	System.out.println("encode: " + charSequence.toString());
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	System.out.println("matches: " + charSequence.toString() + "  " + s);
            	if (charSequence.toString().equals(s)) {
            		return true;
            	}
                return false;
            }
        };
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".twig");
        return viewResolver;
    }
}
