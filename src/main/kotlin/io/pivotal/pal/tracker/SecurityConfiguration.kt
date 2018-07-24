package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfiguration(
        @Value("\${HTTPS_DISABLED:false}") var httpsDisabled: Boolean) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        if (!httpsDisabled) {
            http.requiresChannel().anyRequest().requiresSecure();
        }

        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic()
        http.csrf().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
    }

}