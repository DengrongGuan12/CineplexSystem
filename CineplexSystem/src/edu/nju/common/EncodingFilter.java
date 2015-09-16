package edu.nju.common;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class EncodingFilter implements Filter {
	private FilterConfig filterConfig = null;
	private String encoding = null;
	private boolean ignore = true;
	public final void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}
	public final void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// 根据配置信息设置Request对象的编码信息
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding1 = selectEncoding(request);
			if (encoding1 != null) {
				request.setCharacterEncoding(encoding1);
			}
		}
		chain.doFilter(request, response);
	}
	public final void init(final FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
		// 从Web.xml中的过滤器配置信息中获得编码值
		this.encoding = filterConfig.getInitParameter("encoding");
		// 根据参数的值，决定是否忽略编码设置
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}
	}
	protected final String selectEncoding(final ServletRequest request) {
		return (this.encoding);
	}
}
