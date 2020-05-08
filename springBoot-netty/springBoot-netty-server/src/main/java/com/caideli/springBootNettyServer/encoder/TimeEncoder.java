package com.caideli.springBootNettyServer.encoder;

import com.caideli.springBootNettyServer.model.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/5/7 15:46
 * 描述：
 */
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        out.writeInt((int)msg.value());
    }
}
