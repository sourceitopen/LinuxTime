package com.linuxtime

class RegisterController {
	def registerUser = {
	
}
def saveUser = {
	log.debug "params from registercontroller are="+params
	def user = new User(username:params.userName,password:params.password,repeatPassword:params.repeatPassword,country:params.country,enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false)
	if(user.validate())
	{
	user.save(flush:true)
	def role = new UserRole(user:user,role:Role.findWhere(authority:'ROLE_USER'))
	role.save()
	def profile = new Profile(firstName:'',lastName:'',email:'',bio:'',timezone:'',homepage:'',country:'',user:user)
	profile.save()
	log.debug "profile is"+profile
	redirect controller:'post',action:'showPost'
	}
	else{
	flash.message = "Your Account was not Created. You missed something"
	redirect action:'registerUser'
	}
	
}
}
