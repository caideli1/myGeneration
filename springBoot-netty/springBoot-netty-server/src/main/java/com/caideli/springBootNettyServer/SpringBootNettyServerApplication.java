package com.caideli.springBootNettyServer;

import com.caideli.springBootNettyServer.handler.EchoServerHandler;
import com.caideli.springBootNettyServer.handler.EchoTimeServerHandler;
import com.caideli.springBootNettyServer.server.EchoServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootNettyServerApplication implements CommandLineRunner {

	@Value("${netty.port}")
	private int port;

	@Value("${netty.url}")
	private String url;

	@Autowired
	private EchoServer echoServer;

	@Autowired
	private EchoServerHandler echoServerHandler;

	@Autowired
	private EchoTimeServerHandler echoTimeServerHandler;
//	@Autowired
//	private EchoServer echoServer;
//	@Autowired
//	private EchoServer echoServer;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNettyServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ChannelFuture future = echoServer.start(url,port,echoTimeServerHandler);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				echoServer.destroy();
			}
		});
		//服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
		future.channel().closeFuture().syncUninterruptibly();
	}

}
