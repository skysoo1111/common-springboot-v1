package com.study.common.springbootv1.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {
    private static final long serialVersionUID = -5962492673290643136L;
    private String code;
    private String message;
    private Object data;
    private String detailMessage;

    public static CommonResponse.CommonResponseBuilder builder() {
        return new CommonResponse.CommonResponseBuilder();
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public String toString() {
        String var10000 = this.getCode();
        return "CommonResponse(code=" + var10000 + ", message=" + this.getMessage() + ", data=" + this.getData() + ", detailMessage=" + this.getDetailMessage() + ")";
    }

    public CommonResponse() {
    }

    public CommonResponse(final String code, final String message, final Object data, final String detailMessage) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.detailMessage = detailMessage;
    }

    public static class CommonResponseBuilder {
        private String code;
        private String message;
        private Object data;
        private String detailMessage;

        CommonResponseBuilder() {
        }

        public CommonResponse.CommonResponseBuilder code(final String code) {
            this.code = code;
            return this;
        }

        public CommonResponse.CommonResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public CommonResponse.CommonResponseBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public CommonResponse.CommonResponseBuilder detailMessage(final String detailMessage) {
            this.detailMessage = detailMessage;
            return this;
        }

        public CommonResponse build() {
            return new CommonResponse(this.code, this.message, this.data, this.detailMessage);
        }

        public String toString() {
            return "CommonResponse.CommonResponseBuilder(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ", detailMessage=" + this.detailMessage + ")";
        }
    }
}
