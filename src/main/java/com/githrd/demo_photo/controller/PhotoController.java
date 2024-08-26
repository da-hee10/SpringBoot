package com.githrd.demo_photo.controller;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.githrd.demo_photo.dao.PhotoMapper;
import com.githrd.demo_photo.util.MyCommon;
import com.githrd.demo_photo.util.Paging;
import com.githrd.demo_photo.vo.MemberVo;
import com.githrd.demo_photo.vo.PhotoVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/photo/")
public class PhotoController {
    
    @Autowired
    PhotoMapper photo_mapper;
    // ip 구하기 위해서 request 사용
    @Autowired
    HttpServletRequest request;
    // 절대 경로 구하기 위해서 application 사용
    @Autowired
    ServletContext application;
    // Session 전달용 session 사용
    @Autowired
    HttpSession session;
    
    // /photo/list.do?page=2
    @RequestMapping("list.do")
    public String list(@RequestParam(name="page",defaultValue = "1") int nowPage,
            Model model) {
        
        
        // 게시물의 범위 계산(start/end)
        int start = (nowPage-1) * MyCommon.Photo.BLOCK_LIST + 1;
        int end = start + MyCommon.Photo.BLOCK_LIST - 1;
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start",start);
        map.put("end",end);
        
        
        List<PhotoVo> list = photo_mapper.selectPageList(map);
        
        // 전체 게시물수
        int rowTotal = photo_mapper.selectRowTotal();
        
        // pageMenu 만들기
        String pageMenu = Paging.getPaging("list.do",   // pageURL
                                            nowPage,    // 현재페이지
                                            rowTotal,   // 전체페이지
                                            MyCommon.Photo.BLOCK_LIST,  // 한화면에 보여질 게시물 수
                                            MyCommon.Photo.BLOCK_PAGE); // 한화면에 보여질 페이지 수
        
        // 결과적으로 request binding
        model.addAttribute("list",list);
        model.addAttribute("pageMenu",pageMenu);
        
        return "photo/photo_list";
    }
    
    
    @RequestMapping("insert_form.do")
    public String insert_form() {
        
        return "photo/photo_insert_form";
    }
    
    
    //사진등록
    //                          요청 Parameter 이름과 받는 변수명이 동일하면 @RequestParam(name="")의
    //                          name 속성은 생략가능
    @RequestMapping("insert.do")
    public String insert(PhotoVo vo,
            @RequestParam(name="photo") MultipartFile photo,
            RedirectAttributes ra) throws Exception {
        
        // 유저 정보 얻어오기
        MemberVo user = (MemberVo) session.getAttribute("user");
        
        // session timeout
        if(user==null) {
            
            // response.sendRedirect("../member/login_form.do?reason=session_timeout");
            ra.addAttribute("reason","session_timeout");
            return "redirect:../member/login_form.do";
        }
        
        // 파일 업로드 처리
        String absPath = application.getRealPath("/resources/images/");
        String p_filename = "no_file";
        
        if(!photo.isEmpty()) {
            
            // 업로드 파일이름 얻어오기
            p_filename = photo.getOriginalFilename();
            // 저장할 위치 + file 이름
            File f = new File(absPath,p_filename);
            
            if(f.exists()) { // 저장경로에 동일한 파일이 존재하면=>다른이름변경
                // 변경파일명 = 시간_원본파일명
                
                long tm = System.currentTimeMillis();
                p_filename = String.format("%d_%s", tm, p_filename);
                
                f = new File(absPath,p_filename);
            }
            
            // 임시 파일=>내가 지정한 위치로 복사
            photo.transferTo(f);
        }
        
        // 업로드 된 파일이름
        vo.setP_filename(p_filename);
        // IP
        String p_ip = request.getRemoteAddr();
        vo.setP_ip(p_ip);
        String p_content = vo.getP_content().replaceAll("\n", "<br>");
        vo.setP_content(p_content);
        
        // 입력한 로그인 유저 넣는다
        vo.setMem_idx(user.getMem_idx());
        
        vo.setMem_name(user.getMem_name());
        // DB Insert
        int res = photo_mapper.insert(vo);
        return "redirect:list.do";
    }
    
    @RequestMapping(value="photo_one.do")
    @ResponseBody // 현재 반환값을 응답데이터를 이용해라
    public PhotoVo photo_one(int p_idx) {
        
        PhotoVo vo = photo_mapper.selectOne(p_idx);
        
        return vo;
    }
    
    
    @RequestMapping("delete.do")
    public String delete(PhotoVo vo,int p_idx) {
        
        vo = photo_mapper.selectOne(p_idx);
        
        String absPath = request.getServletContext().getRealPath("/resources/images/");
        
        File delFile = new File(absPath, vo.getP_filename());
        delFile.delete();
        
        // DB delete
        int res = photo_mapper.delete(p_idx);
        
        return "redirect:list.do";
    }
    
    @RequestMapping("modify_form.do")
    public String modify_from(int p_idx, PhotoVo vo, Model model) {
        
        vo = photo_mapper.selectOne(p_idx);
        
        String p_content = vo.getP_content().replaceAll("<br>", "\n");
        vo.setP_content(p_content);
        
        model.addAttribute("vo", vo);
        
        return "photo/photo_modify_form";
    }
    
    @RequestMapping("modify.do")
    public String modify(int p_idx, String p_title, String p_content, PhotoVo vo) {
        
        vo.setP_idx(p_idx);
        vo.setP_title(p_title);
        vo.setP_content(p_content);
        
        photo_mapper.update(vo);
        
        return "redirect:list.do";
    }
    
    @RequestMapping(value="photo_upload.do")
    @ResponseBody
    public Map<String, String> upload(PhotoVo vo, MultipartFile photo, RedirectAttributes ra, int p_idx) throws Exception {
        
        String absPath = application.getRealPath("/resources/images/");
        
        String p_filename = "no_file";
        
        if(!photo.isEmpty() ) {
            
            p_filename = photo.getOriginalFilename();
            
            File f = new File(absPath,p_filename);
            
            if(f.exists()) {
                
                long tm = System.currentTimeMillis();
                p_filename = String.format("%d_%s", tm, p_filename);
                
                f = new File(absPath, p_filename);
            }
            
            photo.transferTo(f);
        }
        
        vo = photo_mapper.selectOne(p_idx);
        File delFile = new File(absPath, vo.getP_filename());
        delFile.delete();
        
        vo.setP_filename(p_filename);
        photo_mapper.updateFilename(vo);
        // {"p_filename" : "a.jpg"}
        Map<String, String> map = new HashMap<String, String>();
        map.put("p_filename", p_filename);
        return map;
    }
    
    
    
    
    
    
    
    
    
    
    
}