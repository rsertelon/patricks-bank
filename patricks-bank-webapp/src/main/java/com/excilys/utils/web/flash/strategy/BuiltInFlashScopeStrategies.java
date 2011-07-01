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
package com.excilys.utils.web.flash.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Built-in strategies
 * 
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
public enum BuiltInFlashScopeStrategies implements FlashScopeStrategy {

	/**
	 * Strategy based on a {@link ThreadLocal}
	 * 
	 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
	 */
	MODE_THREADLOCAL() {

		private ThreadLocal<Map<String, Object>> HOLDER = new ThreadLocal<Map<String, Object>>();

		public void clear() {
			HOLDER.set(null);
		}

		public Map<String, Object> getFlash() {
			Map<String, Object> flash = HOLDER.get();
			if (flash == null) {
				flash = new HashMap<String, Object>();
				HOLDER.set(flash);
			}

			return flash;
		}
	},

	/**
	 * Strategy based on an {@link InheritableThreadLocal}
	 * 
	 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
	 */
	MODE_INHERITABLETHREADLOCAL() {

		private ThreadLocal<Map<String, Object>> HOLDER = new InheritableThreadLocal<Map<String, Object>>();

		public void clear() {
			HOLDER.set(null);
		}

		public Map<String, Object> getFlash() {
			Map<String, Object> flash = HOLDER.get();
			if (flash == null) {
				flash = new HashMap<String, Object>();
				HOLDER.set(flash);
			}

			return flash;
		}
	};

	/**
	 * @param name
	 *            the name of the strategy
	 * @return the strategy with the enum name
	 */
	public static FlashScopeStrategy getBuiltIn(String name) {
		try {
			return BuiltInFlashScopeStrategies.valueOf(name);

		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
