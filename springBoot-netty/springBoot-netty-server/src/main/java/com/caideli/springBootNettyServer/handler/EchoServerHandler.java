package com.caideli.springBootNettyServer.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/29 19:09
 * 描述：服务端自定义业务处理handler
 */
@Component
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

    /**
     * 对每一个传入的消息都要调用；
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received: "+in.toString(CharsetUtil.UTF_8));
        channelMap.put(in.toString(CharsetUtil.UTF_8),ctx.channel());

        ctx.write(Unpooled.copiedBuffer("我是server端发送消息过来了!", CharsetUtil.UTF_8));
    }

    /**
     * 主动发送客户端
     */
    public void sendClientMsg(){
        channelMap.get("我是client端发送消息过来了!").writeAndFlush("我主动调用你了！");
    }


    /**
     * 通知ChannelInboundHandler最后一次对channelRead()的调用时当前批量读取中的的最后一条消息。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 在读取操作期间，有异常抛出时会调用。
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
