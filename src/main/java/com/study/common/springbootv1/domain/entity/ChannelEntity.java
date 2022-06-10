package com.study.common.springbootv1.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "channel")
class ChannelEntity {
    @Id
    @Column(name = "ch_id", length = 2)
    private String channelId;
    @Column(name = "ch_nm", length = 50)
    private String channelName;
    @Column(name = "ch_desc", length = 255)
    private String channelDesc;
    @Column(name = "ins_dt", length = 14)
    private String insertDate;
    @Column(name = "upd_dt", length = 14)
    private String updateDate;

}
