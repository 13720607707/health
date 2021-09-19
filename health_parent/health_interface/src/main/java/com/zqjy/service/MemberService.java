package com.zqjy.service;

import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.Member;

import java.util.List;

public interface MemberService {
   public Member findByTelephone(String telephone);

   public void add(Member member);

 public   List<Integer> findMemberCountByMonths(List<String> months);

    public PageResult pageQuery(QueryPageBean queryPageBean);

   public void deleteByid(Integer id);

   public void edit(Member member);

    Member findById(Integer id);
}
