<!doctype html>
<html>
<head>
<meta name="layout" content="main" />
<title>Welcome to LinuxTime</title>
<style type="text/css">
label {
	float: right;
	width: 100px;
}
</style>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'linuxtime.css')}" type="text/css">
	<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
	<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>
<body>
<div class="message">
${flash.message }
${hasErrors(bean:user)}
</div>

	<div id="page-body" role="main">
		<g:link controller="tuxers">
			<img src="${createLinkTo(dir:'images', file: 'main_login.png')}" />
		</g:link>
	</div>
	<g:form controller="register" action="saveUser"
		style="padding-left:200px">

		<div style="width: 0px">
			<label>UserName:</label><input type="text" name="userName"> <label>Password:</label>
			<input	type="password" name="password"> <label>
			Retype Password:</label><input type="password" name="repeatPassword"> <label>Country:</label><input
				type="text" name="country"> &nbsp;<input type="submit"
				value="Register"> &nbsp;
			<g:link uri="/">Cancel</g:link>
		</div>
	</g:form>

</body>
</html>
