package org.zfz7.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/", "/home", "/webjars/**", "/public/**", "/static").permitAll()
      .antMatchers("/**/*.{js,html,css}").permitAll()
      .antMatchers("/api/**").permitAll()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  }

}
