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
class TuxersController {

    def springSecurityService
	def mailService

	def tuxProfile = {
		def user = springSecurityService.currentUser
		def profile=user.profile
		log.debug "profile is=="+profile
		[user:user,profile:profile]
	}
	def saveProfile = {
		def user = User.findByUsername(springSecurityService.currentUser.username)
		log.debug "user found is=="+user
		log.debug "params are====="+params
		def profile = user.profile
		log.debug "profile is"+profile
		if(profile==null){
			render "something went wrong please try again"
		}
		else{
			for(def profileValues in profile){
			profileValues.firstName = params.firstName
			profileValues.lastName = params.lastName
			profileValues.bio = params.bio
			profileValues.homepage = params.homepage
			profileValues.country = params.country
			profileValues.timezone = params.timezone
			profileValues.email = params.email
			}
			//render "profile will be saved"
		redirect controller:'post',action:'showPost',params:[user:springSecurityService.currentUser.username]
		}
		}
	@Secured(['ROLE_ADMIN'])
	def tuxtime = {
		def role = Role.findAllWhere(authority:'ROLE_USER')
		log.debug "users with role user are"+role
		def userRole = UserRole.findAllWhere(role:role)
		log.debug "users with userrole are"+userRole
		
	}
}
