package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    EntityManager em = DBUtil.createEntityManager();

    // 該当のIDのタスクをDBから取得
    Tasks t = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

    em.close();

    // タスク情報とセッションIDをリクエストスコープに設定
    request.setAttribute("task", t);
    request.setAttribute("_token", request.getSession().getId());

    // タスクIDをリクエストスコープに設定
    request.getSession().setAttribute("task_id", t.getId());

    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
    rd.forward(request, response);

    }

}
