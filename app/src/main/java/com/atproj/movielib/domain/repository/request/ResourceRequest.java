package com.atproj.movielib.domain.repository.request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourceRequest<Resource> {

    private final static AtomicInteger requestIdGenerator = new AtomicInteger();

    protected final static String QUERY_KEY = "query";

    private final int requestId;

    private String id;

    protected final Map<String, String> params = new HashMap<>();

    public ResourceRequest() {
        requestId = requestIdGenerator.incrementAndGet();
    }

    public ResourceRequest id(String id) {
        this.id = id;
        return this;
    }

    public ResourceRequest query(String query) {
        if (query != null && !query.isEmpty()) {
            params.put(QUERY_KEY, query);
        }
        return this;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRequest<?> that = (ResourceRequest<?>) o;

        return requestId == that.requestId;

    }

    @Override
    public int hashCode() {
        return requestId;
    }
}
