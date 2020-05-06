package com.caideli.springBootNettyServer.server;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/29 19:05
 * 描述：
 */

import com.caideli.springBootNettyServer.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 服务端
 * 1.创建一个ServerBootstrap的实例引导和绑定服务器。
 * 2.创建并分配一个NioEventLoopGroup实例以进行事件的处理，比如接受连接以及读写数据。
 * 3.指定服务器绑定的本地的InetSocketAddress。
 * 4.使用一个EchoServerHandler的实例初始化每一个新的Channel。
 * 5.调用ServerBootstrap.bind()方法以绑定服务器。
 */
@Slf4j
@Component
public class EchoServer {

    /**
     * NioEventLoop并不是一个纯粹的I/O线程，它除了负责I/O的读写之外
     * 创建了两个NioEventLoopGroup，
     * 它们实际是两个独立的Reactor线程池。
     * 一个用于接收客户端的TCP连接，
     * 另一个用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等。
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    //private ChannelInboundHandlerAdapter serverHandler;
    /**
     * 启动服务
     * @param hostname
     * @param port
     * @return
     * @throws Exception
     */
    public ChannelFuture start(String hostname, int port,ChannelInboundHandlerAdapter serverHandler) throws Exception {
        //final EchoServerHandler echoServerHandler = new EchoServerHandler();
        ChannelFuture f = null;
        try {
            //ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(hostname,port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            为监听客户端read/write事件的Channel添加用户自定义的ChannelHandler
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });

            f = b.bind().sync();
            channel = f.channel();
            log.info("======EchoServer启动成功!!!=========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (f != null && f.isSuccess()) {
                log.info("Netty server listening " + hostname + " on port " + port + " and ready for connections...");
                log.info("Netty server handler name is "+serverHandler.getClass().getSimpleName());
            } else {
                log.error("Netty server start up Error!");
            }
        }
        return f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Server...");
        if(channel != null) { channel.close();}
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }


    /**
     * 拷贝的xxl_job的内容
     * @param hostname
     * @param port
     * @return
     * @throws Exception
     */
    public ChannelFuture start_netty_http_server(String hostname, int port) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelFuture future = null;

        try {
            // start server
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                                    /*ch.pipeline().addLast(new HttpResponseEncoder());
                                    ch.pipeline().addLast(new HttpRequestDecoder());*/
                            /*ch.pipeline().addLast(new ChunkedWriteHandler());*/
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(5*1024*1024));  // merge request & reponse to FULL
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // bind
            future = bootstrap.bind(port).sync();

            log.info(">>>>>>>>>>> netty http server start success, hostname = {}, port = {}", hostname, port);

            // wait util stop
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            if (e instanceof InterruptedException) {
                log.info(">>>>>>>>>>> netty http server stop.");
            } else {
                log.error(">>>>>>>>>>> netty http server error.", e);
            }
        } finally {
            try {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return future;
    }
}
