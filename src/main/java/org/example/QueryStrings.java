package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryStrings {
    private final List<QueryString> queryStrings;

    public QueryStrings(String queryStringLine) {
        String[] token = queryStringLine.split("&");
        this.queryStrings = Arrays.stream(token)
                .map(this::changeStringToQueryString)
                .collect(Collectors.toList());
    }

    public QueryStrings(QueryString... queryStrings) {
        this.queryStrings = Arrays.asList(queryStrings);
    }

    private QueryString changeStringToQueryString(String queryString) {
        String[] values = queryString.split("=");
        if (values.length != 2) {
            throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
        }
        return new QueryString(values[0], values[1]);
    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 queryString 값이 아닙니다."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryStrings that = (QueryStrings) o;
        return Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryStrings);
    }
}
