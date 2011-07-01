package com.excilys.patricksbank.spring;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.excilys.patricksbank.service.api.UtilisateurService;

@Component
public class RoleBasedTargetUrlResolver extends SimpleUrlAuthenticationSuccessHandler {
	
	@Resource
	UtilisateurService utilisateurService;

	private static final GrantedAuthority AUTHORITY_USER = new GrantedAuthorityImpl("ROLE_USER");

	private static final GrantedAuthority AUTHORITY_ADMIN = new GrantedAuthorityImpl("ROLE_ADMIN");

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Invokes the configured {@code RedirectStrategy} with the URL returned by
	 * the {@code determineTargetUrl} method.
	 * <p>
	 * The redirect will not be performed if the response has already been
	 * committed.
	 */
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		String targetUrl = null;

		logger.info("Connexion : \"{}\" {}", authentication.getName(), authentication.getAuthorities().toString());
		
		if (authentication.getAuthorities().contains(AUTHORITY_ADMIN)) {
			targetUrl = "/admin/home.html";
			request.getSession(true).setAttribute("utilisateur", utilisateurService.getUtilisateurParLogin(authentication.getName()) );
		} else if (authentication.getAuthorities().contains(AUTHORITY_USER)) {
			targetUrl = "/user/home.html";
			request.getSession(true).setAttribute("utilisateur", utilisateurService.getUtilisateurParLogin(authentication.getName()) );
		} else {
			throw new IllegalArgumentException("Unknown authority" + authentication.getAuthorities());
		}

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to {}", targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
