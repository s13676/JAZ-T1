package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/table/")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (request.getParameter("kwota") == null || request.getParameter("kwota") == "" || 
				request.getParameter("raty") == null || request.getParameter("raty") == "" ||
				request.getParameter("oprocentowanie") == null || request.getParameter("oprocentowanie") == "" || 
				request.getParameter("rodzaj") == null || request.getParameter("rodzaj") == "" ||
				request.getParameter("opstala") == null || request.getParameter("opstala") == "") {
			response.sendRedirect("/");
		}
		
		Float kwotaKredytu = Float.parseFloat(request.getParameter("kwota"));
		Float ratyKredytu = Float.parseFloat(request.getParameter("raty"));
		Float oplataStala = Float.parseFloat(request.getParameter("opstala"));
		Float oprocentowanieKredytu = Float.parseFloat(request.getParameter("oprocentowanie")) / 100;
		String rodzajRat = request.getParameter("rodzaj");
		
		response.setContentType("text/html");
		response.getWriter().println("<table><tr><th>Nr raty</th><th>Kwota Kapitalu</th><th>Kwota odsetek</th>"
				+ "</th><th>Oplata Stala</th><th>Calkowita kwota raty</th></tr>");
		
		if (rodzajRat.equals("m")) {
			Float splacone = (float) 0;
			for (Integer lp=1;lp<=ratyKredytu;lp++) {
				Float czescKapitalowa = kwotaKredytu / ratyKredytu;
				Float czescOdsetkowa = (kwotaKredytu - splacone) * oprocentowanieKredytu / ratyKredytu;
				Float rata = czescKapitalowa + czescOdsetkowa + oplataStala;
				splacone += czescKapitalowa;
				
				response.getWriter().println("<tr>");
				response.getWriter().println("<td>"+lp+"</td>"+"<td>"+czescKapitalowa+"</td>"+
						"<td>"+czescOdsetkowa+"</td>"+"<td>"+oplataStala+"</td>"+"<td>"+rata+"</td>");
				response.getWriter().println("</tr>");
			}
		} else {
			Float q = 1 + (oprocentowanieKredytu / ratyKredytu);
			Double rata = kwotaKredytu * Math.pow(q, ratyKredytu) * (q - 1)/(Math.pow(q, ratyKredytu)-1);
			rata += oplataStala;
			for (Integer lp=1;lp<=ratyKredytu;lp++) {
				Float czescKapitalowa = kwotaKredytu / ratyKredytu;
				Float czescOdsetkowa = (float) (rata - czescKapitalowa);
				
				response.getWriter().println("<tr>");
				response.getWriter().println("<td>"+lp+"</td>"+"<td>"+czescKapitalowa+"</td>"+
						"<td>"+czescOdsetkowa+"</td>"+"<td>"+oplataStala+"</td>"+"<td>"+rata+"</td>");
				response.getWriter().println("</tr>");
			}
		}
		response.getWriter().println("</table>");
	}

}
