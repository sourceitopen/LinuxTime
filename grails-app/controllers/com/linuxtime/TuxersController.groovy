package com.linuxtime

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
//import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import java.awt.image.BufferedImage
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
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
	def saveUserService
	def tuxProfile = {
		def user = springSecurityService.currentUser
		log.debug "current user is====="+user
		def profile = Profile.findWhere(user:user)
		log.debug "profile is"+profile
		[user:user,profile:profile]
	}
	def saveProfile = {
		log.debug "params received are===="+params
		def fileName
		def user = User.findById(params.userId)
		def profile = Profile.findByUser(user)
		log.debug "profile is"+profile
		if(profile==null){
			render "something went wrong please try again"
		}
		else{
			saveUserService.saveUserProfile(params,user,profile,request)
			//render "profile will be saved"
		redirect (controller:'post',action:'showPost',params:[user:springSecurityService.currentUser.username])
		}
		}
	def displayProfilePic = {
		def user = springSecurityService.currentUser
		log.debug "current user is====="+user
		def profile = Profile.findWhere(user:user)
		log.debug "profile is"+profile
		String profileImagePath = "/home/neuron/LinuxTime/ProfileImages/"
		//String profileImagePath = grailsApplication.grails.profile.images.path
		String image 
		if(profile.profilePicName==null){
			image = "linux-logo-large.jpg"
		}
		else{
			image = profile.profilePicName
		}
		try{
		File imageFile =new File(profileImagePath+image);
		BufferedImage originalImage=ImageIO.read(imageFile);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();
		response.setHeader('Content-length', imageInByte.length.toString())
		response.contentType = 'image/jpg' // or the appropriate image content type
		response.outputStream << imageInByte
		response.outputStream.flush()
		}catch(Exception e){
		e.printStackTrace()
		}		
	}
	@Secured(['ROLE_ADMIN'])
	def tuxtime = {
		def role = Role.findAllWhere(authority:'ROLE_USER')
		log.debug "users with role user are"+role
		def userRole = UserRole.findWhere(role:role)
		log.debug "users with userrole are"+userRole
		
	}
}
