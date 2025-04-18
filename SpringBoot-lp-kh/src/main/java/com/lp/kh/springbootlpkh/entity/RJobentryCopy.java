package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * (RJobentryCopy)实体类
 *
 * @author makejava
 * @since 2025-03-19 13:56:28
 */
public class RJobentryCopy implements Serializable {
    private static final long serialVersionUID = 105459922548350083L;

    private String idJobentryCopy;

    private String idJobentry;

    private String idJob;

    private String idJobentryType;

    private String nr;

    private String guiLocationX;

    private String guiLocationY;

    private String guiDraw;

    private String parallel;


    public String getIdJobentryCopy() {
        return idJobentryCopy;
    }

    public void setIdJobentryCopy(String idJobentryCopy) {
        this.idJobentryCopy = idJobentryCopy;
    }

    public String getIdJobentry() {
        return idJobentry;
    }

    public void setIdJobentry(String idJobentry) {
        this.idJobentry = idJobentry;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdJobentryType() {
        return idJobentryType;
    }

    public void setIdJobentryType(String idJobentryType) {
        this.idJobentryType = idJobentryType;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getGuiLocationX() {
        return guiLocationX;
    }

    public void setGuiLocationX(String guiLocationX) {
        this.guiLocationX = guiLocationX;
    }

    public String getGuiLocationY() {
        return guiLocationY;
    }

    public void setGuiLocationY(String guiLocationY) {
        this.guiLocationY = guiLocationY;
    }

    public String getGuiDraw() {
        return guiDraw;
    }

    public void setGuiDraw(String guiDraw) {
        this.guiDraw = guiDraw;
    }

    public String getParallel() {
        return parallel;
    }

    public void setParallel(String parallel) {
        this.parallel = parallel;
    }

}

