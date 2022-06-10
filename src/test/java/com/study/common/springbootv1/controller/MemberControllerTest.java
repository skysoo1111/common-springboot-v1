package com.study.common.springbootv1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.common.springbootv1.domain.Member;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("JPA TEST - signup member")
    @org.junit.jupiter.api.Order(1)
    @Test
    public void signup() throws Exception {
        //given
        String uid = "skysoo1111@test.com";
        String password = "1234";
        String name = "테스트";
        String number = "";

        Member member = Member.builder()
                              .uid(uid)
                              .name(name)
                              .password(password)
                              .build();

        String memberJsonStr = objectMapper.writeValueAsString(member);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/signup/")
                .content(memberJsonStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uid").value(uid))
                .andExpect(jsonPath("$.data.name").value(name));

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NULL).description("seq"),
                        fieldWithPath("uid").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("number").type(JsonFieldType.STRING).description("전화번호").optional()
                ),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태"),
                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("seq no"),
                        fieldWithPath("data.uid").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호"),
                        fieldWithPath("data.number").type(JsonFieldType.STRING).description("전화번호").optional()
                )
                )
        );
    }

    @DisplayName("Mybatis TEST - get member")
    @org.junit.jupiter.api.Order(2)
    @Test
    public void getMemberByUid() throws Exception {

        //given
        String email = "skysoo1111@test.com";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/mybatis/member/{email}", email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.uid").value(email))
                .andExpect(jsonPath("$.data.name").value("테스트"));
    }

    @DisplayName("JPA TEST - get member")
    @org.junit.jupiter.api.Order(3)
    @Test
    public void getMemberEntityByUid() throws Exception {

        //given
        String email = "skysoo1111@test.com";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/jpa/member/{email}", email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.uid").value(email))
                .andExpect(jsonPath("$.data.name").value("테스트"));
    }

    @DisplayName("JPA TEST - signin member")
    @org.junit.jupiter.api.Order(4)
    @Test
    public void signin() throws Exception {
        //given
        Long id = 0L;
        String uid = "skysoo1111@test.com";
        String password = "1234";
        String name = "테스트";
        String number = "";

        Member member = Member.builder()
                .uid(uid)
                .name(name)
                .password(password)
                .build();

        String memberJsonStr = objectMapper.writeValueAsString(member);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/signin/")
                .content(memberJsonStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uid").value(uid))
                .andExpect(jsonPath("$.data.name").value(name))
                .andExpect(jsonPath("$.data.password").value(password));

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NULL).description("seq"),
                        fieldWithPath("uid").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("number").type(JsonFieldType.STRING).description("전화번호").optional()
                ),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태"),
                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("seq no"),
                        fieldWithPath("data.uid").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호"),
                        fieldWithPath("data.number").type(JsonFieldType.STRING).description("전화번호").optional()
                )
                )
        );
    }

    @DisplayName("JPA TEST - signout member")
    @org.junit.jupiter.api.Order(5)
    @Test
    public void signout() throws Exception {
        //given
        String email = "skysoo1111@test.com";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/v1/signout/{email}",email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andDo(document("{class-name}/{method-name}",
                pathParameters(
                        parameterWithName("email").description("이메일")
                ),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 상태")
                )
        )
        );
    }

}
