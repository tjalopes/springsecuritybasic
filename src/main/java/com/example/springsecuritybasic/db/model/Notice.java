package com.example.springsecuritybasic.db.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "notice_detail")
public class Notice {

    @Id
    @GenericGenerator(
            name = "noticeId",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "notice_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noticeId")
    @Column(name = "id")
    private int noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notic_beg_dt")
    private Date noticBegDt;

    @Column(name = "notic_end_dt")
    private Date noticEndDt;

    @Column(name = "create_dt")
    private Date createDt;

    @Column(name = "update_dt")
    private Date updateDt;

    public Notice() {
    }

    public int getNoticeId() {
        return noticeId;
    }

    public String getNoticeSummary() {
        return noticeSummary;
    }

    public String getNoticeDetails() {
        return noticeDetails;
    }

    public Date getNoticBegDt() {
        return noticBegDt;
    }

    public Date getNoticEndDt() {
        return noticEndDt;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public void setNoticeSummary(String noticeSummary) {
        this.noticeSummary = noticeSummary;
    }

    public void setNoticeDetails(String noticeDetails) {
        this.noticeDetails = noticeDetails;
    }

    public void setNoticBegDt(Date noticBegDt) {
        this.noticBegDt = noticBegDt;
    }

    public void setNoticEndDt(Date noticEndDt) {
        this.noticEndDt = noticEndDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeSummary='" + noticeSummary + '\'' +
                ", noticeDetails='" + noticeDetails + '\'' +
                ", noticBegDt=" + noticBegDt +
                ", noticEndDt=" + noticEndDt +
                ", createDt=" + createDt +
                ", updateDt=" + updateDt +
                '}';
    }
}
