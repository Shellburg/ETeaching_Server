package com.sdp.eteaching.controller;

import com.sdp.eteaching.service.StudentService;
import com.sdp.eteaching.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.spi.AudioFileReader;
import java.io.*;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/uploadAudio")
public class AudioController {
    @Autowired
    StudentService studentService;


    private boolean saveFile(MultipartFile file, String path) {

        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();

                String savePath = path + file.getOriginalFilename();

                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean saveFile(File file, String path) {

        {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();

                //String savePath = path + file.g();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @SuppressWarnings({"unchecked", "deprecation"})
    @RequestMapping(value = "/filesUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public void myHeadPortraitJson(HttpServletRequest request,
                                   @RequestParam(value = "audio", required = false) List<MultipartFile>
                                           listFile, HttpServletResponse response, ModelMap modelMap) {
        Integer studentID = (Integer) (Integer.parseInt(request.getParameter("s_id")));
        Integer classID = (Integer) (Integer.parseInt(request.getParameter("class_id")));
        System.out.println("studentId:" + request.getParameter("s_id") + "class_id:" + classID);

        if (listFile.size() > 0) {
            for (int i = 0; i < listFile.size(); i++) {
                if (!listFile.get(i).isEmpty()) {
// 文件保存路径

                    // String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/test/" + listFile.get(i).getOriginalFilename();
// 转存文件
                    try {
                        saveFile(listFile.get(i), "E:/Eteaching/audio/");
                        //listFile.get(i).transferTo(new File(filePath));

                        String fileName = listFile.get(i).getOriginalFilename();
                        String path = "E:/Eteaching/audio/" + fileName;
                        studentService.uploadAudioHomework(path, studentID, classID);

                    } catch (IllegalStateException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

//    @RequestMapping(value = "/getfile.do",method ={RequestMethod.GET,RequestMethod.POST})
//    public  File getFile(@RequestParam("homeworkPath") String filename, HttpServletRequest request, HttpServletResponse response, HttpSession session){
//        System.out.println(filename);
//        File file=new File(filename);
//
//        try {
//            FileInputStream fileInputStream=new FileInputStream(filename);
//            response.setContentType("application/force-download");
//            response.addHeader("Content-Disposition","attachment;fileName="+filename);
//            return  file;
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(file.getAbsoluteFile());
//
//        //copy(filename,"E:/Eteaching/hhh.amr");
//
//        return file;
//
//    }


    @RequestMapping(value = "/getfile.do", method = {RequestMethod.GET, RequestMethod.POST})
    public void getFile(@RequestParam("homeworkPath") String filename) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        FileUtil.download(filename, response);
    }


//    @RequestMapping(value = "/getfile.do",method ={RequestMethod.GET,RequestMethod.POST})
//    public String download(HttpServletRequest request, HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
//        if (fileName != null){
//            File file = new File(fileName);
//
//            System.out.println(file.getAbsolutePath());
//            System.out.println(file.getAbsoluteFile());
//            if (file.exists()){
//                response.setContentType("application/force-download");
//                response.addHeader("Content-Disposition","attachment;fileName="+fileName);
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try{
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while(i != -1){
//                        os.write(buffer,0,i);
//                        i = bis.read(buffer);
//                    }
//                    System.out.println("success");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    if (bis != null){
//                        try{
//                            bis.close();
//                        }catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null){
//                        try{
//                            fis.close();
//                        }catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(fileName);
//        return fileName;
//
//    }

static void copy(String srcPathStr, String desPathStr) {
    //获取源文件的名称
    String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1); //目标文件地址
    System.out.println("源文件:" + newFileName);
    //desPathStr = desPathStr + File.separator + newFileName; //源文件地址
    System.out.println("目标文件地址:" + desPathStr);
    try {
        FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
        FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
        byte datas[] = new byte[1024 * 8];//创建搬运工具
        int len = 0;//创建长度
        while ((len = fis.read(datas)) != -1)//循环读取数据
        {
            fos.write(datas, 0, len);
        }
        fis.close();//释放资源
        fis.close();//释放资源
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

