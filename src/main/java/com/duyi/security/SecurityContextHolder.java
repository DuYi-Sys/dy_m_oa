/**
 * 
 */
package com.duyi.security;

import org.springframework.util.Assert;

/**
 * @author wangyan
 *
 */
public abstract class SecurityContextHolder {
	private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();
	public void clearContext() {
		contextHolder.remove();
	}

	public static SecurityContext getContext() {
		SecurityContext ctx = contextHolder.get();

		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}

	public static void setContext(SecurityContext context) {
		Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
		contextHolder.set(context);
	}

	static SecurityContext createEmptyContext() {
		return new SecurityContextImpl();
	}
}
