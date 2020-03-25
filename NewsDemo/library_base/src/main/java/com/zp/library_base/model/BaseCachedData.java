package com.zp.library_base.model;

import java.io.Serializable;

public class BaseCachedData<T> implements Serializable {
    public long updateTimeInMills;
    public T data;
}
