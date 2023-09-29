package com.example.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class SpringController {

	@GetMapping("/sender/{a}/{b}")
    @ResponseBody
    public String sender(@PathVariable("a") int a, @PathVariable("b") int b) {
      String result = null;
      try {
        Calculator c1 = new Calculator();
        c1.setA(a);
        c1.setB(b);
        URL url = new URL("http://localhost:8082/receiver/"+ c1.getA() + "/" + c1.getB());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setUseCaches(false);
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        result = br.readLine();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return result;
    }
    
    @GetMapping("/sender2/{a}/{b}")
    @ResponseBody
    public String sender2(@PathVariable("a") int a, @PathVariable("b") int b) {
      String result = null;
      try {
        Calculator c1 = new Calculator();
        c1.setA(a);
        c1.setB(b);
        URL url = new URL("http://localhost:8082/receiver");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type","application/json");
        con.setUseCaches(false);
        con.setDoOutput(true);
        DataOutputStream dw = new DataOutputStream(con.getOutputStream());
        dw.writeBytes(new Gson().toJson(c1));
        dw.flush();
        dw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        result = br.readLine();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return result;
    }
	
}
