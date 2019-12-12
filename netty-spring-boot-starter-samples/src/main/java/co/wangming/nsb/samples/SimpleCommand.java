package co.wangming.nsb.samples;

import co.wangming.nsb.netty.CommandController;
import co.wangming.nsb.netty.CommandMapping;
import co.wangming.nsb.samples.protobuf.Search;
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

    @CommandMapping(id = 1)
    public Search.SearchResponse search(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 1 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());

        return Search.SearchResponse.newBuilder().setResult("查询成功").build();
    }

    @CommandMapping(id = 2)
    public Search.SearchResponse search2(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 2 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());

        simpleService.print();

        return Search.SearchResponse.newBuilder().setResult("查询成功").build();
    }

    @CommandMapping(id = 3)
    public void justSearch3(Search.SearchRequest searchRequest) {
        log.info("收到SearchRequest 3 --> {}, {}, {}", searchRequest.getQuery(), searchRequest.getPageNumber(), searchRequest.getResultPerPage());
    }

    @CommandMapping(id = 4)
    public void justSearch4(Search.SearchRequest searchRequest, String nullParam) {
        log.info("收到SearchRequest 4 --> {}, {}", searchRequest.getQuery(), nullParam);

        simpleService.print();

    }

}
