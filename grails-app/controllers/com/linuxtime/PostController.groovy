package com.linuxtime
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
//import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
@Secured(['ROLE_USER'])
class PostController {
def springSecurityService
	def addPost = {
		log.debug "content received is"+params.content
		
		def user = User.findByUsername(springSecurityService.currentUser.username)
		log.debug "user found is=="+user
		def post= new Post(content:params.content,user:user)
		if(post.validate()){
		post.save()
		}else{
		flash.message = "Your Post was empty or contained wrong content"
		}
		redirect action:'showPost'
	}
	def showPost = {
		log.debug "user is ="+springSecurityService.currentUser
		def user = User.findByUsername(springSecurityService.currentUser.username)
		log.debug "user found is"+user.id
		[user:user]
	}
}
