

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Search() {
        super();

    }


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String password=req.getParameter("pass");
		PrintWriter out=resp.getWriter();
		if(name.equals("Dipak") && password.equals("1234")) {
		//	req.setAttribute("name","Dipak");
			HttpSession session=req.getSession();
			session.setAttribute("name", "Dipak");
			RequestDispatcher rd=req.getRequestDispatcher("/pro.jsp");
			rd.forward(req, resp);
		}else {
			resp.setContentType("text/html");
			out.println("<h2> name and password are wrong <h2> ");
			RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
		}
	}

}
