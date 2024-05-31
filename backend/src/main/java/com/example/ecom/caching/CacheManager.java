package com.example.ecom.caching;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheManager {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();
}
