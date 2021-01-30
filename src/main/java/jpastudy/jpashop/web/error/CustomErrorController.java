package jpastudy.jpashop.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        log.info("httpStatus : "+httpStatus.toString());

        model.addAttribute("code", status.toString());
        model.addAttribute("path",request.getAttribute("javax.servlet.error.request_uri"));
        model.addAttribute("timestamp", LocalDateTime.now());

        Object exceptionObj = request.getAttribute("javax.servlet.error.exception");
        if(exceptionObj != null) {
            Throwable e = ((Exception) exceptionObj).getCause();
            model.addAttribute("exception", e.getClass().getName());
            model.addAttribute("message", e.getMessage());
        }

        /*
        if(status.equals(HttpStatus.NOT_FOUND.value())) {
            return "/error/404";
        } else if(status.equals(500)) {
            return "/error/500";
        }
        */
        return "error/error";
    }

}