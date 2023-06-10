package qa.projects;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SecondTest {

 @BeforeClass
 public void beforeMethod(){
  System.out.println("Second test is started.");
 }

@Test(description = "Secondt status exec")
    public void test1(){
     boolean testStatus = true;
     System.out.println("Second test status is " + testStatus);
     assert testStatus;
 }

 @AfterClass
 public void afterMethod(){
  System.out.println("Second test is stopped");
 }
}
