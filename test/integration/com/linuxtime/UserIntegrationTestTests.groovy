package com.linuxtime

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTestTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
     //   fail "Implement me"
    }
	void testUserSave()
	{
	def user = new User(username:"arvinddas",password:"password123",repeatPassword:"password123",country:"india",enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false)
	assertNotNull user.save()
	def role=Role.findWhere(authority:'ROLE_USER')
		def userrole=new UserRole(user:user,role:role)
		assertNotNull userrole.save()
	}
	void testProfileSave(){
		def user = new User(username:"neuron",password:"neuron",repeatPassword:"neuron",country:"india",enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false)
		assertNotNull user.save()
		def role=Role.findWhere(authority:'ROLE_ADMIN')
			def userrole=new UserRole(user:user,role:role)
			assertNotNull userrole.save()
			def profile = new Profile(firstName:"neuron",lastName:"kakarot",email:"dass.arvind@gmail.com",bio:"I am a s/w developer",timezone:"IST",homepage:"www.google.com",country:"India",user:user)
			assertNotNull profile.save()
	}
}
