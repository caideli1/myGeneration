package com.caideli.springBootNettyClient.client;

import com.caideli.springBootNettyClient.decoder.TimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/29 19:37
 * 描述：
 */
/**
 * 客户端
 * 1.为初始化客户端，创建一个Bootstrap实例
 * 2.为进行事件处理分配了一个NioEventLoopGroup实例，其中事件处理包括创建新的连接以及处理入站和出站数据；
 * 3.当连接被建立时，一个EchoClientHandler实例会被安装到（该Channel的一个ChannelPipeline中；
 * 4.在一切都设置完成后，调用Bootstrap.connect()方法连接到远程节点。
 */
@Slf4j
@Component
public class EchoClient {

    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(String host, int port,ChannelInboundHandlerAdapter clientHandler) throws Exception {

        /**
         * Netty用于接收客户端请求的线程池职责如下。
         * （1）接收客户端TCP连接，初始化Channel参数；
         * （2）将链路状态变更事件通知给ChannelPipeline
         */
        ChannelFuture f = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(clientHandler);
                            //基于流的传输方法，平均拆分，累计缓冲的方式
                            //socketChannel.pipeline().addLast(new TimeDecoder(),clientHandler);
                        }
                    });
            //绑定端口
            f = b.connect().sync();
            channel = f.channel();
            //f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (f != null && f.isSuccess()) {
                log.info("Netty client listening " + host + " on port " + port + " and ready for send");
                log.info("Netty server handler name is "+clientHandler.getClass().getSimpleName());
            } else {
                log.error("Netty client start up Error!");
            }
        }
        return f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Client...");
        if(channel != null) { channel.close();}
        group.shutdownGracefully();
        log.info("Shutdown Netty Client Success!");
    }
}
