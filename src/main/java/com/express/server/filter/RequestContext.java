package com.express.server.filter;

import com.express.server.common.LoginSession;

/**
 * @author youping.tan
 * @date 2024/8/6 16:39
 */
public class RequestContext {

    private static final ThreadLocal<LoginSession> context = new ThreadLocal<>();

    private RequestContext() {

    }

    public static void add(LoginSession session) {
        context.set(session);
    }

    public static LoginSession get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}
