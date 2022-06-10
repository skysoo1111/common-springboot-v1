package com.study.common.springbootv1.controller;

import com.study.common.springbootv1.domain.Channel;
import com.study.common.springbootv1.service.ChannelService;
import com.study.common.springbootv1.utility.Globals;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


/*
* 1. 매개변수를 Map에 담아서 넘겨주는 것을 지양한다.
* 2. 매개변수는 확장성을 고려해 클래스 타입보다 인터페이스 타입을 지향한다.
* 3. 매개변수는 3개 이상을 넘지 않는 것을 지향한다.
*
* */
@RequiredArgsConstructor
@RequestMapping("/channel")
@RestController
public class ChannelController {
    private final ChannelService channelService;
    /*
    * PIP 채널 정보 전체 입수
    * */
    @GetMapping(value = "/all" ,produces = "application/json")
    public List<Channel> channelInfoAll() throws URISyntaxException {
        List<Channel> channels = channelService.callPipApiList(new URIBuilder(Globals.API_URL)
                                                                .addParameter("type", "all")
                                                                .build());
        return channels;
    }

    /*
     * PIP 채널 정보 최근 입수
     * */
    @GetMapping("/recent")
    public List<Channel> channelInfoRecent(@RequestParam("start_dt") String start_dt
                                            ,@RequestParam("end_dt") String end_dt) throws URISyntaxException {
        List<Channel> channels = channelService.callPipApiList(new URIBuilder(Globals.API_URL)
                                                        .addParameter("type", "recent")
                                                        .addParameter("start_dt", start_dt)
                                                        .addParameter("end_dt", end_dt)
                                                        .build());
        return channels;
    }

    /*
     * PIP 채널 정보 단건 입수
     * */
    @GetMapping("/single/{ch_id}")
    public List<Channel> channelInfoById(@PathVariable("ch_id") String chId) throws URISyntaxException {
        List<Channel> channel = channelService.callPipApiList(new URIBuilder(Globals.API_URL)
                                                        .addParameter("ch_id", chId)
                                                        .build());
        return channel;
    }
}
