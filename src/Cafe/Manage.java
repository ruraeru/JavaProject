package Cafe;

import java.util.ArrayList;

public class Manage {
    private static final String ADMIN_PASS = "P@$$w0rd";
    public static String getADMIN_PASS() {
        return ADMIN_PASS;
    }
    public static ArrayList<Member> memberList = new ArrayList<>();
    public static ArrayList<Member> usingMemberList = new ArrayList<>();
    public static int[] ages = new int[7];
    public static JobList[] jobList = JobList.values();
    public static int[] jobs = new int[jobList.length];
    public static String[][] ticket = new String[][]{
            {"2시간", "3시간", "4시간", "8시간", "12시간"}, // 당일
            {"30시간", "50시간", "100시간", "200시간"}, // 시간
            {"7일", "14일", "28일", "42일"} //기간
    };
    public static String[][] ticketPrice = new String[][] {
            {"3,000", "4,000", "5,000", "8,000", "10,000"},
            {"35,000", "60,000", "110,000", "190,000"},
            {"35,000", "65,000", "111,000", "180,000"}
    };
    // "2주", "4주", "8주", "12주"
}
