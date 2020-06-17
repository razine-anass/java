package org.sid;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//Le filtre va intercepter toutes les requêtes et vérifier la présence d’un JWT, et va ensuite valider le token et récupérer
//l’objet “authentication” pour l’ajouter au contexte de spring-security. le client sera donc authentifié 
//pour la suite de l’exécution de sa requête. À la fin du processus, on supprime l’authentification du contexte
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MonUserDetailsService userDetailsService;

    @Autowired
    private JsonWebToken jwtUtil;

    //Valider le jeton
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("BEARER ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
             // on valide le jeton
            if (jwtUtil.validateToken(jwt, userDetails)) {
                //on récupére les données du user dont le jwt est valide
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //on ajouter le user dont le jwt est valide au contexte de spring-security
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
