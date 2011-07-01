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

import java.lang.reflect.Constructor;
import java.util.Map;

import com.excilys.utils.web.flash.strategy.BuiltInFlashScopeStrategies;
import com.excilys.utils.web.flash.strategy.FlashScopeStrategy;

/**
 * Stores the current FlashScope so that it can be accessed without relying on
 * the underliying HTTP storage. The underlying storage strategy can be
 * configured with the System property "excilys.flash.strategy". Build in values
 * are MODE_THREADLOCAL (default) and MODE_INHERITABLETHREADLOCAL. One can also
 * configure a custom strategy by passing its Class name.
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
public class FlashScope {

	public static final class Binder {

		private final String name;

		private Binder(String name) {
			this.name = name;
		}

		/**
		 * @param value
		 *            The value to be set for the associated attribute name
		 */
		public void to(Object value) {
			setFlashAttribute(name, value);
		}
	}

	/**
	 * Call this method to set flash attributes. The fluid API should be used
	 * the following way : {@link FlashScopeHandler}.bind(name).to(value);
	 * 
	 * @param name
	 *            the flash attribute name
	 * @return a {@link Binder} on which you can call the
	 *         {@link Binder#to(Object)} method to set the attribute value.
	 */
	public static Binder bind(String name) {
		return new Binder(name);
	}

	public static final String SYSTEM_PROPERTY = "excilys.flash.strategy";

	private static String strategyName = System.getProperty(SYSTEM_PROPERTY);

	private static FlashScopeStrategy strategy;

	static {
		initialize();
	}

	/**
	 * Try to determine a strategy depending on the name system property
	 */
	private static void initialize() {
		if ((strategyName == null) || "".equals(strategyName)) {
			// Set default
			strategy = BuiltInFlashScopeStrategies.MODE_THREADLOCAL;

		} else {
			strategy = BuiltInFlashScopeStrategies.getBuiltIn(strategyName);
		}

		if (strategy == null) {
			// Try to load a custom strategy
			try {
				Class<?> clazz = Class.forName(strategyName);
				Constructor<?> customStrategy = clazz.getConstructor();
				strategy = FlashScopeStrategy.class.cast(customStrategy.newInstance());
			} catch (Exception ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}

		if (strategy == null) {
			throw new ExceptionInInitializerError("Impossible to find a strategy for " + strategyName);
		}
	}

	static Map<String, Object> getFlash() {
		return strategy.getFlash();
	}

	static void clear() {
		strategy.clear();
	}

	private static void setFlashAttribute(String name, Object value) {
		getFlash().put(name, value);
	}
}
