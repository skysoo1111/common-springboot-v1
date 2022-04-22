package com.study.common.springbootv1.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
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

//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ActiveProfiles("mysql")
@ExtendWith({RestDocumentationExtension.class})
@AutoConfigureRestDocs
@WebMvcTest({HealthCheckController.class})
class HealthCheckControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Health_테스트() throws Exception {
        // given
        String message = "Success";

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/healthcheck/_check/" + message)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000"))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.status").value("OK"));

        // restDocs
        resultActions.andDo(document("test",
                requestParameters (
                        parameterWithName("code").description("상태 코드").optional(),
                        parameterWithName("message").description("상태 설명").optional(),
                        parameterWithName("data.status").description("상태").optional()
                ),
                responseFields(
                        fieldWithPath("code").description("상태 코드"),
                        fieldWithPath("message").description("상태 설명"),
                        fieldWithPath("data.status").description("상태")
                )
            )
        );
    }
}