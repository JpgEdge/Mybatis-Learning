package org.apache.ibatis.cache;

import org.apache.ibatis.reflection.ArrayUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Clinton Begin
 */
public class CacheKey implements Cloneable, Serializable {

    public static final CacheKey NULL_CACHE_KEY = new CacheKey() {

        @Override
        public void updateAll(Object[] objects) {
            throw new CacheException("Not allowed to update a null cache key instance.");
        }

        @Override
        public void update(Object object) {
            throw new CacheException("Not allowed to update a null cache key instance.");
        }
    };

    private static final long serialVersionUID = 1146682552656046210L;

    private static final int DEFAULT_MULTIPLIER = 37;

    private static final int DEFAULT_HASHCODE = 17;

    private final int multiplier;

    private int hashcode;

    private long checksum;

    private int count;

    // 8/21/2017 - Sonarlint flags this as needing to be marked transient. While true if content is not serializable, this
    // is not always true and thus should not be marked transient.
    private List<Object> updateList;

    public CacheKey(Object[] objects) {
        this();
        updateAll(objects);
    }

    public CacheKey() {
        this.hashcode = DEFAULT_HASHCODE;
        this.multiplier = DEFAULT_MULTIPLIER;
        this.count = 0;
        this.updateList = new ArrayList<>();
    }

    public void updateAll(Object[] objects) {
        for (Object o : objects) {
            update(o);
        }
    }

    public void update(Object object) {
        int baseHashCode = object == null ? 1 : ArrayUtil.hashCode(object);

        count++;
        checksum += baseHashCode;
        baseHashCode *= count;

        hashcode = multiplier * hashcode + baseHashCode;

        updateList.add(object);
    }

    public int getUpdateCount() {
        return updateList.size();
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CacheKey)) {
            return false;
        }

        final CacheKey cacheKey = (CacheKey) object;

        if (hashcode != cacheKey.hashcode) {
            return false;
        }
        if (checksum != cacheKey.checksum) {
            return false;
        }
        if (count != cacheKey.count) {
            return false;
        }

        for (int i = 0; i < updateList.size(); i++) {
            Object thisObject = updateList.get(i);
            Object thatObject = cacheKey.updateList.get(i);
            if (!ArrayUtil.equals(thisObject, thatObject)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CacheKey clone() throws CloneNotSupportedException {
        CacheKey clonedCacheKey = (CacheKey) super.clone();
        clonedCacheKey.updateList = new ArrayList<>(updateList);
        return clonedCacheKey;
    }

    @Override
    public String toString() {
        StringJoiner returnValue = new StringJoiner(":");
        returnValue.add(String.valueOf(hashcode));
        returnValue.add(String.valueOf(checksum));
        updateList.stream().map(ArrayUtil::toString).forEach(returnValue::add);
        return returnValue.toString();
    }

}
