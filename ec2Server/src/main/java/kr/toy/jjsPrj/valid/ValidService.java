package kr.toy.jjsPrj.valid;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.toy.jjsPrj.valid.mapper.ValidMapper;
import kr.toy.jjsPrj.valid.vo.ValidVO;

@Service
public class ValidService {

	@Resource
	private ValidMapper validMapper;
	
	public boolean checkKey(ValidVO vo){
		return validMapper.checkKey(vo);
	}
}
