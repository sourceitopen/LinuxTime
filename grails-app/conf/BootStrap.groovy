import com.linuxtime.Role
import com.linuxtime.User
import com.linuxtime.UserRole
import com.linuxtime.*
class BootStrap {

    def init = { servletContext ->
		if(UserRole.list().size()==0){
		def adminRole = Role.findByAuthority('ROLE_ADMIN')?: new Role(authority:'ROLE_ADMIN').save(failOnError:true)
		def userRole = Role.findByAuthority('ROLE_USER')?: new Role(authority:'ROLE_USER').save(failOnError:true)
		
		def user1 = User.findByUsername('arvind')?: new User(username:'arvind',password:'arvind',country:'india',enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false).save(failOnError:true)
		if(!user1.authorities.contains(userRole)){
			UserRole.create user1, userRole,true
		}
		def profile_user1 = new Profile(firstName:'',lastName:'',email:'',bio:'',timezone:'',homepage:'',country:'',user:user1)
		profile_user1.save()
		def user2 = User.findByUsername('admin')?: new User(username:'admin',password:'admin',country:'india',enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false).save(failOnError:true)
		def profile_user2 = new Profile(firstName:'',lastName:'',email:'',bio:'',timezone:'',homepage:'',country:'',user:user2)
		profile_user2.save()
		if(!user2.authorities.contains(userRole)){
			UserRole.create user2, userRole,true
		}
		if(!user2.authorities.contains(adminRole)){
			UserRole.create user2, adminRole,true
		}
		assert User.count() == 2
		assert Role.count() == 2
		assert UserRole.count() == 3
    }
    }
    def destroy = {
    }
}
