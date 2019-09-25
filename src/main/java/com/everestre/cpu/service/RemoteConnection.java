package com.everestre.cpu.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class RemoteConnection {
	
	@Value("${details.hostname}")
	private  String host;
	
	@Value("${details.username}")
	private  String username;
	
	@Value("${details.password}")
	private  String password;

	public  void remoteHostUsage() {
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
			System.out.println("=================================start");
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(
					"wmic cpu get loadpercentage   && wmic os get freephysicalmemory && wmic os get freevirtualmemory");
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
			System.out.println("=================================DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}