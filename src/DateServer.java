import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {
    public static final int PORT = 9090;
    private static String[] names= {"bob" , "Simon" , "David", "Rose", "Geralt"};
    private static String[] adjs = {"the good", "the great", "the nobel", };

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        Socket client = listener.accept();
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println((new Date()).toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        try {
            while (true) {
                String request = in.readLine();
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else {
                    out.println("Type 'tell me a name' to get a random name");
                }
            }
        } finally {
            out.close();
            in.close();
        }
    }
        public static String getRandomName () {
            String name = names[(int) (Math.random() * names.length)];
            String adj = adjs[(int) (Math.random() * adjs.length)];
            return name + " " + adj;
        }

}
