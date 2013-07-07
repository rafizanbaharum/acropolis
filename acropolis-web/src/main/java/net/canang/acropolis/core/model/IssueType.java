package net.canang.acropolis.core.model;

/**
 * @author rafizan.baharum
 * @since 7/7/13
 */
public enum IssueType {

    GENERAL(0), // 0
    CRIME(1),       // 1
    PETTY_CRIME(2),   // 2
    TOWNSHIP(3),         // 3
    WELFARE(4);               // 4

    private int code;

    IssueType(int code) {
        this.code = code;
    }

    @org.codehaus.jackson.annotate.JsonValue
    public int code() {
        return code;
    }

    @org.codehaus.jackson.annotate.JsonCreator
    public static IssueType fromValue(int typeCode) {
        return IssueType.values()[typeCode];
    }
}
