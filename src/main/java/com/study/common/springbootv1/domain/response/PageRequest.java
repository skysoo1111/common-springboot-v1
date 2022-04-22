package com.study.common.springbootv1.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageRequest {
    private Integer page;
    private Integer pageNum;

    public static PageRequest.PageRequestBuilder builder() {
        return new PageRequest.PageRequestBuilder();
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public PageRequest() {
    }

    public PageRequest(final Integer page, final Integer pageNum) {
        this.page = page;
        this.pageNum = pageNum;
    }

    public static class PageRequestBuilder {
        private Integer page;
        private Integer pageNum;

        PageRequestBuilder() {
        }

        public PageRequest.PageRequestBuilder page(final Integer page) {
            this.page = page;
            return this;
        }

        public PageRequest.PageRequestBuilder pageNum(final Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public PageRequest build() {
            return new PageRequest(this.page, this.pageNum);
        }

        public String toString() {
            return "PageRequest.PageRequestBuilder(page=" + this.page + ", pageNum=" + this.pageNum + ")";
        }
    }
}