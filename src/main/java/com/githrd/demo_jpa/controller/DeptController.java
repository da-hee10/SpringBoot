package com.githrd.demo_jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_jpa.entity.Dept;
import com.githrd.demo_jpa.repository.DeptRepository;

// @RestController = @Controller + @ResponseBody
@RestController
public class DeptController {

    @Autowired
    DeptRepository deptRepository;

    @GetMapping("/depts")
    public List<Dept> list(){

        List<Dept> list = deptRepository.findAll();

        return list;
    }

    @GetMapping("/dept/{deptno}")
    public Dept selectOne(@PathVariable int deptno){

        Optional<Dept> dept_op = deptRepository.findByDeptno(deptno);

        if(dept_op.isPresent()){   // 데이터를 가져왔나?
            Dept dept = dept_op.get();
            return dept;
        }

        return null;
    }

    // 추가
    @PostMapping("/dept")
    public Map<String, Boolean> insert(@RequestBody Dept dept){
        // insert
        Dept resDept = deptRepository.save(dept);

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("result", resDept != null);

        return map;
    }

    // 삭제
    //@RequestMapping(value = "/depte/{deptno}", method = RequestMethod.DELETE)
    @DeleteMapping("/dept/{deptno}")
    public Map<String, Boolean> delete(@PathVariable int deptno){

        deptRepository.deleteById(deptno);

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("result", true);

        return map;
    }

    // 수정 : method = PUT or PATCH uri="/dept" data={"deptno":10, "dname" : "", "loc" : ""}
    @PutMapping("/dept")
    public Map<String, Boolean> update(@RequestBody Dept dept){
        
        // 수정 원본 데이터 읽어오기
        Optional<Dept> oriDept_Optional = deptRepository.findByDeptno(dept.getDeptno());
        Dept oriDept = null;
        if(oriDept_Optional.isPresent()){
            oriDept = oriDept_Optional.get();

            // 수정된 데이터로 저장
            oriDept = deptRepository.save(dept);
        }

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("result", oriDept != null);

        return map;
    }
}
