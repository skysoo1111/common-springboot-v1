package com.study.common.springbootv1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel implements Serializable {
    private static final long serialVersionUID= 4205470462791002677L;

    @JsonProperty("ch_id")
    private String channelId;
    @JsonProperty("ch_nm")
    private String channelName;
    @JsonProperty("ch_desc")
    private String channelDesc;
    @JsonProperty("ins_dt")
    private String insertDate;
    @JsonProperty("upd_dt")
    private String updateDate;
}
