package com.linuxtime

class Profile {
	String firstName
	String lastName
	String bio
	String homepage
	String email
	String timezone
	String country
	static belongsTo =[user:User]
    static constraints = {
    }
}
