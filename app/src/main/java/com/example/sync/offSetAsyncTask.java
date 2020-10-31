/*package com.example.sync;

import android.content.Context;
import android.os.AsyncTask;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DecimalFormat;

public class offSetAsyncTask extends AsyncTask<Void,Void,Double> {

    private String serverName;
    private double localClockOffset;
    private double destinationTimestamp;
    private double roundTripDelay;
    double total = 0;
    Context context;
    double avg;

    @Override
    protected Double doInBackground(Void... params) {


        getAllForMe();
        getAllForMe();
        getAllForMe();
        getAllForMe();
        getAllForMe();

        System.out.println("!!!!!!!" + total);
        avg = total/5;
        System.out.println("~~~avg. Lag: " +  avg);

        response.processFinish(avg);

        return avg;
    }

    public interface AsyncResponse{
        void processFinish(double offSet);
    }

    public AsyncResponse response = null;


    public offSetAsyncTask(AsyncResponse res, String name, Context c){
        response = res;
        serverName = name;
        context = c;
    }

    private void getAllForMe(){

        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(serverName);
            byte[] buf = new NtpMessage().toByteArray();
            DatagramPacket packet =
                    new DatagramPacket(buf, buf.length, address, 123);

            // Set the transmit timestamp *just* before sending the packet
            // ToDo: Does this actually improve performance or not?
            NtpMessage.encodeTimestamp(packet.getData(), 40,
                    (System.currentTimeMillis()/1000.0) + 2208988800.0);

            socket.send(packet);


            // Get response
            System.out.println("NTP request sent, waiting for response...\n");
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // Immediately record the incoming timestamp
            destinationTimestamp =
                    (System.currentTimeMillis()/1000.0) + 2208988800.0;


            // Process response
            NtpMessage msg = new NtpMessage(packet.getData());

            // Corrected, according to RFC2030 errata
            roundTripDelay = (destinationTimestamp-msg.originateTimestamp) -
                    (msg.transmitTimestamp-msg.receiveTimestamp);

            localClockOffset =
                    ((msg.receiveTimestamp - msg.originateTimestamp) +
                            (msg.transmitTimestamp - destinationTimestamp)) / 2;

            total+=localClockOffset;

            // Display response
            System.out.println("NTP server: " + serverName);
            System.out.println(msg.toString());

            System.out.println("Dest. timestamp:     " +
                    NtpMessage.timestampToString(destinationTimestamp));

            System.out.println("Round-trip delay: " +
                    new DecimalFormat("0.00").format(roundTripDelay*1000) + " ms");

            System.out.println("Local clock offset: " +
                    new DecimalFormat("0.00").format(localClockOffset*1000) + " ms");

            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}*/