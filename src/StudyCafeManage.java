import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Input {
    static Scanner in = new Scanner(System.in);
}
class Member {
    String phoneNum, PIN_PASS_WORD, IN_TIME, EXIT_TIME, job;
    boolean state;
    int SeatNum;
    String AccessType;
    String USE_TIME;
    public Member(String phoneNum, String PIN_PASS_WORD, String job) {
        this.phoneNum = phoneNum;
        this.PIN_PASS_WORD = PIN_PASS_WORD;
        this.job = job;
    }
    public void Print() {
        System.out.printf("\tID(핸드폰 번호) : %s\n", phoneNum);
        System.out.printf("\t비밀번호 : %s\n", PIN_PASS_WORD);
        System.out.printf("\t직업 : %s\n\n", job);
    }
}

class Manage {
    public static ArrayList<Member> memberList = new ArrayList<>();
}

class Room {
    public static int[][] COVID_SEAT = new int[5][];
    public static int[][] SEAT = new int[10][];
    public static int[][] GROUP_ROOM = new int[10][];
}

class Seat {
    String seatId;
}

public class StudyCafeManage {
    static void adminM() {
        System.out.println("\t\t===== 관리자 모드 진입 =====");
        boolean menu = true;
        while(menu) {
            System.out.print("1번 : 룸의 좌석 수 설정\n2번 : 채워진 좌석 조회\n3번 : 월 단위 통계 조회\n4번 : 회원 리스트 관리\n5번 : 메인메뉴\n입력 : ");
            int accessMode = Input.in.nextInt();
            switch (accessMode) {
                case 1 -> {
                    System.out.println("\t\t===== 좌석 설정 =====");
                    System.out.print("좌석 수를 입력하세요 >> ");
                    int seatNum = Input.in.nextInt();
                    for (int i = 0; i < Room.COVID_SEAT.length; i++) {
                        Room.COVID_SEAT[i] = new int[seatNum];
                    }
                }
                case 2 -> {
                    System.out.println("\t\t===== 좌석 조회 =====");
                    if (true ) { //조건 달기
                        int seatNumber = 1;
                        for (int i = 0; i < Room.COVID_SEAT.length; i++) {
                            for (int j = 0; j < Room.COVID_SEAT[i].length; j++) {
                                if (Room.COVID_SEAT[i][j] == 0) {
                                    if (seatNumber < 10) {
                                        System.out.printf("[\s0%d\s]", seatNumber++);
                                    } else System.out.printf("[\s%d\s]", seatNumber++);
                                }
                            }
                            System.out.println();
                        }
                    } else System.out.println("좌석 수를 먼저 설정해주세요.");
                }
                case 3 -> {
                    System.out.println("\t\t===== 통계 조회 =====");
                }
                case 4 -> {
                    if (Manage.memberList.size() != 0) {
                        showMemberList();
                        System.out.println();
                        while(true) {
                            System.out.print("몇번째 회원을 조회하시겠습니까? : ");
                            int index = Input.in.nextInt() - 1;

                            if(index < 0 || index > Manage.memberList.size()) {
                                System.out.println("종료합니다.\n");
                                break;
                            } else {
                                System.out.printf("\n====== \"%s\"님의 정보 ======\n", Manage.memberList.get(index).phoneNum);
                                Manage.memberList.get(index).Print();
                            }
                        }
                    } else System.out.println("등록된 회원이 없습니다.");
                }
                case 5 -> {
                    System.out.println("메인으로 나갑니다.");
                    menu = false;
                }
                default -> {
                    System.out.println("다시 선택해주세요.");
                }
            }
        }
        //관리자 모드
        //룸의 좌석 수 설정
        //채워진 좌석 조회
        //월 단위로 매일 사용한 좌석 수 조회(전체 좌석 수 대비 사용한 좌석 수, 룸 종류별로)
        //월단위 사용한 사람들의 직업 조회
        //시간대별로 입장한 사람들 수 조회
        //멤버 리스트 관리
    }
    static void memberAccessM() {
        System.out.print("ID(핸드폰 번호) : ");
        String phone = Input.in.next();
        System.out.print("비밀번호 : ");
        String passWord = Input.in.next();
        JobList[] jobList = JobList.values();
        System.out.println("다음 직업중 해당하는 것을 골라주세요.");
        for (int i = 0 ; i < jobList.length; i++) {
            System.out.printf("%d번 : %s \n", (i+1), jobList[i]);
        }
        System.out.print("직업 : ");
        int job = Input.in.nextInt();
        String jobs = null;
        switch (job) {
            case 1 -> jobs = "중학생";
            case 2 -> jobs = "고등학생";
            case 3 -> jobs = "대학생";
            case 4 -> jobs = "직장인";
            case 5 -> jobs = "무직";
        }
        Manage.memberList.add(new Member(phone, passWord, jobs));

        //유저 모드
        //차감된 시간 및 날짜 알려줌
        //사용 가능한 좌석 배열로 보여줌
        //좌석 배정시 원하는 종류의 좌석이 남아있는 경우 좌석 번호가 배정
        //좌석이 만석인 경우 입장 X
    }
    static void showMemberList() {
        System.out.println("\t\t===== 회원 리스트 =====");
        for (int i = 0; i < Manage.memberList.size(); i++) {
            System.out.printf("%d번 : \"%s\"님\n", i + 1, Manage.memberList.get(i).phoneNum);
        }
    }
    static void memberExitM() {
        System.out.println("\t\t===== 퇴실 =====");
        System.out.print("퇴실할 아이디 입력 : ");
        String logOutID = Input.in.next();
        System.out.print("비밀번호 입력 : ");
        String logOutPass = Input.in.next();
        for (int i = 0; i < Manage.memberList.size(); i++) {
            if(Manage.memberList.get(i).phoneNum.equals(logOutID) && Manage.memberList.get(i).PIN_PASS_WORD.equals(logOutPass)) {
                Manage.memberList.remove(i);
            }
        }
        //회원 퇴장 모드
        //남은 시간 차감
        //시간을 초과한 경우 추가 요금 부가됨.
    }
    static void userReport() {
        System.out.println("신고할 유저의 아이디를 입력하세요.");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /*
        추가
        회원리스트와 입장한 회원리스트를 분리해서 관리해야함.
        */
        while (true) {
            System.out.print("1번 : 관리자 모드\n2번 : 회원 입장\n3번 : 회원 퇴장\n4번 : 신고\n5번 : 종료\n입력 : ");
            int AccessMode = in.nextInt();
            switch (AccessMode) {
                case 1 -> adminM();
                case 2 -> memberAccessM();
                case 3 -> memberExitM();
                case 4 -> userReport();
                case 5 -> {
                    System.out.println("종료");
                    return;
                }
            }
        }
    }
}

enum JobList {
    중학생, 고등학생, 대학생, 직장인, 무직;
}