/**
 * Project 5
 * @author Rajat Srivastava, srivastr, Y01
 * @author David Shin-Bae, dshinbae, L04
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringJoiner;

public class SafeWalkServer extends ServerSocket implements Runnable {
    
    static final String[] locations = { "CL50", "EE", "LWSN", "PMU", "PUSH", "*" };
    private static ArrayList<Request> listRequests = new ArrayList<Request>();
    static Request temp2;
    
    // CONSTRUCTOR WITH PORT
    public SafeWalkServer(int port) throws IOException {
        super(port);
        setReuseAddress(true); // Makes reusing address possible
    }
    
    // CONSTRUCTOR WITHOUT PORT
    public SafeWalkServer() throws IOException {
        super(0);
        setReuseAddress(true);
    }
    
    // MAIN METHOD
    public static void main(String[] args) throws IOException {
        if (args.length == 0) { // WORKS PERFECT DO NOT CHANGE
            SafeWalkServer s1 = new SafeWalkServer();
            System.out.println("No Port specified. Now using default port: "
                                   + s1.getLocalPort());
            s1.run();
        } else if (!SafeWalkServer.isPortValid(args[0])) {
            System.out
                .println("Port Number is Invalid. Input number between 1025 and 65535 inclusive");
            return;
        } else {
            SafeWalkServer s2 = new SafeWalkServer(Integer.parseInt(args[0]));
            s2.run();
        }
    }
    
    // RUN() of SafeWalkServer class
    public void run() {
        while (true) {
            try {
                Socket client = accept(); // Accepts the connection
                temp2 = new Request(client); // Creates new Request class object
                temp2.run(); // Calls Request class for each client/connection
                if (temp2.shutdown) {
                    return;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    
    // isPortValid METHOD
    public static boolean isPortValid(String port) {
        int portNum;
        try {
            portNum = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }
        if (portNum < 1025 || portNum > 65535) {
            return false;
        } else
            return true;
    }
    
    
    // Returns true if there is a match else returns false
    public static boolean matching(Request a, Request b) {
        // a = Current Client (this)
        // b = List of Requests (get)
        
        if (a.getTo().equals(locations[5])) {
            if (!b.getTo().equals(locations[5]) && a.getFrom().equals(b.getFrom())) {
                return true;
            }
        }
        else {
            if (a.getFrom().equals(b.getFrom())) {
                if (a.getTo().equals(b.getTo())) {
                    return true;
                }
                else if (b.getTo().equals(locations[5])) {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    // Put outside the Request inner on 04/20/2015 as TA suggested
    // WORKS PERFECT DO NOT CHANGE
    public void reset() {
        for (int i = 0; i < listRequests.size(); i++) {
            listRequests.get(i).pw.println("ERROR: connection reset");
            try { 
                listRequests.get(i).sock.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        listRequests.clear();
        temp2.pw.println("RESPONSE: success");
        try {
            temp2.sock.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
    
    public static boolean errorRequest(String from, String to) {
        // Checks from and to are from locations array. DOESN"T WORK :(
        if (from.equals(locations[5]) || to.equals(from)) {
            return true;
        }
        int counterFrom = 0;
        for (int i = 0; i < locations.length; i++){
            if (!from.equals(locations[i])) {
                counterFrom++;
            }
        }
        if (counterFrom == locations.length) {
            return true;
        }
        
        int counterTo = 0;
        for (int i = 0; i < locations.length; i++){
            if (!to.equals(locations[i])) {
                counterTo++;
            }
        }
        if (counterTo == locations.length) {
            return true;
        }
        
        return false;
    }
    
    // Creates "REQUEST" class request inside the SafeWalkServer class
    class Request {
        private BufferedReader br;
        private PrintWriter pw;
        private Socket sock;
        private String name;
        private String task;
        private String from;
        private String to;
        private String[] store;
        boolean requestMessage = false;
        boolean reset = false;
        boolean shutdown = false;
        boolean pending = false;
        StringJoiner strang = new StringJoiner(", ","[","]");
        
        
        // Constructor for Request Class
        Request(Socket sock) throws IOException {
            
            this.sock = sock;
            this.br = new BufferedReader(new InputStreamReader(
                                                               this.sock.getInputStream()));
            this.pw = new PrintWriter(this.sock.getOutputStream(), true);
            this.store = br.readLine().split(",");
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getFrom() {
            return this.from;
        }
        
        public String getTo() {
            return this.to;
        }
        
        // Run() for Request Class
        public void run() throws IOException {
            
            // REQUESTS AND COMMANDS
            if (store[0].charAt(0) == ':') {
                if (store[0].equals(":RESET")) {
                    reset();
                }
                else if (store[0].equals(":SHUTDOWN")) {
                    reset();
                    shutdown = true;
                }
                
                // Pending Request Method (99% Completed) (Left over is the final output format) 
                else if (store[0].equals(":PENDING_REQUESTS")) {
                    this.task = store[1];
                    this.from = store[2];
                    this.to = store[3];
                    if (this.task.equals("#")) {
                        if (this.from.equals("*")) {
                            if (this.to.equals("*")) {
                                pw.println("Total number of pending requests = " + listRequests.size());
                            }
                            else {
                                int sumTo = 0;
                                for (int i = 0; i < listRequests.size(); i++) {
                                    if (this.to.equals(listRequests.get(i).getTo())){
                                        sumTo ++; // Counts the number of Time getTo matches with this.to
                                        
                                    }
                                }
                                pw.println("# of pending request to " + this.to + " = " + sumTo);
                            }
                        }
                        else {
                            int sumFrom = 0;
                            for (int i = 0; i < listRequests.size(); i++) {
                                if (this.from.equals(listRequests.get(i).getFrom())){
                                    sumFrom++; // Counts the number of times this.from matches getFrom
                                    
                                }
                            }
                            pw.println("# of pending requests from " + this.from + " = " + sumFrom);
                        }
                    }
                    else if (this.task.equals("*") && this.from.equals("*") && this.to.equals("*")) {
                        
                        for (int i = 0; i < listRequests.size(); i++) {
                            
                            strang.add(("["+listRequests.get(i).getName()+", " +listRequests.get(i).getFrom()+", "+listRequests.get(i).getTo()+"]"));
                            
                            
                            // @DAVID: Your new stuff goes here!
                            
                            
                        }
                        pw.println(strang.toString());
                        
                    }
                } 
                else {
                    pw.println("ERROR: invalid command");
                }
                sock.close();
            }
            
            else if (store.length == 3) {
                this.name = store[0];
                this.from = store[1];
                this.to = store[2];
                
                // Check for typo's from user
                if (errorRequest(this.from, this.to)) {
                    pw.println("ERROR: invalid request");
                    return;
                }
                
                // Checks if from = * or to = from, and gives error. Works! :)
                if (this.from.equals(locations[5]) || this.to.equals(this.from)) {
                    pw.println("ERROR: invalid request");
                    return;
                } 
                
                // Now that there are no errors, add it to the ArrayList of Requests
                listRequests.add(temp2);
                
                // Call the matching method done outside earlier
                // Checks if true, sends the clients each other's information, else adds the current client in the list of arrays
                for (int i = 0; i < listRequests.size() - 1; i++) { // Checks all Requests except the one currently added
                    if (matching(temp2, listRequests.get(i))) {
                        pw.println("RESPONSE: "+ listRequests.get(i).getName()+ ","+ listRequests.get(i).getFrom() + ","+ listRequests.get(i).getTo());
                        listRequests.get(i).pw.println("RESPONSE: " + this.name + "," + this.from + "," + this.to);
                        listRequests.remove(listRequests.size()-1); // Removing current client
                        listRequests.remove(i);  // Removing matched client
                    }
                }
            }
        }
    }
}
