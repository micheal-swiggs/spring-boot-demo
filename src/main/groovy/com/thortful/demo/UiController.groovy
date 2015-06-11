package com.thortful.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* Simple Groovy Demonstration
*/
@Controller
public class UiController
{

    @RequestMapping("/ui/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/ui")
    public String uiHome()
    {
        return "index";
    }

    @RequestMapping("/api/quotes")
    @ResponseBody
    public Map<String,String> quotes(@RequestParam(value="symbol") String symbol)
    {

        def result = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=${symbol}&f=s,p2,l1,k,j,j1")
            .text
            .split(",")
            .collect{ it.replace('"','')}
        return [ symbol: result[0], change: result[2], price: result[4],
                 yearHigh: result[6], yearLow: result[8], marketCap: result[10] ]
    }
}
