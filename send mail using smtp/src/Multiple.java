import java.io.*;
import java.time.LocalDateTime;
import javax.net.ssl.*;
import java.util.*;
import java.util.Scanner;

class Multiple {

    private static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String argv[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String user = "s2010576134@ru.ac.bd";
//        System.out.print("Enter your mail: ");
//        user = in.next();
//        System.out.print("Enter your pass: ");
        String pass = "password";

        String userMail =new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));
        SSLSocket ssl = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        dos = new DataOutputStream(ssl.getOutputStream());
        br = new BufferedReader(new InputStreamReader(ssl.getInputStream()));

        FileReader fr = new FileReader("mails.txt");
        Scanner sc = new Scanner(fr);
        List<String>mailList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            mailList.add(line);
        }

        for(String mail : mailList){
            System.out.println(mail);
        }

        send("EHLO smtp.gmail.com\r\n");
        for(int i=1;i<=9;i++){
            prnt();

        }

        send("AUTH LOGIN\r\n");
        prnt();

        send(userMail + "\r\n");
        prnt();

        send(password + "\r\n");
        prnt();

        for(String mail : mailList) {

            send("MAIL FROM:<" + user + ">\r\n");
            prnt();


            send("RCPT TO:<" + mail + ">\r\n");
            prnt();


            send("DATA\r\n");
            prnt();

            send("FROM: " + user + "\r\n");//change
            send("TO: " + mail + "\r\n");//change
            send("Subject: Learn sending mail to multiple users" + LocalDateTime.now() + "\r\n");
            send("THIS IS A TEST EMAIL. Please cooperate. THANK YOU\r\n");
            send(".\r\n");
            prnt();

            // Reset the session for next email
            send("RSET\r\n");
            prnt();
        }
        send("QUIT\r\n");
        prnt();

    }
    public static void prnt() throws IOException {
        System.out.println("SERVER: "+ br.readLine());
    }
    private static void send(String s) throws Exception {
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: " + s);
    }
}