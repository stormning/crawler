package com.slyak;

import com.dampcake.bencode.Bencode;
import com.dampcake.bencode.Type;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;

import java.nio.charset.Charset;

/**
 * .
 *
 * @author stormning 2018/2/12
 * @since 1.3.0
 */
public class DHTServerVerticle extends AbstractVerticle {

    private Charset charset = Charset.forName("iso-8859-1");

    private Bencode bencode;

    private int port;

    public DHTServerVerticle(int port) {
        this.port = port;
        this.bencode = new Bencode(charset);
    }

    @Override
    public void start() throws Exception {
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        socket.listen(port, "0.0.0.0", result -> {
            if (result.succeeded()) {
                socket.handler(packet -> {
                    bencode.decode(packet.data().getBytes(), Type.STRING);
                });
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(Math.log(4));
        System.out.println(Math.pow(2.718281828,1.3862943611198906));
    }
}
