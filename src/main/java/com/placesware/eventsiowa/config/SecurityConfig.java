package com.placesware.eventsiowa.config;

import com.placesware.eventsiowa.security.CustomAuthenticationFilter;
import com.placesware.eventsiowa.security.CustomAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@ComponentScan
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomAuthorization permissionEvaluator;
//
//    @Bean
//    public DefaultMethodSecurityExpressionHandler expressionHandler()
//    {
//        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
//        handler.setPermissionEvaluator(permissionEvaluator);
//        return handler;
//    }


//    @Configuration
//    @EnableGlobalMethodSecurity(prePostEnabled = true)
//    static class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{
//        @Autowired
//        private CustomAuthorization customAuthorization;

//        @Bean(name = "spPermissionEvaluator")
//        public PermissionEvaluator permissionEvaluator() {
//            return new CustomAuthorization();
//        }

        //An expression handler used to secure methods. This overrides the DefaultMethodSecurityExpressionHandler to include roleHierarchy.
//        @Bean
//        public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler( RoleHierarchy roleHierarchy ) {
//            DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//
//            //To use hasPermission() expressions, we have to configure a PermissionEvaluator See 15.3.2 Built-In Expression
//            //@http://static.springsource.org/spring-security/site/docs/3.0.x/reference/el-access.html#el-permission-evaluator -->
//
//            expressionHandler.setPermissionEvaluator( new CustomPermissionEvaluator().permissionEvaluator() );
//            expressionHandler.setRoleHierarchy( roleHierarchy );
//
//            return expressionHandler;
//
//        }

//        @Override
//        protected MethodSecurityExpressionHandler createExpressionHandler(){
//            DefaultMethodSecurityExpressionHandler handler =
//                    new DefaultMethodSecurityExpressionHandler();
//            handler.setPermissionEvaluator(customAuthorization);
//            return handler;
//        }

   // }

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

        http.addFilterBefore(new CustomAuthenticationFilter(), BasicAuthenticationFilter.class);

    }

}