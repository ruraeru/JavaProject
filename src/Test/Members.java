package Test;

import java.util.ArrayList;
import java.util.Scanner;

class Member {
    public static int[] ages = { 0, 0, 0, 0, 0, 0, 0 }; // 10 ~ 70세 연령만 분류하기 떄문에 사이즈는 7
    public static int[] jobs = { 0, 0, 0, 0, 0 }; // 현재 직업은 5개로 분류 하므로 사이즈 5
    final String[] jobList = { "교사, 교수", "주부", "학생", "직장인", "무직" }; // for문으로 출력하기 위한 String 배열

    String name, job;
    int age;
    public Member(String name, int age, String job) { //name, job, age를 초기화하는 생성자
        this.name = name;
        this.age = age;
        this.job = job;
        if ( age > 9 && age < 81) { // 9 < age < 81
            switch (age/10) { // 1,2,3,4,5,6,7로 나누고 특정 배열에 값증가
                case 1 -> ages[0] += 1;
                case 2 -> ages[1] += 1;
                case 3 -> ages[2] += 1;
                case 4 -> ages[3] += 1;
                case 5 -> ages[4] += 1;
                case 6 -> ages[5] += 1;
                case 7 -> ages[6] += 1;
            }
            switch (job) { //job을 받아 분류하여 배열값 증가
                case "교사", "교수" -> jobs[0]++;
                case "주부" -> jobs[1]++;
                case "학생" -> jobs[2]++;
                case "직장인" -> jobs[3]++;
                case "무직" -> jobs[4]++;
            }
        }
    }
    public void Print() { //유저를 하나하나 조회할때 출력을 위한 메서드
        System.out.printf("\t회원 이름 : %s\n", name);
        System.out.printf("\t나이 : %d\n", age);
        System.out.printf("\t직업 : %s\n\n", job);
    }
    public void BunSuk() { //유저 연령, 직업을 출력하기 위한 메서드
        int member = 0;
        for (int i = 0; i < ages.length; i++) {
            if(ages[i] != 0) {
                member+=ages[i];
            }
        }
        System.out.printf("통계 회원수 : %d\n", member);
        System.out.println();
        for (int i = 0; i < ages.length; i++) { //연령
            if(ages[i] != 0) {
                System.out.printf("%d대 : %d명\n", (i+1)*10, ages[i]);
            }
        }
        System.out.println();
        for (int i = 0; i < jobs.length; i++) { //직업
            if(jobs[i] != 0) {
                System.out.printf("%s : %d명\n", jobList[i], jobs[i]);
            }
        }
    }
}

public class Members {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Member> memberList = new ArrayList<>();

        System.out.print("회원수 입력 : ");
        int memberNum = in.nextInt();

        for (int i = 0; i < memberNum; i++) { //회원수 만큼 반복
            System.out.print("회원 이름 입력 : ");
            String name = in.next();
            System.out.print("나이 입력 : ");
            int age = in.nextInt();
            System.out.print("직업 입력 : ");
            String job = in.next();
            memberList.add(new Member(name, age, job)); //객체를 만들어 대입
        }

        //회원 명단 출력
        System.out.println("\n====== 회원 리스트 ======\n");
        for (int i = 0; i < memberNum; i++) { //회원수 만큼 반복
            System.out.printf("%d번째 : \"%s\"님\n", i + 1, memberList.get(i).name);
        }
        System.out.println();
        while (true) { //회원 리스트 조회
            System.out.print("몇번째 회원을 조회하시겠습니까? : ");
            int index = in.nextInt() - 1; //1번째는 배열에 0 그러므로 -1

            if(index < 0 || index > memberList.size()) { //인덱스 범위를 벗어나면 종료
                System.out.println("종료합니다.\n");
                break;
            } else {
                System.out.printf("\n====== \"%s\"님의 정보 ======\n", memberList.get(index).name);
                memberList.get(index).Print();
            }
        }
        System.out.printf("전체 회원수 : %d명\n", memberNum);
        memberList.get(0).BunSuk(); //유저 직업, 나이 통계를 출력, static 클래스 변수로 값을 공유하므로 인덱스값은 상관없음
    }
}