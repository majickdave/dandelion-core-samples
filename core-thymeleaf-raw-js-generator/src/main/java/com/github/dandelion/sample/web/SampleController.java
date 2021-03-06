package com.github.dandelion.sample.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dandelion.core.asset.generator.js.RawJsContentGenerator;
import com.github.dandelion.core.web.AssetRequestContext;
import com.github.dandelion.sample.generator.CustomContent;
import com.github.dandelion.sample.service.PersonService;

/**
 * <p>
 * Controller for all examples contained in the sample.
 * </p>
 * 
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class SampleController {

   @Autowired
   private PersonService personService;
   
   @RequestMapping(value = "/")
   public String goToIndex(HttpServletRequest request) {

      request.setAttribute("persons", personService.findLimited(10));

      AssetRequestContext
         .get(request)
         .addGenerator("my-generator", new RawJsContentGenerator(new CustomContent(request)));
      
      return "index";
   }
}