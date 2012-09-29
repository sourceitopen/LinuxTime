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
<g:hasErrors>
<div class="errors">
<g:renderErrors bean="${post}" as="list" />
</div>
<div class="message">
${flash.message }
${hasErrors(bean:post)}
</div>
</g:hasErrors>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a>
		<div class="logout">
		 Welcome! ${user.username} <img src="${createLink(controller: 'tuxers', action:'displayProfilePic')}" height="100" width="70"/>
		 <g:link controller="tuxers" action="tuxProfile"> Edit your profile</g:link>
		<g:link controller="logout"> <img src="${createLinkTo(dir:'images', file: 'logout-tux.png')}" alt="Logout"/></g:link> 
		</div>
		</div>
		<div>
		<div id="txtArea">
		Type in the Box below to make your posts.</br>
		<g:form method="post" controller="post" action="addPost">
		<g:textArea id="postContent" name="content" /></br>
		<g:submitButton name="post" value="createPost"/>
		</g:form>
		</div>
		<g:each in="${user.posts}" var="post">
		<div class="postEntry">
		${post.content}
		</div>
		<div class="postDate">
		${post.dateCreated }
		</div>
		</g:each>
		</div>
		<div class="footer" role="contentinfo">Welcome To LinuxTime</div>
		
        <r:layoutResources />
	</body>
</html>

		