package simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class SimpleHttpClient {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: SimpleHttpClient <url>");
            return;
        }

        try {
            /* http://www.amazon.com/index.html. */
            URL url = new URL(args[0]);
            String host = url.getHost();
            String path = url.getPath(); //index.html

            int port = url.getPort();
            if (port < 0) {
                port = 80;
            }

            String request = "GET" + path + " HTTP/1.1\n";
            request += "host: " + host;
            request += "\n\n";

            // connect to socket
            Socket sock = new Socket(host, port);

            // send out request
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            writer.print(request);
            writer.flush();

            // retrieve response
            BufferedReader reader = new BufferedReader(new InputStreamReader((sock.getInputStream())));
            String nextRecord = null;
            while ((nextRecord = reader.readLine()) != null) {
                System.out.println(nextRecord);
            }

            sock.close();
        } catch(MalformedURLException e) {
            throw new RuntimeException("Please try again. Bad URL.\n" + e);
        } catch(UnknownHostException e) {
            throw new RuntimeException("Please try again. Unknown host.\n" + e);
        } catch(IOException e) {
            throw new RuntimeException("Please try again. Something's wrong.\n" + e);
        }




    }
}
