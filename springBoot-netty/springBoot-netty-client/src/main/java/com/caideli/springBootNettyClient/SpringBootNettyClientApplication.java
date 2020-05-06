package com.caideli.springBootNettyClient;

import com.caideli.springBootNettyClient.client.EchoClient;
import com.caideli.springBootNettyClient.handler.EchoClientHandler;
import com.caideli.springBootNettyClient.handler.EchoTimeClientHandler;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootNettyClientApplication implements CommandLineRunner {

	@Value("${netty.server.port}")
	private int port;

	@Value("${netty.server.url}")
	private String url;

	@Autowired
	private EchoClient echoClient;

	@Autowired
	private EchoClientHandler echoClientHandler;

	@Autowired
	private EchoTimeClientHandler echoTimeClientHandler;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNettyClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ChannelFuture future = echoClient.start(url,port,echoTimeClientHandler);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				echoClient.destroy();
			}
		});
		//服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
		future.channel().closeFuture().syncUninterruptibly();
	}
}
