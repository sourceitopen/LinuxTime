package com.linuxtime

class Tag {
	String name
	User user
	static belongsTo = [User,Post]
	static hasMany = [posts:Post]
    static constraints = {
    }
}
