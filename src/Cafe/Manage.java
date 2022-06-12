package Cafe;

import java.util.ArrayList;

public class Manage {
    private static final String ADMIN_PASS = "P@$$w0rd";
    public static int[] UserMonth = new int[12];
    public static String getADMIN_PASS() {
        return ADMIN_PASS;
    }
    public static ArrayList<Member> memberList = new ArrayList<>();
    public static ArrayList<Member> usingMemberList = new ArrayList<>();
    public static int[] ages = new int[7];
    public static JobList[] jobList = JobList.values();
    public static int[] jobs = new int[jobList.length];
    public static int[][] ticket = new int[][]{
            {2, 3, 4, 8, 12},   //당일권
            {30, 50, 100, 200}, //시간권
            {7, 14, 28, 42}     //기간권
    };
    public static String[][] ticketPrice = new String[][] {
            {"3,000", "4,000", "5,000", "8,000", "10,000"}, //당일권 가격
            {"35,000", "60,000", "110,000", "190,000"}, //시간권 가격
            {"35,000", "65,000", "111,000", "180,000"} //기간권 가격
    };
    // "2주", "4주", "8주", "12주"
}
