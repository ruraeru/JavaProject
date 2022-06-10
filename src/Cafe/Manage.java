package Cafe;

import java.util.ArrayList;

public class Manage {
    public static ArrayList<Member> memberList = new ArrayList<>();
    public static ArrayList<Member> usingMemberList = new ArrayList<>();
    public static int[] ages = new int[7];
    public static JobList[] jobList = JobList.values();
    public static int[] jobs = new int[jobList.length];
    public static String[][] ticket = new String[][]{
            {"2시간 : 3,000원", "3시간 : 4,000원", "4시간 : 5,000원", "8시간 : 8,000원", "12시간 : 10,000원"}, // 당일
            {"30시간 : 35,000원", "50시간 : 60,000", "100시간 : 110,000원", "200시간 : 190,000"}, // 시간
            {"7일 : 35,000", "14일 : 65,000원", "28일 : 110,000원", "42일 : 180,000원"} //기간
    };
    // "2주", "4주", "8주", "12주"
}
