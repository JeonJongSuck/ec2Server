package kr.toy.jjsPrj.mapper;

import java.util.List;

import kr.toy.jjsPrj.PrjVO;

public interface PrjMapper {
	public List<PrjVO> viewAll();
	
	public void insertProdcut(PrjVO vo);
	
	public void updateProduct(PrjVO vo);
	
	public void deleteProduct(PrjVO vo);
}
