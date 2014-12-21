import java.util.ArrayList;
import java.util.List;



public class TemporaryClass {

	public static boolean newMethod(List<? extends Number> formalParam)
	{
		for (Number iobj: formalParam)
			System.out.println(iobj.byteValue());
		
		return (Math.random() > 0.5)? true:false;
	}

	public static <T> T max(T x, T y) {
        return x > y ? x : y;
    }	
	
	public static boolean writeMethod(List<? super Number> formalParam)
	{
		formalParam.add(new Integer(1));
		formalParam.add(new Long(1));
		
		return (Math.random() > 0.5)? true:false;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		List<Number> rawList = new ArrayList<Number>();
//		rawList.add(new Object());
		rawList.add(42);
		
		List<Integer> intList = new ArrayList<Integer>(); 
		intList.add(42);
		
		String[] strArray = new String[10];
		Object[] objArray = strArray;
//		objArray[0] = intList.get(0);
		
		int i = intList.get(0);
		
		newMethod(intList);
		writeMethod(rawList);
	}
}
