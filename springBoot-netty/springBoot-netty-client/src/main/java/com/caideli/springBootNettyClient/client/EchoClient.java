package com.caideli.springBootNettyClient.client;

import com.caideli.springBootNettyClient.decoder.TimeDecoder;
import com.caideli.springBootNettyClient.initializer.SimpleChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private EventLoopGroup group;

    //private Bootstrap b = new Bootstrap();
    private Channel channel;

    @Autowired
    private SimpleChatClientInitializer simpleChatClientInitializer;

    public void start(String host, int port,ChannelInboundHandlerAdapter clientHandler) throws Exception{
        this.group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        startEchoClient(b,host,port,clientHandler);
            log.info("Netty client listening " + host + " on port " + port + " and ready for send");
            log.info("Netty server handler name is "+clientHandler.getClass().getSimpleName());
    }

    public void startEchoClient(Bootstrap b,String host, int port,ChannelInboundHandlerAdapter clientHandler) throws Exception {
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
                    })
                .option(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口
        this.channel = b.connect().sync().channel();
    }

    public ChannelFuture startSimpleChatClient(Bootstrap b,String host, int port) throws Exception {
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(simpleChatClientInitializer);
        //绑定端口
        ChannelFuture f = b.connect(host,port).sync();
        channel = f.channel();
        return  f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Client...");
        if (this.channel != null && this.channel.isActive()) {
            this.channel.close();        // if this.channel.isOpen()
        }
        if (this.group != null && !this.group.isShutdown()) {
            this.group.shutdownGracefully();
        }
        log.info("Shutdown Netty Client Success!");
    }

    /**
     * 客户端主动发送给服务端
     * @throws Exception
     */
    public void sendServerMsg() throws Exception{
        this.channel.writeAndFlush(Unpooled.copiedBuffer("客户端主动发送消息", CharsetUtil.UTF_8)).sync();
    }
}
