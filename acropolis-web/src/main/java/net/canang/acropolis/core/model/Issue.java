package net.canang.acropolis.core.model;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
public interface Issue {

    public static String FIELD = "location";
    public static String STATUS = "status";

    Long getId();

    void setId(Long id);

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);

    String getReporter();

    void setReporter(String reporter);

    Date getReportedDate();

    void setReportedDate(Date reportedDate);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    IssueStatus getStatus();

    void setStatus(IssueStatus status);
}
