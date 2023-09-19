package shop.askreply;

public class AskreplyDTO {
	private int idx; //고유번호
	private String pass; //비밀번호
	private String name; //작성자 이름
	private String regdate; //작성일자
	private String subject; //제목
	private String contents; //내용
	private int parent; //최상위 부모 글번호
	private int realparent; //바로 한 수준 위의 글번호
	private int indent; //들여쓰기 기준
	private int depth; //답변글의 넘버를 저장하는 필드
	private int readcnt; //조회수
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getRealparent() {
		return realparent;
	}
	public void setRealparent(int realparent) {
		this.realparent = realparent;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

}
