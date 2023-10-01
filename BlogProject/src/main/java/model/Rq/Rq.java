import java.util.List;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;
import model.notice.NoticeDAO;
import model.notice.NoticeDTO;

public class Rq {

    private int loginedMemberId;
    private MemberDTO loginedMember;
    private List<NoticeDTO> memberNotices;
    
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    
    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.session = req.getSession();
        
        int loginedMemberId = 0;
        MemberDTO loginedMember = null;
        int cartSize = 0;
        List<NoticeDTO> memberNotices = null;
        
        if (session.getAttribute("loginedMemberId") != null) {
        	MemberDAO mdao = MemberDAO.getInstance();
        	NoticeDAO ndao = NoticeDAO.getInstance();
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
            loginedMember = mdao.memberSelect(loginedMemberId);
            memberNotices = ndao.getMemberNotices(loginedMemberId);
        }
        
        this.loginedMemberId = loginedMemberId;
        this.loginedMember = loginedMember;
        this.memberNotices = memberNotices;
        
        this.req.setAttribute("rq", this);
    }

    public void login(MemberDTO member) {
        this.session.setAttribute("loginedMemberId", member.getId());
    }

    public void logout() {
        this.session.removeAttribute("loginedMemberId");
    }

    public void init() {
        // 초기화 작업 수행
    }

	public int getLoginedMemberId() {
		return loginedMemberId;
	}

	public void setLoginedMemberId(int loginedMemberId) {
		this.loginedMemberId = loginedMemberId;
	}

	public MemberDTO getLoginedMember() {
		return loginedMember;
	}

	public void setLoginedMember(MemberDTO loginedMember) {
		this.loginedMember = loginedMember;
	}

	public List<NoticeDTO> getMemberNotices() {
		return memberNotices;
	}

	public void setMemberNotices(List<NoticeDTO> memberNotices) {
		this.memberNotices = memberNotices;
	}
    
    
}