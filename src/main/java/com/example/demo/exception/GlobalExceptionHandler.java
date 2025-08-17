package com.example.demo.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/**
 * アプリケーション全体の例外を処理するハンドラー
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * アクセス拒否例外の処理
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDenied(AccessDeniedException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "このページにアクセスする権限がありません。");
        mav.addObject("requestUrl", request.getRequestURL().toString());
        mav.setViewName("error/403");
        return mav;
    }

    /**
     * MyBatis関連例外の処理
     */
    @ExceptionHandler(org.apache.ibatis.exceptions.PersistenceException.class)
    public ModelAndView handlePersistenceException(org.apache.ibatis.exceptions.PersistenceException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "データベースエラーが発生しました。");
        mav.addObject("details", e.getMessage());
        mav.setViewName("error/500");
        return mav;
    }

    /**
     * SQL例外の処理
     */
    @ExceptionHandler(java.sql.SQLException.class)
    public ModelAndView handleSQLException(java.sql.SQLException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "データベース操作でエラーが発生しました。");
        mav.addObject("details", "SQLエラー: " + e.getSQLState());
        mav.setViewName("error/500");
        return mav;
    }

    /**
     * 一般的な実行時例外の処理
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "システムエラーが発生しました。");
        mav.addObject("details", e.getMessage());
        mav.setViewName("error/500");
        return mav;
    }

    /**
     * その他の例外の処理
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "予期しないエラーが発生しました。");
        mav.addObject("details", e.getMessage());
        mav.setViewName("error/500");
        return mav;
    }
}