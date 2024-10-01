package org.community.moyoyoung.kimyong91;

import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    void remove(Long id);

    Long register();

    void modify();

}
