public class AssignmentTest {
  public static void main(String[] args) {
      WordStore store = new WordStore();
      //THIS IS THE NEW VERSION - OLD VERSION USED IF,ELSE STATMENTS - DO NOT USE.
      //HashMap - modify as needed for additional testing      
      store.add('a', "Chair");
      store.add('a', "Table"); 
      store.add('B', "Lamp");
  
      //FOR TESTING: TEST B WILL FAIL INTENTIONALLY - TO PASS ALL:  CHANGE THE VALUE OF B's ITEM FROM 'Lamp' to 'Desk'
      
      String testA = store.getRandomItem('a');
      assert testA != null : "Test failed: 'a' should have words.";
      System.out.println("Test passed: 'a' has a non-null value (" + testA + ").");
      
      String testB = store.getRandomItem('b');
      assert testB != null && testB.equals("desk") : "Test failed: 'B' should return 'desk', but returned: " + testB; //THIS TEST WILL INTENTIONALLTY FAIL
      System.out.println("Test passed: 'B' returned 'desk' (" + testB + ").");
      
      String testC = store.getRandomItem('c');
      assert testC == null : "Test failed: 'c' has no words, but returned: " + testC;
      System.out.println("Test C passed correctly returned null.");

      System.out.println("All tests passed");
  }
}