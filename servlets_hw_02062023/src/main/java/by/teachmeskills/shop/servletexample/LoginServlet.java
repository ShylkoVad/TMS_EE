package by.teachmeskills.shop.servletexample;

import by.teachmeskills.shop.exceptions.RequestParamNullException;
import by.teachmeskills.shop.model.Category;
import by.teachmeskills.shop.model.User;
import by.teachmeskills.shop.utils.CRUDUtils;
import by.teachmeskills.shop.utils.HttpRequestParamValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (((User) req.getSession().getAttribute("user")).getEmail().equals("empty")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            HttpRequestParamValidator.validateParamNotNull(email);
            HttpRequestParamValidator.validateParamNotNull(password);
        } catch (RequestParamNullException e) {
            System.out.println(e.getMessage());
        }

        User user = CRUDUtils.getUser(email, password);
        List<Category> categories = CRUDUtils.getCategories();

        RequestDispatcher rd;
        if (user != null && user.getPassword().equals(password) && user.getEmail().equals(email)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("categories", categories);
            rd = req.getRequestDispatcher("/home.jsp");
        } else {
            rd = req.getRequestDispatcher("/login.jsp");
        }
        rd.forward(req, resp);
    }
}