package com.clothes.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.clothes.entity.Person;
import com.clothes.util.SystemTimeUtil;
import com.mysql.jdbc.Driver;

/** 
 * SpringMVC�е��ļ��ϴ� 
 * @see ��һ��������SpringMVCʹ�õ���commons-fileuploadʵ�֣��ʽ������������Ŀ�� 
 * @see       �����õ�����commons-fileupload-1.2.2.jar��commons-io-2.0.1.jar 
 * @see �ڶ�������####-servlet.xml������MultipartResolver�����������ڴ˼�����ϴ��ļ����������� 
 * @see ����������Controller�ķ��������MultipartFile�������ò������ڽ��ձ���file��������� 
 * @see ���Ĳ�����дǰ̨����ע��enctype="multipart/form-data"�Լ�<input type="file" name="****"/> 
 * @author ���� 
 * @create May 12, 2012 1:26:21 AM 
 */  
@Controller  
@RequestMapping("/user")  
public class PersonController {
	
	private final static Map<String, Person> persons=new HashMap<String, Person>();
	
	public PersonController(){
		
		persons.put("1", new Person("������", "����ƿ", "02200059", "menyouping@yeah.net"));
		persons.put("2", new Person("��Ѱ��", "��̽��", "08866659", "lixunhuan@gulong.cn"));
		persons.put("3", new Person("�ذ�Ұ", "�����", "05577759", "tuobaye@manhuang.cc"));
		persons.put("4", new Person("�����", "������", "03311159", "sunhouzi@xiyouji.zh"));
		
	}
	@RequestMapping("/list") 
	public String list(Model model){
		model.addAttribute("persons",persons);
		return "user/list";
	}
	@RequestMapping(value="/add", method=RequestMethod.GET)  
	public String addPerson(){
		return "user/add";  
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)  
    public String addPerson(Person person, @RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException{  
        //���ֻ���ϴ�һ���ļ�����ֻ��ҪMultipartFile���ͽ����ļ����ɣ�����������ʽָ��@RequestParamע��  
        //������ϴ�����ļ�����ô�����Ҫ��MultipartFile[]�����������ļ������һ�Ҫָ��@RequestParamע��  
        //�����ϴ�����ļ�ʱ��ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles������������myfiles�޷���ȡ�������ϴ����ļ�  
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){  
                System.out.println("�ļ�δ�ϴ�");  
            }else{  
                System.out.println("�ļ�����: " + myfile.getSize());  
                System.out.println("�ļ�����: " + myfile.getContentType());  
                System.out.println("�ļ�����: " + myfile.getName());  
                System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());  
                System.out.println("========================================");  
                //����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
                String realPath = request.getSession().getServletContext().getRealPath("/upload");  
                //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
            }  
        }  
        persons.put(person.getUsername(), person);  
        return "redirect:/user/list.do";  
    }  
	@RequestMapping(value="/addpic",method=RequestMethod.POST)
	public String addPerson(Person person,@RequestParam MultipartFile[] myfiles,HttpServletRequest request,Driver driver) throws IOException{
		int pic_num=1;
		for(MultipartFile myfile:myfiles){
			String systemTime=SystemTimeUtil.getSystemTimePic();
			String username=person.getUsername();
			
			if(myfile.isEmpty()){
				System.out.println("�ļ�δ�ϴ���");
			}else{
				
				String pic_type=myfile.getContentType();
				String file_ture_name=username;
				if(pic_type.equals("image/jpeg")){
	                 file_ture_name =   (file_ture_name+pic_num).concat(".jpg");
	             } else if (pic_type.equals("image/png")){
	                 file_ture_name = (file_ture_name+pic_num).concat(".png");
	             } else if(pic_type.equals("image/bmp")){
	                 file_ture_name =  (file_ture_name+pic_num).concat(".bmp");
	             } else if(pic_type.equals("image/gif")){
	                 file_ture_name = (file_ture_name+pic_num).concat(".gif");
	             } else file_ture_name = (file_ture_name+pic_num).concat(".jpg");
				
				String realPath=request.getSession().getServletContext().getRealPath("/upload");
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath,file_ture_name));
			}
			System.out.println("��ǰʱ�䣺"+systemTime);
			pic_num++;
		}
		persons.put(person.getUsername(), person);  
        return "redirect:/user/list.do"; 
	}

}
