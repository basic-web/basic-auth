package com.github.ququzone.common.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * pagination tag.
 *
 * @author Yang XuePing
 */
public class PaginationTag extends SimpleTagSupport {
    private int current;
    private int totalPage;
    private String baseUrl;

    public void doTag() throws JspException, IOException {
        boolean hasParam = baseUrl.contains("?");
        JspWriter out = getJspContext().getOut();
        out.println("<ul class='pagination'>");
        if (current > 1) {
            out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + (current - 1) + "'>上一页</a></li>");
        } else {
            out.println("<li class='disabled'><a href='#'>上一页</a></li>");
        }
        if (totalPage == 0) {
            out.println("<li class='active'><a href='#'>1</a></li>");
        }
        if (totalPage <= 10) {
            for (int i = 1; i <= totalPage; i++) {
                if (current != i) {
                    out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>"
                            + i + "</a></li>");
                } else {
                    out.println("<li class='active'><a href='#'>" + i
                            + "</a></li>");
                }
            }
        } else if (current - 1 <= 7) {
            for (int i = 1; i <= 9; i++) {
                if (current != i) {
                    out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>"
                            + i + "</a></li>");
                } else {
                    out.println("<li class='active'><a href='#'>" + i
                            + "</a></li>");
                }
            }
            if (totalPage > 11) {
                out.println("<li class='disabled'><a href='#'>...</a></li>");
            }
            for (int i = totalPage - 1; i <= totalPage; i++) {
                out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>" + i
                        + "</a></li>");
            }
        } else if (totalPage - current <= 6) {
            for (int i = 1; i <= 2; i++) {
                out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>" + i
                        + "</a></li>");
            }
            out.println("<li class='disabled'><a href='#'>...</a></li>");
            for (int i = totalPage - 7; i <= totalPage; i++) {
                if (current != i) {
                    out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>"
                            + i + "</a></li>");
                } else {
                    out.println("<li class='active'><a href='#'>" + i
                            + "</a></li>");
                }
            }
        } else {
            for (int i = 1; i <= 2; i++) {
                out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>" + i
                        + "</a></li>");
            }
            out.println("<li class='disabled'><a href='#'>...</a></li>");
            for (int i = current - 3; i <= current + 3; i++) {
                if (current != i) {
                    out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>"
                            + i + "</a></li>");
                } else {
                    out.println("<li class='active'><a href='#'>" + i
                            + "</a></li>");
                }
            }
            if (totalPage - 1 != current + 2) {
                out.println("<li class='disabled'><a href='#'>...</a></li>");
            }
            for (int i = totalPage - 1; i <= totalPage; i++) {
                out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + i + "'>" + i
                        + "</a></li>");
            }
        }
        if (totalPage > current) {
            out.println("<li><a href='" + baseUrl + (hasParam ? "&" : "?") + "page=" + (current + 1)
                    + "'>下一页</a></li>");
        } else {
            out.println("<li class='disabled'><a href='#'>下一页</a></li>");
        }
        out.println("</ul>");
        super.doTag();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
