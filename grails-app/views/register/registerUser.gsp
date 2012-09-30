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
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<script src="http://yui.yahooapis.com/3.7.2/build/yui/yui-min.js"></script>
<script>
	// Create a YUI sandbox on your page.
	YUI().use('node', 'event', function(Y) {
		// The Node and Event modules are loaded and ready to use.
		// Your code goes here!
	});
</script>
</head>
<body>
	<g:hasErrors>
		<div class="errors">
			<g:eachError><p>${it.defaultMessage}</p></g:eachError>s
		</div>
	</g:hasErrors>
	<div id="page-body" role="main">
	Already Have an account! 
		<g:link controller="tuxers">
		Click Here
		</g:link>
	</div>
	<g:form controller="register" action="saveUser"
		style="padding-left:200px">

		<div style="width: 0px">
			<label>UserName:</label><input type="text" name="userName"> <label>Password:</label><input
				type="password" name="password"> <label> Retype
				Password:</label><input type="password" name="repeatPassword"> <label>Country:</label><input
				type="text" name="country"> &nbsp; <input type="submit"
				value="Register"> &nbsp;
			<g:link uri="/">Cancel</g:link>
		</div>
	</g:form>

</body>
</html>
