package com.placesware.eventsiowa.config;

//import org.ingini.spring.boot.mongodb.security.MongoDBAuthenticationProvider;
import com.placesware.eventsiowa.DemoAuthenticationFilter;
import com.placesware.eventsiowa.security.DemoAuthenticationProvider;
//import com.placesware.eventsiowa.security.MongoDBAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MongoDBAuthenticationProvider authenticationProvider;

    @Autowired
    private DemoAuthenticationProvider demoAuthenticationProvider;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.formLogin().defaultSuccessUrl("/resource")
////                .and().logout().and().authorizeRequests()
////                .antMatchers("/index.html", "/home.html", "/login.html", "/", "/access", "/logout").permitAll().anyRequest()
////                .authenticated()
////                .and().csrf().disable();
//        http
//
//                .authorizeRequests().and()
//                .addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .antMatcher("/events/**")
//        ;
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
//                antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
                anyRequest().authenticated().
                and().
                anonymous().disable();//.
//                exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter.class);

//        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
//                addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

//    private String[] actuatorEndpoints() {
//        return new String[]{ApiController.AUTOCONFIG_ENDPOINT, ApiController.BEANS_ENDPOINT, ApiController.CONFIGPROPS_ENDPOINT,
//                ApiController.ENV_ENDPOINT, ApiController.MAPPINGS_ENDPOINT,
//                ApiController.METRICS_ENDPOINT, ApiController.SHUTDOWN_ENDPOINT};
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(demoAuthenticationProvider);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }
}