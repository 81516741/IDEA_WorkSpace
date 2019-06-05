package com.example.bikeproject.Controller;

import com.example.bikeproject.pojo.BikeModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BikeController {
    @GetMapping("/dd")
    @ResponseBody
    public String getName(HttpServletRequest request){
        return "dddd";
    }

    //接受json类型
    @PostMapping("/da")
    @ResponseBody
    public String getInfo(@RequestBody String json) {
        System.out.println(json);
        return json;
    }

    //接受单个属性
    @GetMapping("/hao")
    @ResponseBody
    public String getName1(String bid){
        return bid;
    }

    //接受多个属性并转模型
    @GetMapping("/hao1")
    @ResponseBody
    public BikeModel getName2(BikeModel bike){
        System.out.println(bike);
        return bike;
    }


}
