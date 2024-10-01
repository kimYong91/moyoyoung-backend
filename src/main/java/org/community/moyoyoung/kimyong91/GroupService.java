package org.community.moyoyoung.kimyong91;

import org.community.moyoyoung.dto.GroupDTO;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Long register(GroupDTO groupDTO);

    GroupDTO getOne(Long id);

    void modify(GroupDTO groupDTO);

    void remove(Long id);


}
