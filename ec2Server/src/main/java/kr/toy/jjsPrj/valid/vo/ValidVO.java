package kr.toy.jjsPrj.valid.vo;

import org.apache.ibatis.type.Alias;

@Alias("validVO")
public class ValidVO {
	private String validName;
	private String validAppKey;
	
	public String getValidName() {
		return validName;
	}
	public void setValidName(String validName) {
		this.validName = validName;
	}
	public String getValidAppKey() {
		return validAppKey;
	}
	public void setValidAppKey(String validAppKey) {
		this.validAppKey = validAppKey;
	}
	
}
