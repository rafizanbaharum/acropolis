package net.canang.acropolis.core.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.builtin.EnumBridge;
import org.hibernate.search.spatial.Coordinates;
import org.hibernate.search.spatial.SpatialFieldBridge;
import org.hibernate.search.spatial.SpatialFieldBridgeByRange;
import org.hibernate.search.spatial.impl.Point;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
@Indexed
@Entity(name = "Issue")
@Table(name = "ISSUE")
public class IssueImpl implements Issue {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "SEQ_ISSUE")
//    @SequenceGenerator(name = "SEQ_ISSUE", sequenceName = "SEQ_ISSUE", allocationSize = 1)
    private Long id;

    @Column(name = "LAT")
    @Latitude(of = Issue.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double latitude;

    @Column(name = "LON")
    @Longitude(of = Issue.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double longitude;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "KEY")
    private String key;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "TITLE")
    private String title;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REPORTER")
    private String reporter;

    @Column(name = "REPORTED_DATE")
    private Date reportedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ISSUE_STATUS")
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @FieldBridge(impl = EnumBridge.class)
    private IssueStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ISSUE_TYPE")
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @FieldBridge(impl = EnumBridge.class)
    private IssueType type;

    public IssueImpl() {
        setStatus(IssueStatus.UNRESOLVED);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IssueImpl{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Transient
    @Field
    @FieldBridge(impl = SpatialFieldBridgeByRange.class)
    public Coordinates getLocation() {
        if (latitude == null || longitude == null) {
            return null;
        }
        Coordinates point = Point.fromDegrees(latitude, longitude);
        return point;
    }
}
