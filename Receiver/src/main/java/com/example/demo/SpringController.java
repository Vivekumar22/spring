package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringController {
	Calculator c1 = new Calculator();
	  
	  @GetMapping("/test")
	  @ResponseBody
	  public String fun1() {
	    return "ruuning receiver";
	  }
	  
	  @GetMapping("/receiver/{a}/{b}")
	  @ResponseBody
	  public String receiver(@PathVariable("a") int a, @PathVariable("b") int b) {
	    System.out.println(c1.add(a, b));
	    return String.valueOf(c1.add(a, b));
	  }
	  
	  @PostMapping("/receiver")
	  @ResponseBody
	  public String receiver2(@RequestBody Calculator c1) {
	    System.out.println(c1.add(c1.getA(), c1.getB()));
	    return String.valueOf(c1.add(c1.getA(), c1.getB()));
	  }
}
