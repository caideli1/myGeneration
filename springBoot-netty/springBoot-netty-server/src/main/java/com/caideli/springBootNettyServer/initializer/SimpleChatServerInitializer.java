package com.caideli.springBootNettyServer.initializer;

import com.caideli.springBootNettyServer.handler.EchoServerHandler;
import com.caideli.springBootNettyServer.handler.SimpleChatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/5/7 17:20
 * 描述：
 */
@Component
@Slf4j
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private SimpleChatServerHandler simpleChatServerHandler;

@Override
public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", simpleChatServerHandler);

        System.out.println("SimpleChatClient:"+ch.remoteAddress() +"连接上");
        }
}
