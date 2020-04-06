package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.MemberDTO;
import com.toprate.erp.repositories.MemberRepository;
import com.toprate.erp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<MemberDTO> getListMemberForAutocomplete(SearchDTO dto) {
        return memberRepository.getListMemberForAutoComplete(dto);
    }
}
