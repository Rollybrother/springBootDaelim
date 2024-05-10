package com.mysite.sbb.Daelim;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DaelimService {
	
	private final DaelimRepository daelimRepository;
	
	public List<DaelimVO> findAllFromDbTest() {
        return this.daelimRepository.findAllFromDbTest();
    }
}
