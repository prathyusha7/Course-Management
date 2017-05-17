import java.sql.*;
import java.io.*;
import java.util.Scanner;
class ASSIGN_2 {
public static void main (String args [])
throws SQLException, IOException {
// Load Oracle’s JDBC Driver
try {
Class.forName
("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
System.out.println ("Could not load the driver");
}
String user,sem,year, pass,ch,num_check;
int num;
Scanner l = new Scanner(System.in);
System.out.println("enter uname");
user = l.nextLine();
System.out.println("enter pass");
pass = l.nextLine();
Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@csoracle.utdallas.edu:1521:student",user,pass);

System.out.println("student number:");
num=l.nextInt();


num_check="select * from STUDENT where student_number=?";
PreparedStatement p=conn.prepareStatement(num_check);
p.setInt(1,num);
ResultSet r=p.executeQuery();


if(r.next())
{
Scanner l1=new Scanner(System.in);
System.out.println("semester:");
sem=l1.nextLine();
System.out.println("year:");
year=l1.nextLine();
ch="y";
while(ch.equals("y"))
{
System.out.println("MAIN MENU");
Scanner l3=new Scanner(System.in);
System.out.println("(1)Add a class (2)Drop a class  (3)see my schedule  (4)EXIT");
int a=l3.nextInt();

switch (a) {
        case 1:
	Scanner l4=new Scanner(System.in);
System.out.println("course number:");
String c_num=l4.nextLine();
System.out.println("section:");
int sec_num=l4.nextInt();
String grade="U";

try
{
String sql = "INSERT INTO GRADE_REPORT values(?,?,?)";
PreparedStatement s = conn.prepareStatement(sql);
s.setInt(1,num);
s.setInt(2,sec_num);
s.setString(3,grade);
s.executeUpdate();
System.out.println("class added");
}
catch(SQLException e){
System.out.println("You have already registered for this course");
}
break;
case 2:
Scanner l5=new Scanner(System.in);
System.out.println("course number:");
String c_num1=l5.nextLine();
System.out.println("section:");
int sec_num1=l5.nextInt();
try
{	
String drop_cls="delete from GRADE_REPORT where student_number=? and section_identifier=? and grade='U'";
PreparedStatement stmt=conn.prepareStatement(drop_cls);
stmt.setInt(1,num);
stmt.setInt(2,sec_num1);
stmt.executeUpdate();
System.out.println("class dropped");
}
catch(SQLException e){
System.out.println(e);
}
break;
case 3:
try
{
System.out.println("Your current schedule is:");
String schedule="select c.course_name,s.course_number,s.instructor,g.grade from COURSE c,SECTION s,GRADE_REPORT g where s.section_identifier=g.section_identifier and c.course_number=s.course_number and g.student_number=?";
PreparedStatement p1=conn.prepareStatement(schedule);
p1.setInt(1,num);
ResultSet r1=p1.executeQuery();
if(r1.next())
{
String c_name=r1.getString(1);
String c_num2=r1.getString(2);
String instructor=r1.getString(3);
String gr=r1.getString(4);

System.out.println("Course name:" +c_name);
System.out.println("Course Number:"+c_num2);
System.out.println(" instructor:"+instructor);
System.out.println("Grade:"+gr);
}
}catch(Exception e)
{
System.out.println(e);
}
break; 
case 4:
break;
}
System.out.println("enter y to continue");
Scanner l6 = new Scanner(System.in);
ch=l6.nextLine();

}


}
else 
{
System.out.println("student id does not exist");
}

}
}

