package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Vacxin;
import model.bo.VacxinBO;

@WebServlet("/")

public class VacxinController extends HttpServlet {
	private VacxinBO bo;

    private static final long serialVersionUID = 1L;
    public void init() {
    	bo = new VacxinBO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getServletPath();
        switch (action) {
            case "/add":
                showAddForm(request, response);
                break;
                
            case "/insert":
                addVacxin(request, response);
                break;
            case "/muivacxin":
            	muivacxin(request, response);
                break;
          
            case "/edit":
                showEditForm(request, response);
                break;

            case "/update":
                updateVacxin(request, response);
                break;

            case "/delete":
                deleteVacxin(request, response);
                break;
            case "/Thongketien":
            	Thongketien(request, response);
                break;
            default:
                vacxin(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void vacxin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Vacxin> vacxins = bo.getAllVacxin();
    	request.setAttribute("vacxins", vacxins);
    	request.getRequestDispatcher("vacxin.jsp").forward(request, response);
    }
    private void muivacxin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Vacxin> vacxins = bo.getAllVacxin();
    	request.setAttribute("vacxins", vacxins);
    	request.getRequestDispatcher("Muivacxin.jsp").forward(request, response);
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("addvacxin.jsp").forward(request, response);
    }
    
    private void addVacxin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String mavacxin = request.getParameter("mavacxin");
    	String tenvacxin = request.getParameter("tenvacxin");
    	int somui = Integer.parseInt(request.getParameter("somui"));
    	String mota = request.getParameter("mota");
    	int giavacxin = Integer.parseInt(request.getParameter("giavacxin"));
    	String tenhangsx = request.getParameter("tenhangsx");
    	
    	Vacxin v = new Vacxin(mavacxin,tenvacxin,somui,mota,giavacxin,tenhangsx);
    	bo.addVacxin(v);
    	response.sendRedirect("list");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
    	Vacxin v = bo.getVacxinByID(id);
    	request.setAttribute("vacxin", v);
    	request.getRequestDispatcher("editvacxin.jsp").forward(request, response);
    }
    
    private void updateVacxin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String mavacxin = request.getParameter("mavacxin");
    	String tenvacxin = request.getParameter("tenvacxin");
    	int somui = Integer.parseInt(request.getParameter("somui"));
    	String mota = request.getParameter("mota");
    	int giavacxin = Integer.parseInt(request.getParameter("giavacxin"));
    	String tenhangsx = request.getParameter("tenhangsx");
    	
    	Vacxin v = new Vacxin(mavacxin,tenvacxin,somui,mota,giavacxin,tenhangsx);
    	bo.updateVacxin(v);
    	response.sendRedirect("list");
    }
    
    private void deleteVacxin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
    	bo.deleteVacxin(id);
    	response.sendRedirect("list");
    }
    
    private void Thongketien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Vacxin> vacxins = bo.getAllVacxin();
    	request.setAttribute("vacxins", vacxins);
    	request.getRequestDispatcher("Thongketien.jsp").forward(request, response);
    }
}

