package com.linuxtime

class User {

	transient springSecurityService

	String username
	String password
	String repeatPassword
	String country
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	static hasMany = [posts:Post,tags:Tag,following:User]
	static constraints = {
		username blank: false, unique: true
		password (blank:false,validator:{passwd,user->
			return passwd != user.username
	})
		repeatPassword (nullable:false,validator:{passwd2,passwdTwo->
			return passwd2 == passwdTwo.password
	})
	}

	static mapping = {
		password column: '`password`'
		repeatPassword column:'`repeat_password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
		if (isDirty('repeatPassword')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
		repeatPassword = springSecurityService.encodePassword(repeatPassword)
	}
}
