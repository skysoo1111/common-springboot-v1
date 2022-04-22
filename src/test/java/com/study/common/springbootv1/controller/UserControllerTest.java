package com.study.common.springbootv1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.common.springbootv1.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("mysql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("JPA TEST - join member")
    @Order(1)
    @Test
    public void signup() throws Exception {
        //given
        String uid = "skysoo1111@test.com";
        String password = "1234";
        String name = "테스트";
        String number = "";

        User user = User.builder()
                .uid(uid)
                .name(name)
                .password(password)
                .build();

        String memberJsonStr = objectMapper.writeValueAsString(user);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/signup/")
                .content(memberJsonStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uid").value(uid))
                .andExpect(jsonPath("$.data.name").value(name));

        // restDocs
        resultActions.andDo(document("member",
                requestParameters (
                        parameterWithName("data.uid").description("이메일").optional(),
                        parameterWithName("data.name").description("이름").optional()
                ),
                responseFields(
                        fieldWithPath("code").description("상태 코드"),
                        fieldWithPath("message").description("상태 설명"),
                        fieldWithPath("data.id").description("seq no"),
                        fieldWithPath("data.uid").description("이메일"),
                        fieldWithPath("data.name").description("이름"),
                        fieldWithPath("data.password").description("비밀번호"),
                        fieldWithPath("data.number").description("전화번호")
                )
                )
        );
    }

    @DisplayName("Mybatis TEST - get member")
    @Order(2)
    @Test
    public void getMemberByUid() throws Exception {

        //given
        String email = "skysoo1111@test.com";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/mybatis/member/" + email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.uid").value(email))
                .andExpect(jsonPath("$.data.name").value("테스트"));

        // restDocs
        resultActions.andDo(document("member",
                requestParameters (
                        parameterWithName("code").description("상태 코드").optional(),
                        parameterWithName("message").description("상태 설명").optional(),
                        parameterWithName("data.id").description("seq no").optional(),
                        parameterWithName("data.uid").description("이메일").optional(),
                        parameterWithName("data.password").description("비밀번호").optional(),
                        parameterWithName("data.name").description("이름").optional(),
                        parameterWithName("data.number").description("전화번호").optional()
                ),
                responseFields(
                        fieldWithPath("code").description("상태 코드"),
                        fieldWithPath("message").description("상태 설명"),
                        fieldWithPath("data.id").description("seq no"),
                        fieldWithPath("data.uid").description("이메일"),
                        fieldWithPath("data.password").description("비밀번호"),
                        fieldWithPath("data.name").description("이름"),
                        fieldWithPath("data.number").description("전화번호")
                )
                )
        );
    }

    @DisplayName("JPA TEST - get member")
    @Order(3)
    @Test
    public void getMemberEntityByUid() throws Exception {

        //given
        String email = "skysoo1111@test.com";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/jpa/member/" + email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.uid").value(email))
                .andExpect(jsonPath("$.data.name").value("테스트"));

        // restDocs
        resultActions.andDo(document("member",
                requestParameters (
                        parameterWithName("code").description("상태 코드").optional(),
                        parameterWithName("message").description("상태 설명").optional(),
                        parameterWithName("data.id").description("seq no").optional(),
                        parameterWithName("data.uid").description("이메일").optional(),
                        parameterWithName("data.password").description("비밀번호").optional(),
                        parameterWithName("data.name").description("이름").optional(),
                        parameterWithName("data.number").description("전화번호").optional()
                ),
                responseFields(
                        fieldWithPath("code").description("상태 코드"),
                        fieldWithPath("message").description("상태 설명"),
                        fieldWithPath("data.id").description("seq no"),
                        fieldWithPath("data.uid").description("이메일"),
                        fieldWithPath("data.password").description("비밀번호"),
                        fieldWithPath("data.name").description("이름"),
                        fieldWithPath("data.number").description("전화번호")
                )
                )
        );
    }

    @DisplayName("JPA TEST - delete member")
    @Order(4)
    @Test
    public void deleteMember() throws Exception {
        //given
        Long id = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/v1/delete/"+id)
                .accept(MediaType.APPLICATION_JSON));
    }

}
