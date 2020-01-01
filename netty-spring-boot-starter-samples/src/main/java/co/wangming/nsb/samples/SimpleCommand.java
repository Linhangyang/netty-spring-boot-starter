package co.wangming.nsb.samples;

import co.wangming.nsb.command.CommandController;
import co.wangming.nsb.command.CommandMapping;
import co.wangming.nsb.samples.protobuf.Search;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created By WangMing On 2019-12-07
 **/
@Slf4j
@CommandController
public class SimpleCommand {

    @Autowired
    private SimpleService simpleService;

    @CommandMapping(requestId = 1)
    public Search.SearchResponse search(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 1 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());

        return Search.SearchResponse.newBuilder().setResult("查询成功").build();
    }

    @CommandMapping(requestId = 2)
    public Search.SearchResponse search2(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 2 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());

        simpleService.print();

        return Search.SearchResponse.newBuilder().setResult("查询成功").build();
    }

    @CommandMapping(requestId = 3)
    public void justSearch3(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 3 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());
    }

    @CommandMapping(requestId = 4)
    public void justSearch4(Search.SearchRequest searchRequest, String nullParam) {
        log.info("收到SearchRequest 4 --> {}, {}", searchRequest.getQuery(), nullParam);

        simpleService.print();
    }

    @CommandMapping(requestId = 5)
    public void justSearch5(Search.SearchRequest searchRequest, ChannelHandlerContext ctx) {
        log.info("收到SearchRequest 5 --> {}, {}", ctx.channel().remoteAddress(), searchRequest.getQuery());
    }

    @CommandMapping(requestId = 6)
    public void justSearch6(Search.SearchRequest searchRequest, ChannelHandlerContext ctx) {
        log.info("收到SearchRequest 6 --> {}, {}", ctx.channel().remoteAddress());
        throw new RuntimeException();
    }

    @CommandMapping(requestId = 7)
    public void justSearch7(Search.SearchRequest searchRequest, User user) {
        log.info("收到SearchRequest 7 --> {}", user.getChannelHandlerContext().channel().remoteAddress());
    }
}
