package com.neo.beans;

import java.sql.Date;

public class CompleteTest extends BaseEntity {
    private int studentId;
    private Date date;
    private double score;
    private int q1_id, q2_id,q3_id,q4_id,q5_id,q6_id,q7_id,q8_id,q9_id,q10_id;
    private boolean q1_answ1,q1_answ2, q1_answ3, q1_answ4,
            q2_answ1,q2_answ2, q2_answ3, q2_answ4,
            q3_answ1,q3_answ2, q3_answ3, q3_answ4,
            q4_answ1,q4_answ2, q4_answ3, q4_answ4,
            q5_answ1,q5_answ2, q5_answ3, q5_answ4,
            q6_answ1,q6_answ2, q6_answ3, q6_answ4,
            q7_answ1,q7_answ2, q7_answ3, q7_answ4,
            q8_answ1,q8_answ2, q8_answ3, q8_answ4,
            q9_answ1,q9_answ2, q9_answ3, q9_answ4,
            q10_answ1,q10_answ2, q10_answ3, q10_answ4;

    public CompleteTest(){}
    public CompleteTest( int id, int studentId, Date date, double score,
                         int q1_id, boolean q1_answ1, boolean q1_answ2, boolean q1_answ3, boolean q1_answ4,
                         int q2_id, boolean q2_answ1, boolean q2_answ2, boolean q2_answ3, boolean q2_answ4,
                         int q3_id, boolean q3_answ1, boolean q3_answ2, boolean q3_answ3, boolean q3_answ4,
                         int q4_id, boolean q4_answ1, boolean q4_answ2, boolean q4_answ3, boolean q4_answ4,
                         int q5_id, boolean q5_answ1, boolean q5_answ2, boolean q5_answ3, boolean q5_answ4,
                         int q6_id, boolean q6_answ1, boolean q6_answ2, boolean q6_answ3, boolean q6_answ4,
                         int q7_id, boolean q7_answ1, boolean q7_answ2, boolean q7_answ3, boolean q7_answ4,
                         int  q8_id, boolean q8_answ1,boolean q8_answ2, boolean q8_answ3, boolean q8_answ4,
                         int q9_id, boolean q9_answ1, boolean q9_answ2, boolean q9_answ3, boolean q9_answ4,
                         int q10_id, boolean q10_answ1, boolean q10_answ2, boolean q10_answ3, boolean q10_answ4){
        this.setId(id); this.studentId=studentId; this.date = date; this.score = score;
        this.q1_id = q1_id; this.q1_answ1 = q1_answ1; this.q1_answ2 = q1_answ2; this.q1_answ3 = q1_answ3; this.q1_answ4 = q1_answ4;
        this.q2_id = q2_id; this.q2_answ1 = q2_answ1; this.q2_answ2 = q2_answ2; this.q2_answ3 = q2_answ3; this.q2_answ4 = q2_answ4;
        this.q3_id = q3_id; this.q3_answ1 = q3_answ1; this.q3_answ2 = q3_answ2; this.q3_answ3 = q3_answ3; this.q3_answ4 = q3_answ4;
        this.q4_id = q4_id; this.q4_answ1 = q4_answ1; this.q4_answ2 = q4_answ2; this.q4_answ3 = q4_answ3; this.q4_answ4 = q4_answ4;
        this.q5_id = q5_id; this.q5_answ1 = q5_answ1; this.q5_answ2 = q5_answ2; this.q5_answ3 = q5_answ3; this.q5_answ4 = q5_answ4;
        this.q6_id = q6_id; this.q6_answ1 = q6_answ1; this.q6_answ2 = q6_answ2; this.q6_answ3 = q6_answ3; this.q6_answ4 = q6_answ4;
        this.q7_id = q7_id; this.q7_answ1 = q7_answ1; this.q7_answ2 = q7_answ2; this.q7_answ3 = q7_answ3; this.q7_answ4 = q7_answ4;
        this.q8_id = q8_id; this.q8_answ1 = q8_answ1; this.q8_answ2 = q8_answ2; this.q8_answ3 = q8_answ3; this.q8_answ4 = q8_answ4;
        this.q9_id = q9_id; this.q9_answ1 = q9_answ1; this.q9_answ2 = q9_answ2; this.q9_answ3 = q9_answ3; this.q9_answ4 = q9_answ4;
        this.q10_id = q10_id; this.q10_answ1 = q10_answ1; this.q10_answ2 = q10_answ2; this.q10_answ3 = q10_answ3; this.q10_answ4 = q10_answ4;
    }

    @Override
    public String toString() {
        return "CompleteTest{" +
                "id"+this.getId()+
                "studentId" + studentId +
                "date=" + date +
                ", score=" + score +
                ", q1_id=" + q1_id +
                ", q1_answ1=" + q1_answ1 +
                ", q1_answ2=" + q1_answ2 +
                ", q1_answ3=" + q1_answ3 +
                ", q1_answ4=" + q1_answ4 +
                ", \nq2_id=" + q2_id +
                ", q2_answ1=" + q2_answ1 +
                ", q2_answ2=" + q2_answ2 +
                ", q2_answ3=" + q2_answ3 +
                ", q2_answ4=" + q2_answ4 +
                ", \nq3_id=" + q3_id +
                ", q3_answ1=" + q3_answ1 +
                ", q3_answ2=" + q3_answ2 +
                ", q3_answ3=" + q3_answ3 +
                ", q3_answ4=" + q3_answ4 +
                ", \nq4_id=" + q4_id +
                ", q4_answ1=" + q4_answ1 +
                ", q4_answ2=" + q4_answ2 +
                ", q4_answ3=" + q4_answ3 +
                ", q4_answ4=" + q4_answ4 +
                ", \nq5_id=" + q5_id +
                ", q5_answ1=" + q5_answ1 +
                ", q5_answ2=" + q5_answ2 +
                ", q5_answ3=" + q5_answ3 +
                ", q5_answ4=" + q5_answ4 +
                ", \nq6_id=" + q6_id +
                ", q6_answ1=" + q6_answ1 +
                ", q6_answ2=" + q6_answ2 +
                ", q6_answ3=" + q6_answ3 +
                ", q6_answ4=" + q6_answ4 +
                ", \nq7_id=" + q7_id +
                ", q7_answ1=" + q7_answ1 +
                ", q7_answ2=" + q7_answ2 +
                ", q7_answ3=" + q7_answ3 +
                ", q7_answ4=" + q7_answ4 +
                ", \nq8_id=" + q8_id +
                ", q8_answ1=" + q8_answ1 +
                ", q8_answ2=" + q8_answ2 +
                ", q8_answ3=" + q8_answ3 +
                ", q8_answ4=" + q8_answ4 +
                ", \nq9_id=" + q9_id +
                ", q9_answ1=" + q9_answ1 +
                ", q9_answ2=" + q9_answ2 +
                ", q9_answ3=" + q9_answ3 +
                ", q9_answ4=" + q9_answ4 +
                ", \nq10_id=" + q10_id +
                ", q10_answ1=" + q10_answ1 +
                ", q10_answ2=" + q10_answ2 +
                ", q10_answ3=" + q10_answ3 +
                ", q10_answ4=" + q10_answ4 +
                '}';
    }
}
