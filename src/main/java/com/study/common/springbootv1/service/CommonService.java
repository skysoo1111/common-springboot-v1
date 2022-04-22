package com.study.common.springbootv1.service;

import java.util.List;

public interface CommonService<T, ID> {
    List<T> findAll();
    T findByUid(String uid);
    void saveAll(List<T> entities);
    void save(T entity);
    void deleteInBatch(Iterable<T> entities);
    void deleteById(Iterable<ID> ids);
}
