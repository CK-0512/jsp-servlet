package service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberDeleteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nowpage = Integer.parseInt(request.getParameter("nowpage"));
		String ids = request.getParameter("ids");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			
			int id = Integer.parseInt(idStr);
			
			memberIds.add(id);
			
			MemberDTO member = dao.memberSelect(id);
			
		}

		int row = dao.deleteMembers(memberIds);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row != 0) {
			out.print("<script>");
			out.print("alert('정상적으로 삭제되었습니다.');");
			out.print("window.opener.location.href='/Member?cmd=member_list.do&page="+ nowpage +"';");
			out.print("self.close();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");			
		}


	}

}
