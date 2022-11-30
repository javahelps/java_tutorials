package com.javahelps.parser.pcap;


import io.pkts.Pcap;

import java.io.IOException;
import java.util.Objects;

public class Main {

    private static final String SAMPLE_FILE = Objects.requireNonNull(Main.class.getResource("/fuzz-2007-03-14-24230.pcap")).getFile();

    public static void main(String[] args) throws IOException {
        // TODO: Replace SAMPLE_FILE by the actual file you want to parse
        Pcap pcap = Pcap.openStream(SAMPLE_FILE);
        pcap.loop(new TcpUdpPacketHandler());
        pcap.close();
    }

}