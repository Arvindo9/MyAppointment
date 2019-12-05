/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.king.algo;

import java.util.Scanner;

/**
 * Created by Arvindo on 19-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 * @author KinG
 *
 */
public class CalendarYear {
    
    private int[][] day;

    public CalendarYear() {
        day = new int[7][7];
    }
    
    public int[][] calenderMonth(int year, int month) {
        int a, d;
        day = new int[7][7];
        a=2012;
        System.out.println("Enete year: " + year);
        Scanner scanner = new Scanner(System.in);
//        year = scanner.nextInt();
        System.out.println("Enete month:" + month);
//        month = scanner.nextInt();

        if (a<=year)
        {
            d = year - a;
            inc_year(d, year, month);
        }

        else
        {
            d = a - year;
            dec_year(d, year, month);

        }
        
        return day;
    }

    private void inc_year(int d, int b, int m) {
        int x=0,e=0,f=0,c=1;
        while(e != d)
        {
            if(e%4==0)
            {
                x += 1;
            }

            e++;
        }

        d += x;
        while(f != d)
        {

            c++;
            if(c>7)
            {
                c=1;
            }

            f++;
        }

        month(c,b, m);
    }

    private void dec_year(int d, int b, int m) {
        int x=0,e=1,c;
        while(e != d+1)
        {
            if(e%4==0)
            {
                x += 1;
            }
            e++;
        }

        d += x;
        c=8;
        while(d != 0)
        {
            c--;
            if(c<1)
            {
                c=7;
            }
            d--;
        }

        month(c,b, m);
    }

    private void month(int c, int b, int m) {
        
        int l=1,f=0,n;
        if(b%4==0)
        {
            f++;
        }

        while(l != m+1)
        {
            if(l==1)
            {
                if(m==1)
                {
                    break;
                }
                c--;
                n=31;
                c = cal(c, n);
            }

            if(l==2)
            {
                if(m==2)
                {
                    c++;
                    if(c>7)
                    c=1;
                    break;
                }
                n=28 + f;
                c=cal(c,n);
            }

            if(l==3)
            {
                if(m==3)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=31;
                c=cal(c,n);

            }

            if(l==4)
            {
                if(m==4)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=30;
                c=cal(c,n);
            }

            if(l==5)
            {
                if(m==5)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=31;
                c=cal(c,n);

            }

            if(l==6)
            {
                if(m==6)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=30;
                c=cal(c,n);

            }
            if(l==7)
            {
                if(m==7)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=31;
                c=cal(c,n);

            }

            if(l==8)
            {
                if(m==8)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=31;
                c=cal(c,n);

            }

            if(l==9)
            {
                if(m==9)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=30;
                c=cal(c,n);

            }

            if(l==10)
            {
                if(m==10)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=31;
                c=cal(c,n);

            }

            if(l==11)
            {
                if(m==11)
                {
                    c++;
                    if(c>7)
                    c=1;

                    break;
                }
                n=30;
                c=cal(c,n);
            }

            if(l==12)
            {
                c++;
                if(c>7)
                c=1;
            }
            l++;
        }



        display(m,f,c);
    }
    
    void display(int m,int f,int c)
    {
        int i,n,p,q;
        p=0;
        q=0;
        n=0;

        if(m==1)
        {
            n=31;
        }

        if(m==2)
        {
            n=28+f;
        }

        if(m==3)
        {
            n=31;
        }

        if(m==4)
        {
            n=30;
        }

        if(m==5)
        {
            n=31;
        }

        if(m==6)
        {
            n=30;
        }

        if(m==7)
        {
            n=31;
        }

        if(m==8)
        {
            n=31;
        }

        if(m==9)
        {
            n=30;
        }

        if(m==10)
        {
            n=31;
        }

        if(m==11)
        {
            n=30;
        }

        if(m==12)
        {
            n=31;
        }
        n += c;

        System.out.println("\n\n\n\n\n\t\tSun    Mon     Tue     Wed     Thu      Fri     Sat\n");
/*
        int x=0, y=0;
        for(i=1;i<n;i++){
            q++;
            if(c>i)
            {
                a += 8;
                y++;
            }
            else{
                p++;
                day[x][y] = p;
                System.out.println(p);
                a += 8;
                if(q>=7)
                {
                    b += 2;
                    a = 17;
                    q = 0;
                    x++;
                }
            }
        }
        */
        q = 0; p =1; int x = 0;
        for(i = 1; i < n; i++){
            if(c > i){
                q++;
            }
            else{
                System.out.println(p);
                day[x][q++] = p++;
                if(q >= 7){
                    q = 0;
                    x++;
                }
            }
        }
        int sdf =0;
    }

    int cal(int c, int n)
    {
        int f=1;
        while(f != n+1)
        {
            c++;
            if(c>7)
            {
                c=1;
            }
            f++;
        }
      return(c);
    }
}
