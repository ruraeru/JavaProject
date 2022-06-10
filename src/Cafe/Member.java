package Cafe;

public class Member {
    private String IN_TIME, EXIT_TIME, USE_TIME, AccessType;
    private int SeatNum, reportCnt;
    private String comment = "";
    private String phoneNum, PIN_PASS_WORD, job;
    private int age;
    int year,month, day, hour, min, sec;
    boolean state;
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

    public boolean getState() {
        return state;
    }
    public Member(String phoneNum, String PIN_PASS_WORD, int age, String job, int... time) {
        this.phoneNum = phoneNum;
        this.PIN_PASS_WORD = PIN_PASS_WORD;
        this.job = job;
        this.age = age;
        this.year = time[0];
        this.month = time[1];
        this.day = time[2];
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

    public Member(String phoneNum, boolean state, String ticket, int seat) {
        this.phoneNum = phoneNum;
        this.state = state;
        this.SeatNum = seat;
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
    }

    public void UsingMember() {

    }

    public void Report(int reportCnt, String comment) {
        this.reportCnt += reportCnt;
        this.comment += comment + " ";
    }
}