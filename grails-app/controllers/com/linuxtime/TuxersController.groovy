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
		def fileName
		def user = User.findById(params.userId)
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
			try{
				def mhsr = request.getFile('photo')
				fileName = mhsr.name
				log.debug "fileName is==="+mhsr.name
				if(!mhsr?.empty && mhsr.size < 1024*2000){
					mhsr.transferTo(
						new File("/home/neuron/sampleuploading/fileName_${user.username}.jpg")
						)
				}
				}catch(FileNotFoundException fnfe){
				fnfe.printStackTrace()
				render "path is not available"
				}
				profileValues.profilePicName = fileName+"_"+user.username+".jpg"
			}
			//render "profile will be saved"
		redirect controller:'post',action:'showPost',params:[user:springSecurityService.currentUser.username]
		}
		}
	def upload = {
		log.debug "reached upload action in image controller"
		log.debug "user received is==="+params.currentUserId
		def mhsr = request.getFile('photo')
		if(!mhsr?.empty && mhsr.size < 1024*2000){
			mhsr.transferTo(
				new File("/home/neuron/sampleuploading/abcd.jpg")
				)
		}
		redirect(controller:'tuxers',action:'tuxProfile')
	}
	@Secured(['ROLE_ADMIN'])
	def tuxtime = {
		def role = Role.findAllWhere(authority:'ROLE_USER')
		log.debug "users with role user are"+role
		def userRole = UserRole.findAllWhere(role:role)
		log.debug "users with userrole are"+userRole
		
	}
}
