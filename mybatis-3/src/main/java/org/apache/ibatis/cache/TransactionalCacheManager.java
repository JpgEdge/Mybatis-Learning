package org.apache.ibatis.cache;

import org.apache.ibatis.cache.decorators.TransactionalCache;
import org.apache.ibatis.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clinton Begin
 */
public class TransactionalCacheManager {

    private final Map<Cache, TransactionalCache> transactionalCaches = new HashMap<>();

    public void clear(Cache cache) {
        getTransactionalCache(cache).clear();
    }

    private TransactionalCache getTransactionalCache(Cache cache) {
        return MapUtil.computeIfAbsent(transactionalCaches, cache, TransactionalCache::new);
    }

    public Object getObject(Cache cache, CacheKey key) {
        return getTransactionalCache(cache).getObject(key);
    }

    public void putObject(Cache cache, CacheKey key, Object value) {
        getTransactionalCache(cache).putObject(key, value);
    }

    public void commit() {
        for (TransactionalCache txCache : transactionalCaches.values()) {
            txCache.commit();
        }
    }

    public void rollback() {
        for (TransactionalCache txCache : transactionalCaches.values()) {
            txCache.rollback();
        }
    }

}
