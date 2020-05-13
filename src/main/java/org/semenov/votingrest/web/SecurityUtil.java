package org.semenov.votingrest.web;

public class SecurityUtil {

    public static final int START_SEQ = 100000;
    private static int id = START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}