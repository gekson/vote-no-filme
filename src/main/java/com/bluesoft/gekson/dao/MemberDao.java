package com.bluesoft.gekson.dao;

import java.util.List;

import com.bluesoft.gekson.entidade.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
