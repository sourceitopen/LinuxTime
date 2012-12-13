package com.linuxtime
class SaveUserService{
	def saveUser(def receivedParams){

		def user = new User(username:receivedParams.userName,password:receivedParams.password,repeatPassword:receivedParams.repeatPassword,country:receivedParams.country,enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false)
		if(user.validate()) {
			user.save(flush:true)
			def role = new UserRole(user:user,role:Role.findWhere(authority:'ROLE_USER'))
			role.save()
			def profile = new Profile(firstName:'',lastName:'',email:'',bio:'',timezone:'',homepage:'',country:'',user:user)
			profile.save()
			if(profile.hasErrors()){
				profile.errors.each{ println "profile has error"+it }
			}
			log.debug "profile is"+profile
			return true
		}
		else{
			return false
		}
		if(user.hasErrors()){
			user.errors.each { println "errors saving user===="+it }
		}
	}
	def saveUserProfile(params,user,profile,request){
		def fileName
		
			profile.firstName = params.firstName
			profile.lastName = params.lastName
			profile.bio = params.bio
			profile.homepage = params.homepage
			profile.country = params.country
			profile.timezone = params.timezone
			profile.email = params.email
			try{
				def mhsr = request.getFile('photo')
				fileName = mhsr.name
				log.debug "fileName is==="+mhsr.name
				if(!mhsr?.empty && mhsr.size < 1024*2000){
					mhsr.transferTo(
							new File("/home/neuron/LinuxTime/ProfileImages/${fileName}_${user.username}.jpg")
							)
				}
			}catch(FileNotFoundException fnfe){
				fnfe.printStackTrace()
				render "path is not available"
			}
			catch(MissingPropertyException mpe){
				mpe.printStackTrace()
				render "path is not available"
			}
			profile.profilePicName = fileName+"_"+user.username+".jpg"
			profile.save()
		}
}
