package com.moa.global.config.security

import com.moa.global.security.jwt.filter.JwtAuthenticationFilter
import com.moa.global.security.jwt.filter.JwtExceptionFilter
import com.moa.global.security.jwt.handler.JwtAccessDeniedHandler
import com.moa.global.security.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtExceptionFilter: JwtExceptionFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf { it.disable() }
        .cors { it.configurationSource(corsConfigurationSource()) }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .rememberMe { it.disable() }

        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        .exceptionHandling {
            it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            it.accessDeniedHandler(jwtAccessDeniedHandler)
        }

        .authorizeHttpRequests { it
            .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/reissue").anonymous()
            .requestMatchers(HttpMethod.GET, "/users/me").authenticated()

            .requestMatchers(HttpMethod.GET, "/clubs/applies").authenticated()
            .requestMatchers(HttpMethod.GET, "/clubs").permitAll()
            .requestMatchers(HttpMethod.POST, "/clubs/:clubId/apply").authenticated()
            .requestMatchers(HttpMethod.POST, "/clubs/:clubId/cancel").authenticated()

            .requestMatchers(HttpMethod.POST, "/admin/clubs").authenticated()

            .requestMatchers("/notices").permitAll()
            .anyRequest().authenticated()
        }

        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .addFilterBefore(jwtExceptionFilter, jwtAuthenticationFilter.javaClass)

        .build()

    fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().apply {
        registerCorsConfiguration("/**", CorsConfiguration().apply {
            allowedOriginPatterns = listOf("*")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
            maxAge = 3600
        })
    }
}