package com.linuxtime

class Profile {
	String firstName
	String lastName
	String bio
	String homepage
	String email
	String timezone
	String country
	String profilePicName
	User user
    static constraints = {
		profilePicName(nullable:true)
    }
	
}
