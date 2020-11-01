package com.it.ldq.kafkademo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @Auther: ldq
 * @Date: 2020/10/30
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class HttpServer {
    private  final int port;

    public HttpServer(int port) {
    this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
       int port = 0;
       new HttpServer(port).start();
    }

    public void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        serverBootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        log.info("innitChannel ok");
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline
                                .addLast("decoder",new HttpRequestDecoder())
                                .addLast("encoder",new HttpRequestEncoder())
                                .addLast("aggregator",new HttpObjectAggregator(512*1024))
                                .addLast("handler",new MyHttpHandler());
                    }

                })
                // 消息合并的数据大小，如此代表聚合的消息内容长度不超过512kb
                .option(ChannelOption.SO_BACKLOG,124)
                .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE);
        serverBootstrap.bind(port).sync();
    }

}
