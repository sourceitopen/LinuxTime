<%@ page import="grails.plugins.springsecurity.SpringSecurityService" %>
<% def springSecurityService %>
<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'linuxtime.css')}" type="text/css">
		
        <r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a>
		<div class="logout">
		 Welcome! ${user.username}
		<g:link controller="logout"> <img src="${createLinkTo(dir:'images', file: 'logout-tux.png')}" alt="Logout"/></g:link> 
		</div>
		</div>
		<div>
		<div id="txtArea">
		Complete Your Profile Here</br>
		<g:form method="post" id="editProfile" controller="tuxers" action="saveProfile">
		First Name<input type="text" name="firstName" value="${profile.firstName}"></br>
		Last Name<input type="text" name="lastName" value="${profile.lastName}"></br>
		Bio<g:textArea id="shortBio" name="bio" value="${profile.bio}"/></br>
		Email&nbsp;<input type="text" name="email" value="${profile.email}"></br>
		Country<input type="text" name="country" value="${profile.country}"></br>
		TimeZone<input type="text" name="timezone" value="${profile.timezone}"></br>
		Homepage<input type="text" name="homepage" value="${profile.homepage}"></br>
		<g:submitButton name="post" value="Save Profile"/>
		</g:form>
		</div>
		</div>
		<div class="footer" role="contentinfo">Welcome To LinuxTime</div>
		
        <r:layoutResources />
        <script src="${resource(dir:'js',file:'jquery.validation.js') }"></script>
	</body>
</html>

		