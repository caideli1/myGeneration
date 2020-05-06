package com.caideli.springBootNettyServer.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/5/3 0003 15:29
 * 描述：时间服务器
 */
@Slf4j
@Component
public class EchoTimeServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 我们将会忽略任何接收到的数据，而只是在连接被创建发送一个消息，
     * 所以这次我们不能使用 channelRead() 方法了，代替他的是，我们需要覆盖 channelActive() 方法
     * 这个方法里完成一个代表当前时间的32位整数消息的构建工作
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        });
    }
}
