package com.linuxtime

class User {

	transient springSecurityService

	String username
	String password
	String country
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	static hasMany = [posts:Post,tags:Tag,following:User]
	Profile profile
	static constraints = {
		username blank: false, unique: true
		password blank: false
		profile(nullable:true)
	}

	static mapping = {
		password column: '`password`'
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
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
