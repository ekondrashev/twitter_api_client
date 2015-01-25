import java.util.ArrayList;
import java.util.List;


public class Jeneric {
	public static boolean newMethod(List<? extends Number> formalParam){
		
		for(Number iobj: formalParam){
			System.out.println(iobj.byteValue());
		}
		return false;
			
	}
	
	public static void writeMethod(List <? super Number> formalParam) {
		formalParam.add(new Integer(1));
	}
	public static void main(String[] args) {
		
		List rawlist = new ArrayList();
		rawlist.add(new Object());
		rawlist.add(new Integer(100));
		
		List<Integer> intList = new ArrayList();
		intList.add(new Integer(100));
		
		String[] strArray = new String[10];
		Object[] objArray = strArray;
//		objArray[0]= intList.get(0);
		
		intList = rawlist;
		int aaa = intList.get(1);
		
		newMethod(intList);
		writeMethod(rawlist);
	}

}
