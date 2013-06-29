package net.canang.acropolis.web.controller;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */

import net.canang.acropolis.biz.BizFinder;
import net.canang.acropolis.core.model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/nearme")
public class NearMeController {

    private Logger log = LoggerFactory.getLogger(NearMeController.class);

    @Autowired
    private BizFinder finder;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        log.debug("searching");
        List<Issue> issues = finder.findAround(10000000.0D, 1.5333D, 103.388D);
        for (Issue issue : issues) {
            log.debug(issue.toString());
        }

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "nearme";
    }
}
