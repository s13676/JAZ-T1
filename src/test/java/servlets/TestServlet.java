package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class TestServlet extends Mockito {
	@Test
	public void ifPArametersAreNull() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Servlet servlet = new Servlet();
		
		when(request.getParameter("kwota")).thenReturn(null);
		when(request.getParameter("raty")).thenReturn(null);
		when(request.getParameter("oprocentowanie")).thenReturn(null);
		when(request.getParameter("rodzaj")).thenReturn(null);
		when(request.getParameter("opstala")).thenReturn(null);
		
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}
}
