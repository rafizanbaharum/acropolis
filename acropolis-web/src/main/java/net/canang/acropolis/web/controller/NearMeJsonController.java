package net.canang.acropolis.web.controller;

import net.canang.acropolis.biz.BizFinder;
import net.canang.acropolis.core.model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * /nearme_json/radius/10.0/lat/1.5333/lon/103.388
 *
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/nearme_json")
public class NearMeJsonController {

    private Logger log = LoggerFactory.getLogger(NearMeController.class);

    @Autowired
    private BizFinder finder;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<Issue> getIssues() {
        log.debug("getIssues");
        List<Issue> issues = finder.findAround(10.0D, 1.5333D, 103.388D);
        for (Issue issue : issues) {
            log.debug(issue.toString());
        }
        return issues;
    }
}
