package kr.toy.jjsPrj.product.mapper;

import java.util.List;

import kr.toy.jjsPrj.product.vo.ProductVO;

public interface ProductMapper {
	public List<ProductVO> viewAll();
	
	public void insertProdcut(ProductVO vo);
	
	public void updateProduct(ProductVO vo);
	
	public void deleteProduct(ProductVO vo);
}
