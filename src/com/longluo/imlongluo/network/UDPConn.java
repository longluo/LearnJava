package com.imlongluo.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConn {
    private int port = 2000;
    private String rip;
    private byte[] msg = new byte[1024];
    DatagramSocket dSocket = null;
    private boolean life = true;

    public UDPConn(int p) {
        port = p;
        new Thread(run).start();
    }

    public boolean get_state() {
        return life;
    }

    // 设置send的目标IP
    public void set_rip(String ip) {
        rip = ip;
    }

    public int send(String msg) {
        try {
            if (dSocket != null) {
                InetAddress serverAddr = InetAddress.getByName(rip);
                DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), serverAddr, port);
                dSocket.send(dp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return -1;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return -2;
        } catch (IOException e) {
            e.printStackTrace();
            return -3;
        }
        return 0;
    }

    public void close() {
        life = false;
        if (dSocket != null) {
            dSocket.close();
        }
    }

    private Runnable run = new Runnable() {

        @Override
        public void run() {
            DatagramPacket dPacket = new DatagramPacket(msg, msg.length);
            try {
                dSocket = new DatagramSocket(port);
            } catch (SocketException e) {
                close();
                e.printStackTrace();
            }

            while (life) {
                try {
                    dSocket.receive(dPacket);
                    // parseData(dPacket.getData(), dPacket.getLength());
                    String s = new String(dPacket.getData(), 0, dPacket.getLength());
                } catch (IOException e) {
                    close();
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
            }
        }
    };
}
