/**
 * 
 */
package com.duyi.commons.resolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.duyi.commons.page.Sort;
import com.duyi.commons.page.Sort.Direction;
import com.duyi.commons.page.Sort.Order;
import com.duyi.commons.page.SortDefault;
import com.duyi.commons.page.SortDefault.SortDefaults;
import com.duyi.commons.utils.SpringDataAnnotationUtils;

/**
 * {@link HandlerMethodArgumentResolver} to automatically create {@link Sort} instances from request parameters or
 * {@link SortDefault} annotations.
 * 
 * @since 1.6
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @author Nick Williams
 * @author Mark Paluch
 */
public class SortHandlerMethodArgumentResolver implements SortArgumentResolver {

	private static final String DEFAULT_PARAMETER = "sort";
	private static final String DEFAULT_PROPERTY_DELIMITER = ",";
	private static final String DEFAULT_QUALIFIER_DELIMITER = "_";
	private static final Sort DEFAULT_SORT = null;

	private static final String SORT_DEFAULTS_NAME = SortDefaults.class.getSimpleName();
	private static final String SORT_DEFAULT_NAME = SortDefault.class.getSimpleName();

	private Sort fallbackSort = DEFAULT_SORT;
	private String sortParameter = DEFAULT_PARAMETER;
	private String propertyDelimiter = DEFAULT_PROPERTY_DELIMITER;
	private String qualifierDelimiter = DEFAULT_QUALIFIER_DELIMITER;

	/**
	 * Configure the request parameter to lookup sort information from. Defaults to {@code sort}.
	 * 
	 * @param sortParameter must not be {@literal null} or empty.
	 */
	public void setSortParameter(String sortParameter) {

		Assert.hasText(sortParameter, "SortParameter must not be null nor empty!");
		this.sortParameter = sortParameter;
	}

	/**
	 * Configures the delimiter used to separate property references and the direction to be sorted by. Defaults to
	 * {@code}, which means sort values look like this: {@code firstname,lastname,asc}.
	 * 
	 * @param propertyDelimiter must not be {@literal null} or empty.
	 */
	public void setPropertyDelimiter(String propertyDelimiter) {

		Assert.hasText(propertyDelimiter, "Property delimiter must not be null or empty!");
		this.propertyDelimiter = propertyDelimiter;
	}

	/**
	 * Configures the delimiter used to separate the qualifier from the sort parameter. Defaults to {@code _}, so a
	 * qualified sort property would look like {@code qualifier_sort}.
	 * 
	 * @param qualifierDelimiter the qualifier delimiter to be used or {@literal null} to reset to the default.
	 */
	public void setQualifierDelimiter(String qualifierDelimiter) {
		this.qualifierDelimiter = qualifierDelimiter == null ? DEFAULT_QUALIFIER_DELIMITER : qualifierDelimiter;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Sort.class.equals(parameter.getParameterType());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	 */
	@Override
	public Sort resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		String[] directionParameter = webRequest.getParameterValues(getSortParameter(parameter));

		// No parameter
		if (directionParameter == null) {
			return getDefaultFromAnnotationOrFallback(parameter);
		}

		// Single empty parameter, e.g "sort="
		if (directionParameter.length == 1 && !StringUtils.hasText(directionParameter[0])) {
			return getDefaultFromAnnotationOrFallback(parameter);
		}

		return parseParameterIntoSort(directionParameter, propertyDelimiter);
	}

	/**
	 * Reads the default {@link Sort} to be used from the given {@link MethodParameter}. Rejects the parameter if both an
	 * {@link SortDefaults} and {@link SortDefault} annotation is found as we cannot build a reliable {@link Sort}
	 * instance then (property ordering).
	 * 
	 * @param parameter will never be {@literal null}.
	 * @return the default {@link Sort} instance derived from the parameter annotations or the configured fallback-sort
	 *         {@link #setFallbackSort(Sort)}.
	 */
	private Sort getDefaultFromAnnotationOrFallback(MethodParameter parameter) {

		SortDefaults annotatedDefaults = parameter.getParameterAnnotation(SortDefaults.class);
		SortDefault annotatedDefault = parameter.getParameterAnnotation(SortDefault.class);

		if (annotatedDefault != null && annotatedDefaults != null) {
			throw new IllegalArgumentException(
					String.format("Cannot use both @%s and @%s on parameter %s! Move %s into %s to define sorting order!",
							SORT_DEFAULTS_NAME, SORT_DEFAULT_NAME, parameter.toString(), SORT_DEFAULT_NAME, SORT_DEFAULTS_NAME));
		}

		if (annotatedDefault != null) {
			return appendOrCreateSortTo(annotatedDefault, null);
		}

		if (annotatedDefaults != null) {
			Sort sort = null;
			for (SortDefault currentAnnotatedDefault : annotatedDefaults.value()) {
				sort = appendOrCreateSortTo(currentAnnotatedDefault, sort);
			}
			return sort;
		}

		return fallbackSort;
	}

	/**
	 * Creates a new {@link Sort} instance from the given {@link SortDefault} or appends it to the given {@link Sort}
	 * instance if it's not {@literal null}.
	 * 
	 * @param sortDefault
	 * @param sortOrNull
	 * @return
	 */
	private Sort appendOrCreateSortTo(SortDefault sortDefault, Sort sortOrNull) {

		String[] fields = SpringDataAnnotationUtils.getSpecificPropertyOrDefaultFromValue(sortDefault, "sort");

		if (fields.length == 0) {
			return null;
		}

		Sort sort = new Sort(sortDefault.direction(), fields);
		return sortOrNull == null ? sort : sortOrNull.and(sort);
	}

	/**
	 * Returns the sort parameter to be looked up from the request. Potentially applies qualifiers to it.
	 * 
	 * @param parameter will never be {@literal null}.
	 * @return
	 */
	protected String getSortParameter(MethodParameter parameter) {

		StringBuilder builder = new StringBuilder();

		if (parameter != null && parameter.hasParameterAnnotation(Qualifier.class)) {
			builder.append(parameter.getParameterAnnotation(Qualifier.class).value()).append(qualifierDelimiter);
		}

		return builder.append(sortParameter).toString();
	}

	/**
	 * Parses the given sort expressions into a {@link Sort} instance. The implementation expects the sources to be a
	 * concatenation of Strings using the given delimiter. If the last element can be parsed into a {@link Direction} it's
	 * considered a {@link Direction} and a simple property otherwise.
	 * 
	 * @param source will never be {@literal null}.
	 * @param delimiter the delimiter to be used to split up the source elements, will never be {@literal null}.
	 * @return
	 */
	Sort parseParameterIntoSort(String[] source, String delimiter) {

		List<Order> allOrders = new ArrayList<Sort.Order>();

		for (String part : source) {

			if (part == null) {
				continue;
			}

			String[] elements = part.split(delimiter);
			Direction direction = elements.length == 0 ? null : Direction.fromStringOrNull(elements[elements.length - 1]);

			for (int i = 0; i < elements.length; i++) {

				if (i == elements.length - 1 && direction != null) {
					continue;
				}

				String property = elements[i];

				if (!StringUtils.hasText(property)) {
					continue;
				}

				allOrders.add(new Order(direction, property));
			}
		}

		return allOrders.isEmpty() ? null : new Sort(allOrders);
	}

	/**
	 * Folds the given {@link Sort} instance into a {@link List} of sort expressions, accumulating {@link Order} instances
	 * of the same direction into a single expression if they are in order.
	 * 
	 * @param sort must not be {@literal null}.
	 * @return
	 */
	protected List<String> foldIntoExpressions(Sort sort) {

		List<String> expressions = new ArrayList<String>();
		ExpressionBuilder builder = null;

		for (Order order : sort) {

			Direction direction = order.getDirection();

			if (builder == null) {
				builder = new ExpressionBuilder(direction);
			} else if (!builder.hasSameDirectionAs(order)) {
				builder.dumpExpressionIfPresentInto(expressions);
				builder = new ExpressionBuilder(direction);
			}

			builder.add(order.getProperty());
		}

		return builder == null ? Collections.<String>emptyList() : builder.dumpExpressionIfPresentInto(expressions);
	}

	/**
	 * Folds the given {@link Sort} instance into two expressions. The first being the property list, the second being the
	 * direction.
	 * 
	 * @throws IllegalArgumentException if a {@link Sort} with multiple {@link Direction}s has been handed in.
	 * @param sort must not be {@literal null}.
	 * @return
	 */
	protected List<String> legacyFoldExpressions(Sort sort) {

		List<String> expressions = new ArrayList<String>();
		ExpressionBuilder builder = null;

		for (Order order : sort) {

			Direction direction = order.getDirection();

			if (builder == null) {
				builder = new ExpressionBuilder(direction);
			} else if (!builder.hasSameDirectionAs(order)) {
				throw new IllegalArgumentException(String.format(
						"%s in legacy configuration only supports a single direction to sort by!", getClass().getSimpleName()));
			}

			builder.add(order.getProperty());
		}

		return builder == null ? Collections.<String>emptyList() : builder.dumpExpressionIfPresentInto(expressions);
	}

	/**
	 * Helper to easily build request parameter expressions for {@link Sort} instances.
	 * 
	 * @author Oliver Gierke
	 */
	class ExpressionBuilder {

		private final List<String> elements = new ArrayList<String>();
		private final Direction direction;

		/**
		 * Sets up a new {@link ExpressionBuilder} for properties to be sorted in the given {@link Direction}.
		 * 
		 * @param direction must not be {@literal null}.
		 */
		public ExpressionBuilder(Direction direction) {

			Assert.notNull(direction, "Direction must not be null!");
			this.direction = direction;
		}

		/**
		 * Returns whether the given {@link Order} has the same direction as the current {@link ExpressionBuilder}.
		 * 
		 * @param order must not be {@literal null}.
		 * @return
		 */
		public boolean hasSameDirectionAs(Order order) {
			return this.direction == order.getDirection();
		}

		/**
		 * Adds the given property to the expression to be built.
		 * 
		 * @param property
		 */
		public void add(String property) {
			this.elements.add(property);
		}

		/**
		 * Dumps the expression currently in build into the given {@link List} of {@link String}s. Will only dump it in case
		 * there are properties piled up currently.
		 * 
		 * @param expressions
		 * @return
		 */
		public List<String> dumpExpressionIfPresentInto(List<String> expressions) {

			if (elements.isEmpty()) {
				return expressions;
			}

			elements.add(direction.name().toLowerCase());
			expressions.add(StringUtils.collectionToDelimitedString(elements, propertyDelimiter));

			return expressions;
		}
	}

	/**
	 * Configures the {@link Sort} to be used as fallback in case no {@link SortDefault} or {@link SortDefaults} (the
	 * latter only supported in legacy mode) can be found at the method parameter to be resolved.
	 * <p>
	 * If you set this to {@literal null}, be aware that you controller methods will get {@literal null} handed into them
	 * in case no {@link Sort} data can be found in the request.
	 * 
	 * @param fallbackSort the {@link Sort} to be used as general fallback.
	 */
	public void setFallbackSort(Sort fallbackSort) {
		this.fallbackSort = fallbackSort;
	}
}
