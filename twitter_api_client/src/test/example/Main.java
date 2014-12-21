package test.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ITschool3 on 12.11.2014.
 */
public class Main {

    private static final String USAGE = "Usage: Main --cmd=<os_cmd>";


    //--param=pamparam --dasdsa das  das dsa --cmd=dir --finalargument --cmd=ver
    /*
    [   "--param=pamparam",
        "--dasdsa",
        "das",
        "das",
        "dsa",
        "--cmd=dir",
        "--finalargument",
        "--cmd=ver"]
     */
    public static void main(String args[]) {
    	

    }
    
    public ArrayList<String> parseCommands(String args[])
    {
        String cmd = null;
        String cmdSignature = "--cmd=";        
    	
        for (String arg: args)
        {
            if (arg.startsWith(cmdSignature))
                {
                    cmd = arg.substring(cmdSignature.length(), arg.length());
                    try {
                        executeCmd(cmd);
                    } catch (InterruptedException e)
                    {

                    }
                }
        }
		return null;
        
    }
    
    private static void executeCmd(String cmd) throws InterruptedException {
         Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec(new String[]{cmd});
                process.waitFor();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                while ((line = bReader.readLine()) != null){
                    System.out.println(line);
                }
                bReader.close();
            } catch (IOException e) {
                System.out.println("Command execution failed");
                e.printStackTrace();
            }
        }

    private static void method1(){
        throw new NullPointerException();
    }
}