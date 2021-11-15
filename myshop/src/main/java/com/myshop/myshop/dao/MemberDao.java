package com.myshop.myshop.dao;


import com.myshop.myshop.entity.Member;
import com.myshop.myshop.entity.Todo;
import org.springframework.data.repository.CrudRepository;

// 第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態
public interface MemberDao extends CrudRepository<Member, Integer> {

}