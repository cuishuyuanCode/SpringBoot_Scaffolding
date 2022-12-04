package com.csy.demo.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.csy.demo.mapper.DemoMapper;
import com.csy.demo.service.DemoService;
import com.csy.demo.web.dto.DemoDTO;
import com.csy.demo.web.vo.DemoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class DemoServiceImpl implements DemoService {


    @Resource
    private DemoMapper demoMapper;

    @Override
    public DemoVo testDemo(DemoDTO demoDTO) {
        log.info("传入参数:{}", JSONObject.toJSONString(demoDTO));
        String id = demoMapper.selectById(demoDTO.getId());
        DemoVo demoVo = new DemoVo();
        demoVo.setId(id);
        log.info("返回的信息为:{}",JSONObject.toJSONString(demoVo));
        return demoVo;
    }
}
