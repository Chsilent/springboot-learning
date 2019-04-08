package com.walker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 全局异常处理
 *
 * @author Walker
 * @date 2019/2/11 下午2:03
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        //return createModelAndView(request, "500", HttpStatus.INTERNAL_SERVER_ERROR, e);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("{error:1}");
        writer.flush();
        return null;
    }*/

    /**
     * 创建对应的异常处理视图对象
     *
     * @param request
     * @param viewName
     * @param status
     * @param e
     * @return
     */
    /*private ModelAndView createModelAndView(HttpServletRequest request, String viewName, HttpStatus status, Exception e) {
        ModelAndView mav = new ModelAndView();
        if (e != null) {
            mav.addObject("error", e);
        }
        mav.addObject("url", request.getRequestURI());
        mav.addObject("method", request.getMethod());
        if (status != null) {
            mav.setStatus(status);
        }
        mav.setViewName(viewName);
        return mav;
    }*/
    @ExceptionHandler(NullOrEmptyException.class)
    @ResponseBody
    public ErrorMessage<String> nullOrEmptyExceptionHandler(HttpServletRequest request, NullOrEmptyException exception) throws Exception {
        return handlerErrorInfo(request, exception.getMessage(), exception);
    }

    /**
     * 处理异常信息
     *
     * @param request
     * @param message
     * @param exception
     * @return
     */
    public ErrorMessage<String> handlerErrorInfo(HttpServletRequest request, String message, Exception exception) {
        ErrorMessage<String> errorMessage = new ErrorMessage<>();
        errorMessage.setMessage(message);
        errorMessage.setCode(ErrorMessage.ERROR);
        //errorMessage.setData(message);
        //errorMessage.setUrl(request.getRequestURI());
        return errorMessage;
    }
}
