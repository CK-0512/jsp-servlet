package shop.member;

public class MemberDTO {
	private int idx; //고유번호
	private String username; //회원 이름
	private String userid; //회원 아이디
	private String pass; //비밀번호
	private String zipcode; //우편번호
	private String address; //주소
	private String de_address; //상세주소
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDe_address() {
		return de_address;
	}
	public void setDe_address(String de_address) {
		this.de_address = de_address;
	}
	
}
