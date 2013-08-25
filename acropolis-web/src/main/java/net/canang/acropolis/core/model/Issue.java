package net.canang.acropolis.core.model;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
public interface Issue {

    public static String FIELD = "location";
    public static String STATUS = "status";

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getCode();

    void setCode(String key);

    /**
     * @return
     */
    Double getLatitude();

    void setLatitude(Double latitude);

    /**
     * @return
     */
    Double getLongitude();

    void setLongitude(Double longitude);

    /**
     * @return
     */
    String getReporter();

    void setReporter(String reporter);

    /**
     * @return
     */
    Date getReportedDate();

    void setReportedDate(Date reportedDate);

    /**
     * @return
     */
    String getTitle();

    void setTitle(String title);

    /**
     * @return
     */
    String getDescription();

    void setDescription(String description);

    /**
     * @return
     */
    IssueStatus getStatus();

    void setStatus(IssueStatus status);

    /**
     * @return
     */
    IssueType getType();

    void setType(IssueType type);
}
