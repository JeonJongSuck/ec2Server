package kr.toy.jjsPrj;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.toy.jjsPrj.product.mapper.ProductMapper;
import kr.toy.jjsPrj.product.vo.ProductVO;
import kr.toy.jjsPrj.valid.ValidService;

@Service
public class ProductService extends ValidService{

	@Resource
	private ProductMapper productMapper;
	
	public List<ProductVO> viewAll(ProductVO vo){
		if(!checkKey(vo)) return null;
		return productMapper.viewAll();
	}
	
	public void insertProdcut(ProductVO vo){
		if(!checkKey(vo)) return;
		productMapper.insertProdcut(vo);
	}
	
	public void updateProduct(ProductVO vo){
		if(!checkKey(vo)) return;
		productMapper.updateProduct(vo);
	}
	
	public void deleteProduct(ProductVO vo){
		if(!checkKey(vo)) return;
		productMapper.deleteProduct(vo);
	}
}
