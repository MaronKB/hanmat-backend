package com.human.hanmat.repository;

import com.human.hanmat.entity.CommonVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonRepository<T> {
    public int insert(CommonVO<T> commonVo);
    public int update(CommonVO<T> commonVo);
    public int delete(CommonVO<T> commonVo);
    public CommonVO<T> select(CommonVO<T> commonVo);
    public CommonVO<T> selectOne(CommonVO<T> commonVo);
    public List<CommonVO<T>> selectList(CommonVO<T> commonVo);
    public int count(CommonVO<T> commonVo);
}
