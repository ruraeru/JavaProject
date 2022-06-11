package Cafe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

class Seat {
    int seatId;
}

public class StudyCafeManage {
    static Scanner in = new Scanner(System.in);
    static void adminM() {
        System.out.println("관리자 인증");
        System.out.print("입력>>> ");
        if (Manage.getADMIN_PASS().equals(in.next())) {
            System.out.println("\t\t===== 관리자 모드 진입 =====");
            boolean menu = true;
            while(menu) {
                System.out.print("1번 : 룸의 좌석 수 설정\n2번 : 채워진 좌석 조회\n3번 : 월 단위 통계 조회\n4번 : 회원 리스트 관리\n5번 : 신고 내역\n6번 : 이용중인 회원\n7번 : 메인메뉴\n입력 : ");
                int accessMode = in.nextInt();
                switch (accessMode) {
                    case 1 -> {
                        System.out.println("\t\t===== 좌석 설정 =====");
                        System.out.print("좌석 수를 입력하세요 >> ");
                        int seatNum = in.nextInt();
                 /*   for (int i = 0; i < Room.COVID_SEAT.length; i++) {
                        Room.COVID_SEAT[i] = new boolean[seatNum];
                    }*/
                        Room.GROUP_ROOM = new int[seatNum];
                    }
                    case 2 -> {
                        showSeatList();
                    }
                    case 3 -> {
                        if (Manage.memberList.size() != 0) {
                            System.out.println("\t\t===== 통계 조회 =====");
                            System.out.printf("회원수 : %d\n", Manage.memberList.size());
                            for (int i = 0; i < Manage.ages.length; i++) {
                                if (Manage.ages[i] != 0) {
                                    System.out.printf("%d대 : %d명\n", (i+1)*10, Manage.ages[i]);
                                }
                            }
                            System.out.println();
                            for (int i = 0; i < Manage.jobs.length; i++) {
                                if (Manage.jobs[i] != 0) {
                                    System.out.printf("%s : %d명\n", Manage.jobList[i], Manage.jobs[i]);
                                }
                            }
                        } else System.out.println("가입된 회원이 없습니다.");
                    }
                    case 4 -> {
                        if (Manage.memberList.size() != 0) {
                            showMemberList();
                            System.out.println();
                            while(true) {
                                System.out.print("몇번째 회원을 조회하시겠습니까? : ");
                                int index = in.nextInt() - 1;

                                if(index < 0 || index > Manage.memberList.size()) {
                                    System.out.println("종료합니다.\n");
                                    break;
                                } else {
                                    System.out.printf("\n====== \"%s\"님의 정보 ======\n", Manage.memberList.get(index).getPhoneNum());
                                    Manage.memberList.get(index).Print();
                                }
                            }
                        } else System.out.println("등록된 회원이 없습니다.");
                    }
                    case 5 -> {
                        //신고 내역 보기
                        System.out.println("\t\t===== 신고 내역 =====");
                        for (int i = 0; i < Manage.memberList.size(); i++) {
                            if (Manage.memberList.get(i).getReportCnt() != 0) {
                                System.out.printf("%s님의 신고\n", Manage.memberList.get(i).getPhoneNum());
                                System.out.printf("신고 누적 : %d\n", Manage.memberList.get(i).getReportCnt());
                                System.out.printf("사유 : %s\n", Manage.memberList.get(i).getComment());
                            }
                        }
                    }
                    case 6 -> {
                        if (Manage.usingMemberList.size() != 0) {
                            System.out.println("\t\t===== 이용중인 회원 =====");
                            for (int i = 0; i < Manage.usingMemberList.size(); i++) {
                                System.out.printf("아이디 : %s\n", Manage.usingMemberList.get(i).getPhoneNum());
                                System.out.printf("상태 : %s\n", Manage.usingMemberList.get(i).getState());
                                System.out.printf("좌석 : %s번\n", Manage.usingMemberList.get(i).getSeatNum());
                                System.out.println();
                            }
                        }
                    }
                    case 7 -> {
                        System.out.println("메인으로 나갑니다.");
                        menu = false;
                    }
                }
            }
        } else System.out.println("인증에 실패하였습니다.");


        //관리자 모드
        //룸의 좌석 수 설정
        //채워진 좌석 조회
        //월 단위로 매일 사용한 좌석 수 조회(전체 좌석 수 대비 사용한 좌석 수, 룸 종류별로)
        //월단위 사용한 사람들의 직업 조회
        //시간대별로 입장한 사람들 수 조회
        //멤버 리스트 관리
    }
    static void memberAccessM() {
        LocalDateTime t = LocalDateTime.now();
        boolean signUp = true;
        boolean signIn = false;
        int seat = 0;
        System.out.print("ID(핸드폰 번호) : ");
        String phone = in.next();
        for (int i = 0; i < Manage.memberList.size(); i++) {
            if (Manage.memberList.get(i).getPhoneNum().equals(phone)) {
                signUp = false;
                signIn = true;
            }
        }
        if (signUp) {
            System.out.print("비밀번호 : ");
            String passWord = in.next();
            System.out.print("나이 : ");
            int age = in.nextInt();
            System.out.println("다음 직업중 해당하는 것을 골라주세요.");
            for (int i = 0 ; i < Manage.jobList.length; i++) {
                System.out.printf("%d번 : %s\n", (i+1), Manage.jobList[i]);
            }
            System.out.print("직업 : ");
            int job = in.nextInt();
            String jobs = null;
            switch (job) {
                case 1 -> jobs = "중학생";
                case 2 -> jobs = "고등학생";
                case 3 -> jobs = "대학생";
                case 4 -> jobs = "직장인";
                case 5 -> jobs = "무직";
            }
            Manage.memberList.add(new Member(phone, passWord, age, jobs,
                    t.getYear(), t.getMonthValue(), t.getDayOfMonth()));
            //, t.getHour(), t.getMinute(), t.getSecond())
        }

        if (signIn) {
            int ticket = 0;
            int ticketSelect = 0;
            System.out.println("이용권을 선택해주세요.");

            System.out.println("당일권 : 1번\n시간권 : 2번\n기간권 : 3번");
            System.out.print("입력>>> ");
            ticket = in.nextInt() - 1;
            if (ticket > 3 || ticket < 0) {
                ticket = 0;
                System.out.println("이용권을 다시 선택해주세요.");
                System.out.print("입력>>> ");
                ticket = in.nextInt() - 1;
            }
            for (int j = 0; j < Manage.ticket[ticket].length; j++) {
                System.out.printf("%d번 : %s : %s원\n", (j+1),Manage.ticket[ticket][j], Manage.ticketPrice[ticket][j]);
            }
            System.out.print("입력>>> ");
            ticketSelect = in.nextInt() - 1;
            if (ticketSelect > 3 || ticketSelect < 0) {
                ticketSelect = 0;
                System.out.println("이용권을 다시 선택해주세요.");
                System.out.print("입력>>> ");
                ticketSelect = in.nextInt() - 1;
            }

            showSeatList(); //좌석조회
            System.out.println("칸막이석, 일반석, 스터디룸");
            System.out.print("좌석 유형을 입력해주세요>>> ");
            String seatType = in.next();

            while(!(seatType.equals("칸막이석") || seatType.equals("일반석") || seatType.equals("스터디룸"))) {
                System.out.println("칸막이석, 일반석, 스터디룸");
                System.out.print("좌석 유형을 입력해주세요>>> ");
                seatType = in.next();
            }

            System.out.println("좌석을 선택하세요.");
            System.out.print("입력>>> ");
            seat = in.nextInt() - 1;

            switch (seatType) {
                case "칸막이석" -> {
                    if (seat <= 5) Room.COVID_SEAT[0][seat] = 0;
                    else if(seat <= 10) Room.COVID_SEAT[1][seat-5] = 0;
                    else if(seat <= 15) Room.COVID_SEAT[2][seat-10] = 0;
                    else if(seat <= 20) Room.COVID_SEAT[3][seat-15] = 0;
                    else if(seat <= 25) Room.COVID_SEAT[4][seat-20] = 0;
                }
                case "일반석" -> {
                    if (seat <= 5) Room.SEAT[0][seat] = 0;
                    else if(seat <= 10) Room.SEAT[1][seat-5] = 0;
                    else if(seat <= 15) Room.SEAT[2][seat-10] = 0;
                    else if(seat <= 20) Room.SEAT[3][seat-15] = 0;
                    else if(seat <= 25) Room.SEAT[4][seat-20] = 0;
                }
                case "스터디룸" -> {
                    Room.GROUP_ROOM[seat] = 0;
                }
            }
            seat = seat + 1;
            String memberSeat = seatType + " " + seat;
            String IN_TIME = String.valueOf(t.getHour()) + "시 " + String.valueOf(t.getMinute()) + "분";
            Manage.usingMemberList.add(new Member(phone, "입장", Manage.ticket[ticket][ticketSelect], memberSeat, IN_TIME));
        }


        //유저 모드
        //차감된 시간 및 날짜 알려줌
        //사용 가능한 좌석 배열로 보여줌
        //좌석 배정시 원하는 종류의 좌석이 남아있는 경우 좌석 번호가 배정
        //좌석이 만석인 경우 입장 X
    }
    static void showSeatList() {
        System.out.println("  ===== COVID 좌석 =====");
        for (int i = 0 ; i < Room.COVID_SEAT.length; i++) {
            for (int j = 0 ; j < Room.COVID_SEAT[i].length; j++) {
                if (Room.COVID_SEAT[i][j] < 10) {
                    if (Room.COVID_SEAT[i][j] == 0) {
                        System.out.print(" [\s사용중\s]");
                    } else System.out.printf(" [\s0%d\s]", Room.COVID_SEAT[i][j]);
                } else {
                    if (Room.COVID_SEAT[i][j] == 0) {
                        System.out.print(" [\s사용중\s]");
                    } else System.out.printf(" [\s%d\s]", Room.COVID_SEAT[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("\t\t\t\t===== 일반 좌석 =====");
        for (int i = 0 ; i < Room.SEAT.length; i++) {
            for (int j = 0 ; j < Room.SEAT[i].length; j++) {
                if (Room.SEAT[i][j] < 10) {
                    if (Room.SEAT[i][j] == 0) {
                        System.out.print(" [\s사용중\s]");
                    } else System.out.printf(" [\s0%d\s]", Room.SEAT[i][j]);
                } else {
                    if (Room.SEAT[i][j] == 0) {
                        System.out.print(" [\s사용중\s]");
                    } else System.out.printf(" [\s%d\s]", Room.SEAT[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("===== 스터디룸 좌석 =====");
        for (int i = 0; i < Room.GROUP_ROOM.length; i++) {
            if (Room.GROUP_ROOM[i] % 3 == 1) {
                System.out.println();
            }
            if (Room.GROUP_ROOM[i] < 10) {
                if (Room.GROUP_ROOM[i] == 0) {
                    System.out.print(" [\s사용중\s]");
                } else System.out.printf(" [\s0%d\s]", Room.GROUP_ROOM[i]);

            } else {
                if (Room.GROUP_ROOM[i] == 0) {
                    System.out.print(" [\s사용중\s]");
                } else System.out.printf(" [\s%d\s]", Room.GROUP_ROOM[i]);
            }
        }
        System.out.println();
    }
    static void showMemberList() {
        System.out.println("\t\t===== 회원 리스트 =====");
        for (int i = 0; i < Manage.memberList.size(); i++) {
            System.out.printf("%d번 : \"%s\"님\n", i + 1, Manage.memberList.get(i).getPhoneNum());
        }
    }
    static void memberExitM() {
        LocalDateTime t = LocalDateTime.now();
        System.out.println("\t\t===== 퇴실 =====");
        System.out.print("퇴실할 아이디 입력 : ");
        String logOutID = in.next();
   /*     System.out.print("비밀번호 입력 : ");
        String logOutPass = in.next();*/
        /*Manage.memberList.removeIf(list -> list.getPhoneNum().equals(logOutID) && list.getPIN_PASS_WORD().equals(logOutPass));*/
        /*Manage.usingMemberList.removeIf(list -> list.getPhoneNum().equals(logOutID) && list.getPIN_PASS_WORD().equals(logOutPass));*/
//        for (int i = 0; i < Cafe.Manage.memberList.size(); i++) {
//            if(Cafe.Manage.memberList.get(i).getPhoneNum().equals(logOutID) && Cafe.Manage.memberList.get(i).getPIN_PASS_WORD().equals(logOutPass)) {
//                Cafe.Manage.memberList.remove(i);
//            }
//        }
        Manage.usingMemberList.removeIf(list -> list.getPhoneNum().equals(logOutID));
        System.out.print("사용 시간을 입력해주세요>>> ");
        String USE_TIME = in.next();
        String EXIT_TIME = String.valueOf(t.getHour()) + "시 " + String.valueOf(t.getMinute()) + "분";
        for (int i = 0; i < Manage.usingMemberList.size(); i++) {
            if (Manage.usingMemberList.get(i).getPhoneNum().equals(logOutID)) {
                Manage.usingMemberList.get(i).setUSE_TIME(USE_TIME);
                Manage.usingMemberList.get(i).setEXIT_TIME(EXIT_TIME);
                Manage.usingMemberList.get(i).state = "퇴실";
            }
        }
        //회원 퇴장 모드
        //남은 시간 차감
        //시간을 초과한 경우 추가 요금 부가됨.
    }
    static void userReport() {
        System.out.print("신고할 유저의 아이디를 입력하세요 : ");
        String reportID = in.next();
        String[] reportSelect = new String[]{"소음", "취식", "관리자 빨리 조치 요함"};
        for (int i = 0; i < reportSelect.length; i++) {
            System.out.printf("%d번 : %s\n", i+1, reportSelect[i]);
        }
        System.out.print("신고할 사유를 선택하세요 : ");
        int comment = in.nextInt() - 1;
        for (int i = 0; i < Manage.memberList.size(); i++) {
            if (Manage.memberList.get(i).getPhoneNum().equals(reportID)) {
                Manage.memberList.get(i).Report(1, reportSelect[comment]);
            }
        }
    }
    public static void main(String[] args) {
        int index = 1;
        for (int i = 0 ; i < Room.COVID_SEAT.length; i++) {
            for (int j = 0 ; j < Room.COVID_SEAT[i].length; j++) {
                Room.COVID_SEAT[i][j] = index++;
            }
        }
        index = 1;
        for (int i = 0 ; i < Room.SEAT.length; i++) {
            for (int j = 0 ; j < Room.SEAT[i].length; j++) {
                Room.SEAT[i][j] = index++;
            }
        }
        index = 1;
        for (int i = 0; i < Room.GROUP_ROOM.length; i++) {
            Room.GROUP_ROOM[i] = index++;
        }
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
                case 6 -> {
                    showSeatList();
                }
            }
        }
    }
}

enum JobList {
    중학생, 고등학생, 대학생, 직장인, 무직
}