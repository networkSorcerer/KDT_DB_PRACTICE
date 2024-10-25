import dao.EmpDAO;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        EmpDAO dao = new EmpDAO();

        while (true) {
            System.out.println("===== EMP TABLE =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]select [2]insert [3]update [4]delete [5]exit :");
            int choice = sc.nextInt();
            switch (choice){
                case 1 :
                    List<EmpVO> list = dao.empSelect();
                    dao.empSelectResult(list);
                    break;
                case 2 :
                    boolean isSuccess = dao.empInsert(empIInput());
                    if(isSuccess)System.out.println("사원등록에 성공했습니다.");
                    else System.out.println("사원등록에 실패했습니다.");
                    break;
                case 3 :
                    boolean isSuccess1 =dao.empUpdate(empUInput2());
                    if(isSuccess1)System.out.println("사원 수정에 성공했습니다.");
                    else System.out.println("사원 수정에 실패했습니다.");
                    break;
                case 4:
                    boolean isSuccess2 =dao.empDelete(empDInput3());
                    if(isSuccess2)System.out.println("사원 삭제에 성공했습니다.");
                    else System.out.println("사원 삭제에 실패했습니다.");
                    break;
            }
        }
    }
    public static EmpVO empIInput() {
        Scanner sc  =new Scanner(System.in);
        System.out.println("사원정보를 입력하세요");
        System.out.println("사원 번호 : ");
        int empno = sc.nextInt();
        System.out.println("사원 이름 : ");
        String name = sc.next();
        System.out.println("직책 : ");
        String job = sc.next();
        System.out.println("상관 사원번호 : ");
        int mgr = sc.nextInt();
        System.out.println("입사일 : ");
        String date = sc.next();
        System.out.println("급여 : ");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.println("성과릅 : ");
        BigDecimal comm = sc.nextBigDecimal();
        System.out.println("부서 번호");
        int deptno =sc.nextInt();
        EmpVO vo = new EmpVO(empno,name,job,mgr, Date.valueOf(date),sal,comm,deptno);
        return vo;
    }
    public static EmpVO empUInput2(){
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 사원의 이름을 입력하세요 : ");
        String name =sc.next();
        System.out.println("직책 : ");
        String job =sc.next();
        System.out.println("급여 : ");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.println("성과급 : ");
        BigDecimal comm = sc.nextBigDecimal();
        EmpVO vo = new EmpVO(name,job,sal,comm);
        return vo;
    }
    public static EmpVO empDInput3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 사원의 이름을 입력하세요");
        String name = sc.next();
        EmpVO vo = new EmpVO(name);
        return vo;
    }



}