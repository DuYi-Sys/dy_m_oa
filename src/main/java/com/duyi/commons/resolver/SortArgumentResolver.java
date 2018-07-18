/**
 * 
 */
package com.duyi.commons.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.duyi.commons.page.Sort;

/**
 * Argument resolver to extract a {@link Sort} object from a {@link NativeWebRequest} for a particular
 * {@link MethodParameter}. A {@link SortArgumentResolver} can either resolve {@link Sort} itself or wrap another
 * {@link SortArgumentResolver} to post-process {@link Sort}. {@link Sort} resolution yields either in a {@link Sort}
 * object or {@literal null} if {@link Sort} cannot be resolved.
 *
 * @author Mark Paluch
 * @since 1.13
 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver
 */
public interface SortArgumentResolver extends HandlerMethodArgumentResolver {

	/**
	 * Resolves a {@link Sort} method parameter into an argument value from a given request.
	 * 
	 * @param parameter the method parameter to resolve. This parameter must have previously been passed to
	 *          {@link #supportsParameter} which must have returned {@code true}.
	 * @param mavContainer the ModelAndViewContainer for the current request
	 * @param webRequest the current request
	 * @param binderFactory a factory for creating {@link WebDataBinder} instances
	 * @return the resolved argument value, or {@code null}
	 */
	@Override
	Sort resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory);
}