package qa.projects;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class FirstTest {

 @BeforeClass
 public void beforeMethod(){
  System.out.println("First test is started.");
 }

@Test(description = "First status exec")
    public void test1(){
     boolean testStatus = true;
     System.out.println("First test status is " + testStatus);
     assert testStatus;
 }

 @AfterClass
 public void afterMethod(){
  System.out.println("First test is stopped");
 }
}
