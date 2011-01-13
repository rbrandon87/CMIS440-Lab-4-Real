<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!--
 Program Name: CMIS440 Lab 4 Address Book Web App
 @author Brandon R Russell
 @Course CMIS440
 Date: Jan 6, 2011

This page collects information about the error and redirects to the error page.
 -->


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ErrorRedirect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="error, redirect">
	<meta http-equiv="description" content="Error redirect page">

  </head>
  
  <body>

 <% // generate a redirect as ICEFaces would do
 response.setContentType("text/xml;charset=UTF-8");
 response.setStatus(200);
 
 // copy error objects to the session if you want to see details on the error page
 /** 
 * Below is simply gathering all of the information on the thrown exception and then
 * redirects to the jspx error page.
 */
 session.setAttribute("_error_message",
    request.getAttribute("javax.servlet.error.message"));
 session.setAttribute("_error_exception_type",
    request.getAttribute("javax.servlet.error.exception_type"));
 session.setAttribute("_error_exception",
    request.getAttribute("javax.servlet.error.exception"));
 session.setAttribute("_error_status_code",
    request.getAttribute("javax.servlet.error.status_code"));
 session.setAttribute("_error_request_uri",
    request.getAttribute("javax.servlet.error.request_uri"));
 session.setAttribute("_error_servlet_name",
    request.getAttribute("javax.servlet.error.servlet_name"));
response.sendRedirect("ErrorPage.iface");    
 %>

   
  </body>
</html>
