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
		def profile=user.profile
		log.debug "profile is=="+profile
		[user:user,profile:profile]
	}
	def saveProfile = {
		log.debug "params received are===="+params
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
						new File("/home/neuron/LinuxTime/ProfileImages/${fileName}_${user.username}.jpg")
						)
				}
				}catch(FileNotFoundException fnfe){
				fnfe.printStackTrace()
				render "path is not available"
				}
				profileValues.profilePicName = fileName+"_"+user.username+".jpg"
			}
			//render "profile will be saved"
			def imageList1 = []
			new File("/home/neuron/LinuxTime/ProfileImages/").eachFileMatch(~/.*?\.jpg/) { imageList1 << it }
			log.debug "image list is====="+imageList1
		redirect (controller:'post',action:'showPost',params:[user:springSecurityService.currentUser.username],imageList:imageList1)
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
	def displayProfilePic = {
		def user = springSecurityService.currentUser
		log.debug "current user is====="+user
		String profileImagePath = "/home/neuron/LinuxTime/ProfileImages/"
		String image = user.profile.profilePicName
		File imageFile =new File(profileImagePath+image);
		BufferedImage originalImage=ImageIO.read(imageFile);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();
		response.setHeader('Content-length', imageInByte.length.toString())
		response.contentType = 'image/jpg' // or the appropriate image content type
		response.outputStream << imageInByte
		response.outputStream.flush()
	}
	@Secured(['ROLE_ADMIN'])
	def tuxtime = {
		def role = Role.findAllWhere(authority:'ROLE_USER')
		log.debug "users with role user are"+role
		def userRole = UserRole.findAllWhere(role:role)
		log.debug "users with userrole are"+userRole
		
	}
}
