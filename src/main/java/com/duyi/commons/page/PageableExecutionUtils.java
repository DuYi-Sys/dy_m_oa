/**
 * 
 */
package com.duyi.commons.page;

import java.util.List;

import org.springframework.util.Assert;

/**
 * @author wangyan
 *
 */
public abstract class PageableExecutionUtils {
	public PageableExecutionUtils() {
		
	}
	public static Pageable calculatePageable(int count, Pageable pageable) {
		if(count<(pageable.getOffset()+pageable.getPageSize())) {
			pageable=pageable.previousOrFirst();
		}
		return pageable;
	}
	/**
	 * Constructs a {@link Page} based on the given {@code content}, {@link Pageable} and {@link TotalSupplier} applying
	 * optimizations. The construction of {@link Page} omits a count query if the total can be determined based on the
	 * result size and {@link Pageable}.
	 *
	 * @param content must not be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @param totalSupplier must not be {@literal null}.
	 * @return the {@link Page}.
	 */
	public static <T> Page<T> getPage(List<T> content, Pageable pageable, TotalSupplier totalSupplier) {

		Assert.notNull(content, "Content must not be null!");
		Assert.notNull(totalSupplier, "TotalSupplier must not be null!");

		if (pageable == null || pageable.getOffset() == 0) {

			if (pageable == null || pageable.getPageSize() > content.size()) {
				return new PageImpl<T>(content, pageable, content.size());
			}

			return new PageImpl<T>(content, pageable, totalSupplier.get());
		}

		if (content.size() != 0 && pageable.getPageSize() > content.size()) {
			return new PageImpl<T>(content, pageable, pageable.getOffset() + content.size());
		}

		return new PageImpl<T>(content, pageable, totalSupplier.get());
	}

	/**
	 * Supplies the total count for a particular query. Can be replaced with a Java 8 Supplier when upgrading to Java 8.
	 *
	 * @author Mark Paluch
	 */
	public interface TotalSupplier {

		/**
		 * @return the total count for a particular query.
		 */
		long get();
	}
}
