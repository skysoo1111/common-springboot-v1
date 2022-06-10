package com.study.common.springbootv1.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
public class ChannelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("CHANNEL ALL")
    @org.junit.jupiter.api.Order(1)
    @Test
    public void channelInfoAll() throws Exception {
        //given

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                                                        .get("/channel/all/")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .accept(MediaType.APPLICATION_JSON))
                                                        .andExpect(status().isOk());

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
            responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태"),
                fieldWithPath("data.[].ch_id").type(JsonFieldType.STRING).description("채널 ID"),
                fieldWithPath("data.[].ch_nm").type(JsonFieldType.STRING).description("채널명"),
                fieldWithPath("data.[].ch_desc").type(JsonFieldType.STRING).description("채널 설명").optional(),
                fieldWithPath("data.[].ins_dt").type(JsonFieldType.STRING).description("등록 시간"),
                fieldWithPath("data.[].upd_dt").type(JsonFieldType.STRING).description("수정 시간")
            )
            )
        );
    }

    @DisplayName("CHANNEL SINGLE")
    @org.junit.jupiter.api.Order(2)
    @Test
    public void channelInfoSingle() throws Exception {
        // given
        String chId ="EA";
        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                                                        .get("/channel/single/{ch_id}",chId)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .accept(MediaType.APPLICATION_JSON))
                                                        .andExpect(status().isOk());

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
            pathParameters(
                parameterWithName("ch_id").description("채널 ID")
            ),
            responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태"),
                fieldWithPath("data.[].ch_id").type(JsonFieldType.STRING).description("채널 ID"),
                fieldWithPath("data.[].ch_nm").type(JsonFieldType.STRING).description("채널명"),
                fieldWithPath("data.[].ch_desc").type(JsonFieldType.STRING).description("채널 설명").optional(),
                fieldWithPath("data.[].ins_dt").type(JsonFieldType.STRING).description("등록 시간"),
                fieldWithPath("data.[].upd_dt").type(JsonFieldType.STRING).description("수정 시간")
            )
            )
        );
    }

    @DisplayName("CHANNEL RECENT")
    @org.junit.jupiter.api.Order(3)
    @Test
    public void channelInfoRecent() throws Exception {
        // given
        String startDt ="20130724135331";
        String endDt = "20130824135331";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                                                        .get("/channel/recent/")
                                                        .param("start_dt",startDt)
                                                        .param("end_dt",endDt)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .accept(MediaType.APPLICATION_JSON))
                                                        .andExpect(status().isOk());

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
            requestFields(
                fieldWithPath("start_dt").type(String.class).description("조회 시작 날짜"),
                fieldWithPath("end_dt").type(String.class).description("조회 종료 날짜")
            ),
            responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태"),
                fieldWithPath("data.[].ch_id").type(JsonFieldType.STRING).description("채널 ID"),
                fieldWithPath("data.[].ch_nm").type(JsonFieldType.STRING).description("채널명"),
                fieldWithPath("data.[].ch_desc").type(JsonFieldType.STRING).description("채널 설명").optional(),
                fieldWithPath("data.[].ins_dt").type(JsonFieldType.STRING).description("등록 시간"),
                fieldWithPath("data.[].upd_dt").type(JsonFieldType.STRING).description("수정 시간")
            )
            )
        );
    }
}
