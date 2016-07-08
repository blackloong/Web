package com.fq.util.yzm;

import java.io.IOException;        
import javax.servlet.ServletException;               
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
@Controller
public class ResultServlet {
     //@RequestMapping(value="resultServlet/validateCode",method=RequestMethod.POST)
     public static String doPost(String validateC,String veryCode,HttpServletResponse response)        
             throws ServletException, IOException {        
//         response.setContentType("text/html;charset=utf-8");        
//         String validateC = (String) request.getSession().getAttribute("validateCode");        
//         String veryCode = request.getParameter("c");        
//         PrintWriter out = response.getWriter();        
         if(veryCode==null||"".equals(veryCode)){        
             //out.println("验证码为空");        
//             out.flush();        
//             out.close();     
//             out.println(201);
            return "201";
         }else{        
             if(validateC.equals(veryCode)){        
                // out.println("验证码正确");        
//                 out.flush();        
//                 out.close();     
//                 out.println(200);
                 return "200";
             }else{        
                 //out.println("验证码错误");       
//                 out.flush();        
//                 out.close();     
//                 out.println(202);
                 return "202";
             }        
         }        
            
     }        
}