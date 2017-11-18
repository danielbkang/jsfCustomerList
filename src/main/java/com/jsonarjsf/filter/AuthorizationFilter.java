package com.jsonarjsf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter")  //, urlPatterns = { "*.xhtml" }
public class AuthorizationFilter implements Filter {
	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
			
			String reqURI = reqt.getRequestURI();
			System.out.println("RequestedURI is: " + reqURI);
			System.out.println("reqURI.indexOf(/dataDiscovery.xhtml) : " + reqURI.indexOf("/dataDiscovery.xhtml"));
			if(ses != null && ses.getAttribute("username") != null)
				System.out.println("session username attribute is : " + ses.getAttribute("username"));

			if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				System.out.println("I am chaining my filter.");
				chain.doFilter(request, response);
			}
			else
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
		} catch (Exception e) {
			System.out.println("Error: at dofilter: " + e.getClass() + " " + e.getMessage());
		}
	}
	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		System.out.println("Entered intop Login Filter");
//		HttpServletRequest req = (HttpServletRequest) request;
//		String path = req.getRequestURI().substring(req.getContextPath().length());
//		System.out.println("path:" + path);
//
//		if (path.contains("/Admin/") || path.contains("/Employee/")) {
//			if (login != null) {
//				if (login.getUsername() != null && !login.getUsername().equals("")) {
//					chain.doFilter(request, response);
//				} else {
//					HttpServletResponse res = (HttpServletResponse) response;
//					res.sendRedirect("/EMS2/faces/Html/Common/Login.xhtml");
//				}
//			} else {
//				HttpServletResponse res = (HttpServletResponse) response;
//				res.sendRedirect("/EMS2/faces/Html/Common/Login.xhtml");
//			}
//
//		} else {
//			chain.doFilter(request, response);
//		}
//	}
	
//	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//	        + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";
//
//	    @Override
//	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
//	        HttpServletRequest request = (HttpServletRequest) req;
//	        HttpServletResponse response = (HttpServletResponse) res;
//	        HttpSession session = request.getSession(false);
//	        
//	        String loginURL = request.getContextPath() + "/login.xhtml";
//
//	        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
//	        boolean loginRequest = request.getRequestURI().equals(loginURL);
//	        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
//	        boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
//
//	        if (loggedIn || loginRequest || resourceRequest) {
//	            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also https://stackoverflow.com/q/4194207/157882
//	                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//	                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//	                response.setDateHeader("Expires", 0); // Proxies.
//	            }
//
//	            chain.doFilter(request, response); // So, just continue request.
//	        }
//	        else if (ajaxRequest) {
//	            response.setContentType("text/xml");
//	            response.setCharacterEncoding("UTF-8");
//	            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
//	        }
//	        else {
//	            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
//	        }
//	    }
	@Override
	public void destroy() {

	}
}