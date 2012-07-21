<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Linuxtime</title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'linuxtime.css')}" type="text/css">
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Welcome to Grails</h1>
			<p>Hi There! We are Happy To See You Here. Join a community to promote which is a tribute to linux
			</p>
			Login Here ;)
			<g:link controller="tuxers"><img src="${createLinkTo(dir:'images', file: 'main_login.png')}" alt="Login"/></g:link>
			Not A Member! Register Here
			<g:link controller="register" action="registerUser"><img src="${createLinkTo(dir:'images', file: 'main_register.png')}" alt="Register"/></g:link>
			</div>
		</div>
	</body>
	</body>
	
</html>
