package server.admin.paintball.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import server.admin.paintball.config.AppConfig
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val appConfig: AppConfig
) : WebSecurityConfigurerAdapter() {

    companion object {
        const val LOGIN_URL = "/auth/login"
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        return DaoAuthenticationProvider().apply {
            setUserDetailsService(userDetailsService)
            setPasswordEncoder(passwordEncoder())
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity) {
        var unAuthorized = emptyArray<String>()
        if (!appConfig.isApiProtected) {
            unAuthorized = arrayOf("/api/**")
        }

        http
                .cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint { _, httpServletResponse, authenticationException ->
                    authenticationException?.let {
                        httpServletResponse.status = HttpServletResponse.SC_UNAUTHORIZED
                    }
                }
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**",
                        "/api/user/register",
                        "/api/map/image/**",
                        "/api/game/**",
                        "/api/user",
                        "/api/mqtt/test/**"
                ).permitAll()
                .antMatchers(*unAuthorized).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl(LOGIN_URL).permitAll()
                .and()
                .logout().logoutUrl("/auth/logout").permitAll().clearAuthentication(true).invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/h2-console/**")
    }
}
