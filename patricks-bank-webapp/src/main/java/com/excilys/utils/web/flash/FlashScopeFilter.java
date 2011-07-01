/*
 * Copyright 2010-2011 Excilys (www.excilys.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.excilys.utils.web.flash;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter in charge of Synchronizing the flash scope in Session and the one in
 * the ThreadLocal.
 * 
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
public class FlashScopeFilter extends AbstractFilter {

	/**
	 * The key to store the Flash in HTTP Session
	 */
	private static final String FLASH_SCOPE_KEY = "com.excilys.utils.web.flash";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			movePreviousFlashFromSessionToRequest(request);
			chain.doFilter(request, response);

		} finally {
			storeCurrentFlashInSession(request);
		}
	}

	/**
	 * Transform the content of the previous Flash as request attributes
	 * 
	 * @param request
	 *            the request
	 */
	private void movePreviousFlashFromSessionToRequest(ServletRequest request) {
		HttpSession session = HttpServletRequest.class.cast(request).getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> flash = Map.class.cast(session.getAttribute(FLASH_SCOPE_KEY));
		if (flash != null) {
			for (Entry<String, Object> attribute : flash.entrySet()) {
				request.setAttribute(attribute.getKey(), attribute.getValue());
			}
			session.setAttribute(FLASH_SCOPE_KEY, null);
		}
	}

	/**
	 * Store the current Flash in the HTTP Session so that it can be retrieved
	 * in the next Request
	 * 
	 * @param request
	 *            the request
	 */
	private void storeCurrentFlashInSession(ServletRequest request) {
		HttpSession session = HttpServletRequest.class.cast(request).getSession();
		session.setAttribute(FLASH_SCOPE_KEY, FlashScope.getFlash());
		FlashScope.clear();
	}
}
