package dao;

import common.Common;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.reflect.Array.setInt;

public class EmpDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt =null;
    ResultSet rs = null;
    Scanner sc = null;

    public EmpDAO() { // scanner 중복을 줄여줌
        sc = new Scanner(System.in);
    }

    // select 기능(조회) 구현
    public List<EmpVO> empSelect() {
        List<EmpVO>list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB연결
            stmt = conn.createStatement(); // statement 생성
            String query = "select * from emp";
            rs = stmt.executeQuery(query);
            // excuteQuery : select 문과 같이 결과 값이 여러 개의 레코드로 반환되는 경우 사용
            // ResultSet : 여러 행의 결과를 받아서 반복자 제공
            // next() : 현재 행에서 한행 앞으로 이동
            // previous() :  현재 행에서 한행 뒤로 이동
            // first() : 첫번째 행으로 이동
            // last() : 마지막 행으로 이동
            while (rs.next()){
                int empno = rs.getInt("empno");
                String name = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date date = rs.getDate("hiredate");
                BigDecimal sal = rs.getBigDecimal("sal");
                BigDecimal comm = rs.getBigDecimal("comm");
                int deptno = rs.getInt("deptno");
                EmpVO vo = new EmpVO(empno, name,job,mgr,date,sal,comm,deptno);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            System.out.println("EMP 조회 실패!!!!");
            e.printStackTrace();
        }
        return list;
    }

    //insert 구현
    public boolean empInsert(EmpVO vo) {
        String sql = "insert into emp(empno, ename, job, mgr, hiredate,sal,comm,deptno)values(?,?,?,?,?,?,?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getEmpNO());
            psmt.setString (2,vo.getName());
            psmt.setString (3,vo.getJob());
            psmt.setInt(4,vo.getMgr());
            psmt.setDate(5,vo.getDate());
            psmt.setBigDecimal(6,vo.getSal());
            psmt.setBigDecimal(7,vo.getComm());
            psmt.setInt(8,vo.getDeptNo());
            psmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    public boolean empUpdate(EmpVO vo){
       String query = "update emp set job = ? , sal = ? , comm = ? where ename = ?  ";

       Connection conn = null;
       PreparedStatement pstmt = null;

       try {
           conn = Common.getConnection();
           pstmt = conn.prepareStatement(query);

           pstmt.setString(1,vo.getJob());
           pstmt.setBigDecimal(2,vo.getSal());
           pstmt.setBigDecimal(3,vo.getComm());
           pstmt.setString(4,vo.getName());

           return true;

       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }

    public boolean empDelete(EmpVO vo) {
        String query ="delete from emp where ename = ? ";

        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = Common.getConnection();
            psmt =conn.prepareStatement(query);

            psmt.setString(1,vo.getName());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void empSelectResult(List<EmpVO>list){
        System.out.println("-".repeat(20));
        System.out.println("       사원 정보   ");
        System.out.println("-".repeat(20));

        for(EmpVO e : list) {
            System.out.print(e.getEmpNO()+" ");
            System.out.print(e.getName()+" ");
            System.out.print(e.getJob()+" ");
            System.out.print(e.getMgr()+" ");
            System.out.print(e.getDate()+" ");
            System.out.print(e.getSal()+" ");
            System.out.print(e.getComm()+" ");
            System.out.print(e.getDeptNo()+" ");
            System.out.println();
        }
        System.out.println("-".repeat(20));


    }
}
