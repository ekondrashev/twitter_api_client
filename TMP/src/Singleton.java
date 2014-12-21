public class Singleton<T> {

    public static T getInstance() {
        if (instance == null)
            instance = new Singleton<T>();

        return instance;
    }

    public static void main(String[] args) {
    	String s = Singleton<String>.getInstance();
    	Double s = Singleton<Double>.getInstance();
    }
    private static T instance = null;
}