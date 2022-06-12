package Cafe;

public class Member {
    private String IN_TIME, EXIT_TIME, AccessType;
    private String seatType;
    private int USE_TIME;
    private int REMAIN_TIME;
    private int reportCnt;
    private int seatNum;
    private String comment = "";
    private String phoneNum, PIN_PASS_WORD, job;
    private int age;
    int year,month, day, hour, min, sec;
    String state;
    public int getREMAIN_TIME() {
        return REMAIN_TIME;
    }
    public String getAccessType() {
        return AccessType;
    }
    public String getIN_TIME() {
        return IN_TIME;
    }
    public String getSeatNum() {
        return seatType + String.valueOf(seatNum);
    }
    public int getSeat() {
        return seatNum;
    }
    public String getSeatType() {
        return seatType;
    }
    public void setUSE_TIME(int USE_TIME) {
        this.USE_TIME += USE_TIME;
    }
    public void setEXIT_TIME(String EXIT_TIME) {
        this.EXIT_TIME = EXIT_TIME;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getPIN_PASS_WORD() {
        return PIN_PASS_WORD;
    }
    public int getReportCnt() {
        return reportCnt;
    }
    public String getComment() {
        return comment;
    }
    public int getAge() {
        return age;
    }
    public String getState() {
        return state;
    }

    //유저리스트
    public Member(String phoneNum, String PIN_PASS_WORD, int age, String job, int... time) {
        this.phoneNum = phoneNum;
        this.PIN_PASS_WORD = PIN_PASS_WORD;
        this.job = job;
        this.age = age;
        this.year = time[0];
        this.month = time[1];
        this.day = time[2];
        Manage.UserMonth[month-1]++;
        if (age >= 10 && age <= 80) {
            Manage.ages[(age/10)-1]++;
            switch (job) {
                case "중학생" -> Manage.jobs[0]++;
                case "고등학생" -> Manage.jobs[1]++;
                case "대학생" -> Manage.jobs[2]++;
                case "직장인" -> Manage.jobs[3]++;
                case "무직" -> Manage.jobs[4]++;
            }
        }
    }

    //사용중인 유저
    public Member(String phoneNum, String state, String ticket, int REMAIN_TIME, String seatType, int seat, String IN_TIME,int month, int day ,int hour, int min) {
        this.phoneNum = phoneNum;
        this.AccessType = ticket;
        switch (REMAIN_TIME) {
            case 7, 28, 14, 42 -> {
                this.REMAIN_TIME += REMAIN_TIME * 24;
            }
            default -> {
                this.REMAIN_TIME += REMAIN_TIME;
            }
        }
        this.seatType = seatType;
        this.state = state;
        this.seatNum = seat;
        this.IN_TIME = IN_TIME;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        System.out.println("아이디 : " + phoneNum + " 상태 : " + state + " 입장시간 : " + IN_TIME);
        System.out.printf("%s %d추가\n", ticket, REMAIN_TIME);
    }

    public void Print() {
        System.out.printf("\t가입 날짜 : %d년 %d월 %d일\n", year, month, day);
        System.out.printf("\tID(핸드폰 번호) : %s\n", phoneNum);
        System.out.printf("\t비밀번호 : %s\n", PIN_PASS_WORD);
        System.out.printf("\t나이 : %d\n", age);
        System.out.printf("\t직업 : %s\n", job);
        System.out.printf("\t신고 누적 : %d번\n", reportCnt);
        if (!comment.equals("")) {
            System.out.printf("\t신고 사유 : %s\n\n", comment);
        }
        System.out.println("\t===== 최근 이용 =====");
        System.out.printf("퇴실 날짜 : %s\n", EXIT_TIME);
        System.out.printf("이용 시간 : %d시간\n", USE_TIME);
    }

    public void Report(int reportCnt, String comment) {
        this.reportCnt += reportCnt;
        this.comment += comment + " ";
    }
}