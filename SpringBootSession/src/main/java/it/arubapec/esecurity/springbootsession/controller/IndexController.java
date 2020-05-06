package it.arubapec.esecurity.springbootsession.controller;

import it.arubapec.esecurity.springbootsession.bean.ArubaSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@EnableRedisRepositories
public class IndexController {

    private static final String sessionHeader = "customsession";

    @GetMapping("/optional")
    public String optionalFlow(HttpServletRequest request, Model model){
        Optional.ofNullable(request.getSession().getAttribute(sessionHeader))
                .ifPresentOrElse(
                        customsessionObj -> {
                            int customsession = 0;
                            if (customsessionObj instanceof Integer)
                                customsession = Integer.parseInt(String.valueOf(customsessionObj));
                            customsession++;
                            System.out.println("CustomSession: " + customsession);
                            request.getSession().setAttribute(sessionHeader, customsession);
                            model.addAttribute("pageViews", customsession);
                        },
                        () -> {
                            int customsession = 0;
                            System.out.println("CustomSession: " + customsession);
                            request.getSession().setAttribute(sessionHeader, customsession);
                            model.addAttribute("pageViews", customsession);
                        });
        return "index";
    }

    @GetMapping("/annotation")
    public String annotation(HttpServletRequest request, Model model, @SessionAttribute(sessionHeader) int customsession){
        customsession++;
        System.out.println("CustomSession: " + customsession);
        request.getSession().setAttribute(sessionHeader, customsession);
        model.addAttribute("pageViews", customsession);
        return  "index";
    }

    @Autowired
    private ArubaSessionBean arubaSessionBean;

    @GetMapping("/manual")
    public String bean(HttpServletRequest request, Model model){
        if (arubaSessionBean == null)
            throw new RuntimeException("No bean defined");
        int customsession = arubaSessionBean.getCustomSession() + 1;

        System.out.println("CustomSession: " + arubaSessionBean.getCustomSession());
        arubaSessionBean.setCustomSession(customsession);

        request.getSession().setAttribute(sessionHeader, customsession);
        model.addAttribute("pageViews", customsession);
        return  "index";
    }

}
