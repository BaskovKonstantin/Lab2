package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //СЕЙЧАС ПОСЛЕ ОТПРАВКИ ФОРМИТСЯ СТРАНИЦА ДЛЯ ПРОВЕРКИ РАБОТЫ ДАННОГО СЕРПЛЕТА
        //после того как ты запилишь свой серплет, мы закоментим весь вывод останется только валидация
        // и перенаправление на AreaCheckServlet, которая будет выдавать клиенту оригинальную html-ку
        // с добавлением внизу резов попадания или не попадания
        // резы ввыводиться так: добавляешь внизу строку таблицы в каждой ячейке которой требуемая инфа
        //Вся нужная для обработки инфа передаётся из этого сервплета в твой в самом конце

        String x = req.getParameter("MiceHunterX");
        String y = req.getParameter("MiceHunterY");
        String r = req.getParameter("R1");

        boolean CheckPassed = false;

        //Здесь пример сессии, который может тебе пригодиться, его код начинает здесь

        HttpSession session = req.getSession();

        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }

        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        if (username == null) {
            printWriter.write("Hello, Anonymous" + "<br>");
        } else {
            printWriter.write("Hello, " + username + "<br>");
        }
        printWriter.write("Page was visited " + visitCounter + " times." + "<br>");

        // и заканчивается здесь

        if ( (x != null)){
                if ((Integer.parseInt (x)<=4)&&((Integer.parseInt (y)>=-4))){
                    CheckPassed = true;
                }
        }
        else {
            printWriter.write(" X was not received "  + "<br>");
        }
        if ( (y != null)&& CheckPassed){
                if ((Integer.parseInt (y)<=5)&&((Integer.parseInt (y)>=-3))){
                    CheckPassed = true;
                }
        }
        else {
            CheckPassed = false;
            printWriter.write(" Y was not received or X failed verification "  + "<br>");
        }
        if ( (r != null)&& CheckPassed){
                if ((Integer.parseInt (r)<=6)&&((Integer.parseInt (y)>=2))){
                    CheckPassed = true;
                }
        }
        else {
            printWriter.write(" R was not received or Y failed verification"  + "<br>");
        }
        if (CheckPassed) {
            printWriter.write("Verification Passed !"  + "<br>");
        }
        //Потом идёт ссылка на твой серплет в котором генериться jsp, который выдаётся клиенту
        // getServletContext().getRequestDispatcher("/areaCheckServlet").forward(req, resp)
        // Это ссылка на твой серплет Миша, сейчас она не работает потому что ты его не сделал
        // Ниже идёт передача данных работоспосбность НЕ проверена ввиду отсутствия второго серплета
        // response.sendredirect(MyServlet?X=x);
        // response.sendredirect(MyServlet?Y=y);
        // response.sendredirect(MyServlet?R=r);


        printWriter.write(" X=" + x + " Y=" + y + " R1=" + r + "<br>");

        printWriter.close();
    }
}