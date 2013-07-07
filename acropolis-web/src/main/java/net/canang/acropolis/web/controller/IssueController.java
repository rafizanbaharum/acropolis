package net.canang.acropolis.web.controller;

import net.canang.acropolis.biz.BizFinder;
import net.canang.acropolis.core.model.Issue;
import net.canang.acropolis.core.model.IssueImpl;
import net.canang.acropolis.core.model.IssueStatus;
import net.canang.acropolis.core.model.IssueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * /nearme_json/issues?lat=1.5333&lon=103.388
 *
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    public static final double RADIUS = 10.0D;
    private Logger log = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/nearme", method = RequestMethod.GET)
    public String nearMe(ModelMap model) {
        return "nearme";
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String statistic(ModelMap model) {
        return "stats";
    }

    @RequestMapping(value = "/findunresolved", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Issue> findUnresolvedIssue(@RequestParam String lat, @RequestParam String lng) {
        log.debug("lat: " + lat + " lon: " + lng);
        List<Issue> issues = finder.findUnresolvedAround(RADIUS, Double.parseDouble(lat), Double.parseDouble(lng));
        log.debug("result: " + issues.size());
        return issues;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addIssue(@RequestParam String title, @RequestParam String type, @RequestParam String description, @RequestParam String lat, @RequestParam String lng) {
        log.debug("type:" + type + " title:" + title + " desc: " + description + " lat: " + lat + " lng: " + lng);
        Issue issue = new IssueImpl();
        issue.setType(IssueType.values()[Integer.valueOf(type)]);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setLatitude(Double.parseDouble(lat));
        issue.setLongitude(Double.parseDouble(lng));
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle(title);
        issue.setDescription(description);
        finder.saveIssue(issue);
        return "success";
    }
}
