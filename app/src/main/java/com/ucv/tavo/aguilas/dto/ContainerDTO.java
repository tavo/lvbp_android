package com.ucv.tavo.aguilas.dto;

import java.io.Serializable;

/**
 * Created by gustavo on 9/12/14.
 */

public class ContainerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private int iconResId;
    private String param;

    public ContainerDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
