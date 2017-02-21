package com.pradipta.client.soap.generic;

import java.io.IOException;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;

import java.util.Map;

public class SOAPCaller {
    public SOAPCaller() {
        super();
    }

    public LoginResponseContent getLoginDetails(String username, String password) throws MalformedURLException, IOException {

        String responseString = "";
        String outputString = "";
        String wsURL = "https://ws5.responsys.net/webservices/services/ResponsysWSService";
        //String wsURL = "https://www.google.co.in/?gfe_rd=cr&ei=EgSrWJn-FsKL8Qez8KGACw";
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpsURLConnection  httpConn = (HttpsURLConnection) connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput =
            " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:ws.rsys.com\">\n" + 
            "   <soapenv:Header/>\n" + 
            "   <soapenv:Body>\n" + 
            "      <urn:login>\n" + 
            "         <urn:username>" + username + "</urn:username>\n" + 
            "         <urn:password>" + password + "</urn:password>\n" + 
            "      </urn:login>\n" + 
            "   </soapenv:Body>\n" + 
            "</soapenv:Envelope>";

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        String SOAPAction = "";
        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        //Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
        //Ready with sending the request.

        //Read the response.
        InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        while ((responseString = in.readLine()) != null) {
        outputString = outputString + responseString;
        }
        System.out.println(outputString);
        Map httpHeaders = (Map) httpConn.getHeaderFields();
        System.out.println("Printing for httpHeaders " + httpHeaders.size());
        System.out.println(httpHeaders.get("Set-Cookie").toString());
        for (Object key : httpHeaders.keySet()) {
            if((key != null))
            {
                System.out.println("\t\tKey : " + key.toString());
            }
        }
        LoginResponseContent loginResponseContent = new LoginResponseContent();
        loginResponseContent.cookie="";
        loginResponseContent.sessionId="";
        
        return loginResponseContent;
    }
    
    
    public LoginResponseContent helloWorldStatefulSOAPService(String name) throws MalformedURLException, IOException {

        String responseString = "";
        String outputString = "";
        String wsURL = "http://oa8066.us.oracle.com:9001/HelloWorldJava-StatefulSOAPService-context-root/StatefulServiceClassPort";
        //String wsURL = "https://www.google.co.in/?gfe_rd=cr&ei=EgSrWJn-FsKL8Qez8KGACw";
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpsURLConnection  httpConn = (HttpsURLConnection) connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.stateful.pradipta.com/\">\n" + 
            "   <soapenv:Header/>\n" + 
            "   <soapenv:Body>\n" + 
            "      <soap:HelloWorld>\n" + 
            "         <!--Optional:-->\n" + 
            "         <arg0>"+ name +"</arg0>\n" + 
            "      </soap:HelloWorld>\n" + 
            "   </soapenv:Body>\n" + 
            "</soapenv:Envelope>";

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        String SOAPAction = "";
        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        //Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
        //Ready with sending the request.

        //Read the response.
        InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        while ((responseString = in.readLine()) != null) {
        outputString = outputString + responseString;
        }
        System.out.println(outputString);
        Map httpHeaders = (Map) httpConn.getHeaderFields();
        System.out.println("Printing for httpHeaders ");
        for (Object key : httpHeaders.keySet()) {
            System.out.println("\t\tKey : " + key.toString() + " Value : " + httpHeaders.get(key));
        }
        LoginResponseContent loginResponseContent = new LoginResponseContent();
        loginResponseContent.cookie="";
        loginResponseContent.sessionId="";
        
        return loginResponseContent;
    }
    
    
    public static void main(String[] args){
        try
        {    
            System.out.println("start");
            SOAPCaller soapCaller = new SOAPCaller();
            System.out.println("mid");
            soapCaller.getLoginDetails("MI_WS", "WSpassword1*");
            System.out.println("end");
            
            System.out.println("start");
            SOAPCaller soapCaller1 = new SOAPCaller();
            System.out.println("mid");
            soapCaller1.helloWorldStatefulSOAPService("aaa");
            System.out.println("end");
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
