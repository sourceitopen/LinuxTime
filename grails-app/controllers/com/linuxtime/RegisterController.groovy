package com.linuxtime

class RegisterController {
	def registerUser = {
}
def saveUserService
def saveUser = {
	log.debug "params from registercontroller are="+params
	boolean returningUser = saveUserService.saveUser(params)
	log.debug "returning user is==="+returningUser
	if(returningUser==true)
	redirect controller:'post',action:'showPost'
	else{
	flash.message = "somthing went wrong"	
	redirect action:'registerUser'
	}
}
}
