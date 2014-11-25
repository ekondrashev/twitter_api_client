import java.util.HashMap;
import java.util.Map;

import org.junit.Test;



public class TwMainTest {

	@Test
	public void testMain() {
		String testArgs[]  = {"-UsID=Igor1578","-GtSt=788798","-PtSt=Новый пост"}; // аналог параметров коммандной строки
		
		Map<String, String> testCommands = new HashMap<String, String>(); //создаем эталонный map
		testCommands.put("ArgUsID","Igor1578");
		testCommands.put("ArgGtSt","788798");
		testCommands.put("ArgPtSt","Новый пост");
		Map<String, String> Result = TwMain.ParcingArg(testArgs); //получаем map из массива параметров
		boolean mapsAreEqual = testCommands.equals(Result); //сравниваем
		if(mapsAreEqual){
			System.out.println("Тест выполнен!!!");
		}
		else {
			System.out.println("Тест провален!!!");
		}
		
		    
		
	}

}
