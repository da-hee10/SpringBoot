package com.githrd.demo_mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.githrd.demo_mybatis.dao.SawonDao;
import com.githrd.demo_mybatis.vo.SawonVo;

@Controller
public class SawonController {

    @Autowired
    SawonDao sawon_dao;

    @RequestMapping("/sawon/list_condition.do")
    public String condition_list(@RequestParam(name="deptno",defaultValue = "0")        int deptno,
                                 @RequestParam(name="hire_year_10",defaultValue = "0")  int hire_year_10,
                                 @RequestParam(name="sajob",defaultValue = "all")       String sajob,
                                 @RequestParam(name="sasex",defaultValue = "all")       String sasex,
                                 Model model){
		
		//검색할 조건을 전달할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		
		//전체가 아니면
		if(deptno!=0) { 
			map.put("deptno", deptno);
		}
		
		//전체가 아니면
		if(hire_year_10!=0) {
			map.put("hire_year_10", hire_year_10);
		}
		
		//전체가 아니면
		if(!sajob.equals("all")) { 
			map.put("sajob", sajob);
		}
		
		//전체가 아니면
		if(!sasex.equals("all")) {
			map.put("sasex", sasex);
		}
			
		//사원목록 가져오기
		List<SawonVo> list = sawon_dao.selectList(map);
		
		//request binding
		model.addAttribute("list", list);

        return "sawon/sawon_list";
    }

}
