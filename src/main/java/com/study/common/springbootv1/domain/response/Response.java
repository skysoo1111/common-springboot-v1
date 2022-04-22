package com.study.common.springbootv1.domain.response;


import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 2044753748756796256L;
    String status;
    String message;

    Response(final String status, final String message) {
        this.status = status;
        this.message = message;
    }

    public static Response.ResponseBuilder builder() {
        return new Response.ResponseBuilder();
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public static class ResponseBuilder {
        private String status;
        private String message;

        ResponseBuilder() {
        }

        public Response.ResponseBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public Response.ResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public Response build() {
            return new Response(this.status, this.message);
        }

        public String toString() {
            return "Response.ResponseBuilder(status=" + this.status + ", message=" + this.message + ")";
        }
    }
}
