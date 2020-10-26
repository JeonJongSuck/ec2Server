package kr.toy.jjsPrj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.toy.jjsPrj.mapper.PrjMapper;

@Service
public class PrjService {

	@Autowired
	private PrjMapper prjMapper;
	
	public List<PrjVO> viewAll(){
		return prjMapper.viewAll();
	}
}
