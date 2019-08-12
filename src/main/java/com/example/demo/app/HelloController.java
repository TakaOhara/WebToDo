package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    
    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("album");
        mav.addObject("msg", "input your name :");    // �\�����b�Z�[�W
        return mav;
    }
 
    @RequestMapping(value="/", method= RequestMethod.POST)
    public ModelAndView send(@RequestParam("name")String name,
                             ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "Hello " + name + " !");    // �\�����b�Z�[�W
        mav.addObject("value", name);                    // ���̓e�L�X�g�ɓ��͒l��\��
        return mav;
    }
}