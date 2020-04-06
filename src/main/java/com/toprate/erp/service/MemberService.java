package com.toprate.erp.service;

import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getListMemberForAutocomplete(SearchDTO dto);
}
