package com.ct.android.marvelcomics.model;


import java.util.List;

public class MarvelApiResponse {
    private int code;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MarvelApiResponseData getData() {
        return data;
    }

    public void setData(MarvelApiResponseData data) {
        this.data = data;
    }

    private MarvelApiResponseData data;

    public class MarvelApiResponseData {
        private int offset;
        private int limit;
        private int total;
        private int count;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<MarvelHero> getResults() {
            return results;
        }

        public void setResults(List<MarvelHero> results) {
            this.results = results;
        }

        private List<MarvelHero> results;
    }
}
