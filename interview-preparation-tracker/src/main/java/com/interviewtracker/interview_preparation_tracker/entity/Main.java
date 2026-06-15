package com.interviewtracker.interview_preparation_tracker.entity;
import java.util.ArrayList;
public class Main {
    public static void main(String[]args) {
        ArrayList<user> L = new ArrayList<>();
        ArrayList<Problem>l=new ArrayList<>();
        user u1 = new user();
        u1.setId(123);
        u1.setName("Dharshini");
        u1.setEmail("Dharsh78@gmail.com");
        u1.setPassword("dhars23!");
        L.add(u1);
        Problem p1=new Problem();
        p1.setProblemno(23);
        p1.setProbelmName("Two sum");
        p1.setAlgorithm("Two pointer");
        p1.setLevel("Easy");
        p1.setItInterview(true);
        p1.setTime(1);
        String loginemail = "Dharsh78@gmail.com";
        String loginame = "Dharshini";
        String loginpassword = "dharsh23!";
        l.add(p1) ;
        int num=23;
        String name="Two sum";
        String algo="Two pointer";
        Boolean b=true;
        for(Problem p:l)
        {
            System.out.println(p.getProblemName());
            System.out.println(p.getProblemno());
            System.out.println(p.getAlgorithm());
        }
        for (user n : L) {
            if (n.getEmail().equals(loginemail)&& n.getPassword().equals(loginpassword)){
                //System.out.println(n.getId());
                //System.out.println(n.getName());
                System.out.println(n.getEmail());
                System.out.println(n.getPassword());
                break;

            } else {
                System.out.println("Invalid");
                continue;
            }
        }
        for(Problem h : l) {
            if(h.getProblemno() == 23) {
                h.setLevel("Medium");
                break;
            }
        }
        for(Problem h:l)
        {
            if(h.getProblemno()==23);
            {
                l.remove(h);
                break;
            }
        }
    }
}
