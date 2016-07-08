
package com.fq.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 描述：
 * 2011-12-13
 * @author                            
 * @version V1.0              
 */


public class AjaxPostFilter extends OncePerRequestFilter {

	private static final String IE_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String FF_AJAX_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
	private static final String XMLHTTP_REQUEST = "XMLHttpRequest";
	private static final String AJAX_CHARACTER_ENCODING_UTF8 = "UTF-8";

	
	private String encoding;

	private boolean forceEncoding = false;


	/**
	 * Set the encoding to use for requests. This encoding will be passed into a
	 * {@link javax.servlet.http.HttpServletRequest#setCharacterEncoding} call.
	 * <p>Whether this encoding will override existing request encodings
	 * (and whether it will be applied as default response encoding as well)
	 * depends on the {@link #setForceEncoding "forceEncoding"} flag.
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Set whether the configured {@link #setEncoding encoding} of this filter
	 * is supposed to override existing request and response encodings.
	 * <p>Default is "false", i.e. do not modify the encoding if
	 * {@link javax.servlet.http.HttpServletRequest#getCharacterEncoding()}
	 * returns a non-null value. Switch this to "true" to enforce the specified
	 * encoding in any case, applying it as default response encoding as well.
	 * <p>Note that the response encoding will only be set on Servlet 2.4+
	 * containers, since Servlet 2.3 did not provide a facility for setting
	 * a default response encoding.
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}


	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestedWith = request.getHeader("x-requested-with");
		String type = request.getContentType();
		if (XMLHTTP_REQUEST.equals(requestedWith)&& (FF_AJAX_CONTENT_TYPE.equals(type)
				||IE_CONTENT_TYPE.equals(type))) {
			request.setCharacterEncoding(AJAX_CHARACTER_ENCODING_UTF8);
			response.setCharacterEncoding(AJAX_CHARACTER_ENCODING_UTF8);
//			request.getParameterMap();
		}else if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encoding);
			if (this.forceEncoding) {
				response.setCharacterEncoding(this.encoding);
			}
		}
		filterChain.doFilter(request, response);
	}
	

//	public void doFilter(ServletRequest servletRequest,
//			ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		String requestedWith = request.getHeader("x-requested-with");
//		String type = request.getContentType();
//		if (XMLHTTP_REQUEST.equals(requestedWith)&& (FF_AJAX_CONTENT_TYPE.equals(type)
//				||IE_CONTENT_TYPE.equals(type))) {
//			request.setCharacterEncoding(AJAX_CHARACTER_ENCODING_UTF8);
//			response.setCharacterEncoding(AJAX_CHARACTER_ENCODING_UTF8);
////			request.getParameterMap();
//		}
//		filterChain.doFilter(request, response);
//
//	}


}


