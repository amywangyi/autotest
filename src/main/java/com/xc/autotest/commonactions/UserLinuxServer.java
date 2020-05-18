package com.xc.autotest.commonactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
/**
 * @description: get s_id
 * @name: UserLinuxServer.java
 * @author: heheda
 * @date: 21:06 2019/04/11
 **/
public class UserLinuxServer {
	private String userLinuxHost;
	private String userLinuxName;
	private String userLinuxPassWord;

	public UserLinuxServer(final String userLinuxHost, final String userLinuxName, final String userLinuxPassWord) {
		this.userLinuxHost = userLinuxHost;
		this.userLinuxName = userLinuxName;
		this.userLinuxPassWord = userLinuxPassWord;
	}
	

	public String getUserLinuxHost() {
		return this.userLinuxHost;
	}
	public String getUserLinuxName() {
		return this.userLinuxName;
	}

	public String getUserLinuxPassWord() {
		return this.userLinuxPassWord;
	}
	
	private String sshSid() {
		String s_id = "";
		String hostname =getUserLinuxHost();
		String username = getUserLinuxName();
		String password = getUserLinuxPassWord();

		Connection conn = new Connection(hostname);
		Session ssh = null;
		
		try {
			conn.connect();
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				System.out.println("username or password is wrong.");
			} else {
				System.out.println("connect user linux server is Ok.");
				ssh = conn.openSession();
				ssh.execCommand("cd /opt/ca-b2b/ca-user; sh sid.sh");
				InputStream is = new StreamGobbler(ssh.getStdout());
				BufferedReader brs = new BufferedReader(new InputStreamReader(is));
				// only read one line
				s_id = brs.readLine();
				brs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ssh.close();
			conn.close();
		}
		return s_id;
	}
	
	private String sshGoodsAuditTaskId() {
		String taskId = "";
		String hostname =getUserLinuxHost();
		String username = getUserLinuxName();
		String password = getUserLinuxPassWord();

		Connection conn = new Connection(hostname);
		Session ssh = null;
		
		try {
			conn.connect();
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				System.out.println("username or password is wrong.");
			} else {
				System.out.println("connect user linux server is Ok.");
				ssh = conn.openSession();
				ssh.execCommand("cd /opt/ca-b2b/ca-user; sh getGoodsTaskID.sh");
				InputStream is = new StreamGobbler(ssh.getStdout());
				BufferedReader brs = new BufferedReader(new InputStreamReader(is));
				// only read one line
				taskId = brs.readLine();
				brs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ssh.close();
			conn.close();
		}
		return taskId;
	}
	
	private String sshPhoneCode() {
		String phoneCode = "";
		String hostname =getUserLinuxHost();
		String username = getUserLinuxName();
		String password = getUserLinuxPassWord();

		Connection conn = new Connection(hostname);
		Session ssh = null;
		
		try {
			conn.connect();
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				System.out.println("username or password is wrong.");
			} else {
				System.out.println("connect user linux server is Ok.");
				ssh = conn.openSession();
				ssh.execCommand("cd /opt/ca-b2b/ca-user; sh getPhoneCode.sh");
				InputStream is = new StreamGobbler(ssh.getStdout());
				BufferedReader brs = new BufferedReader(new InputStreamReader(is));
				// only read one line
				phoneCode = brs.readLine();
				brs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ssh.close();
			conn.close();
		}
		return phoneCode;
	}
	
	
	public String getSid() {
		return sshSid();
	}
	
	public String getTaskId() {
		return sshGoodsAuditTaskId();
	}
	
	public String getPhoneCode() {
		return sshPhoneCode();
	}
}
