package com.csy.demo.config;


import com.alibaba.fastjson.JSONObject;
import controller.dto.ActivityInitDTO;
import controller.dto.MemberCountDTO;
import controller.dto.RedisEntityDTO;
import controller.vo.ActivityVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/redis")
@Slf4j
public class RedisController {


    @Resource
    private ActivityService activityService;

    @PostMapping("/activityInit")
    public ResponseEntity<Boolean> putRedis(@RequestBody ActivityInitDTO activityInitDTO) {
        log.info("进入存放Redis信息{}", JSONObject.toJSONString(activityInitDTO));
        String valueString = JSONObject.toJSONString(activityInitDTO.getValue());
        log.info("存入Redis的Value{}",valueString);
        RedisUtil.put(activityInitDTO.getActivityId(), valueString, 86400);
        Boolean flag = activityService.putOpenId(activityInitDTO);
        return ResponseEntity.ok(flag);
    }

    @PostMapping("/getRedis")
    public ResponseEntity<ActivityRedisEntity> getRedis(@RequestBody RedisEntityDTO redisEntityDTO) {
        log.info("开始获取Redis信息===>{}", JSONObject.toJSONString(redisEntityDTO));
        String key = redisEntityDTO.getKey();
        String activityEntityJson = RedisUtil.get(key);
        ActivityRedisEntity activityEntity = JSONObject.parseObject(activityEntityJson, ActivityRedisEntity.class);
        log.info("获取到的Redis信息====>{}", JSONObject.toJSONString(activityEntity));
        return ResponseEntity.ok(activityEntity);
    }

    @PostMapping("/getActivityById")
    public ResponseEntity<ActivityRedisEntity> getActivityById(@RequestBody MemberCountDTO memberCountDTO){
        log.info("通过Id获取活动信息参数:{}",JSONObject.toJSONString(memberCountDTO));
        return ResponseEntity.ok(activityService.getActivityById(memberCountDTO));
    }

    @PostMapping("/deleteRedis")
    public void deleteRedis(@RequestBody Map<String,String> map) {
        log.info("删除Redis中的数据key为:{}", map);
        RedisUtil.delete(map.get("key"));
    }

    @PostMapping("/memberCount")
    public ResponseEntity<ActivityVO> memberCount(@RequestBody MemberCountDTO memberCountDTO) {
        log.info("进入人数计数===>{}", JSONObject.toJSONString(memberCountDTO));
        ActivityVO activityVO = activityService.memberCount(memberCountDTO);
        log.info("计数返回==>{}",JSONObject.toJSONString(activityVO));
        return ResponseEntity.ok(activityVO);
    }

    @PostMapping("/idList")
    public ResponseEntity<List<IdContentVO>> idList(@RequestBody Map<String,String> map){
        String openId = map.get("activityOpenId");
        String activityList = RedisUtil.get(openId);
        List<IdContentVO> idList = JSONObject.parseArray(activityList, IdContentVO.class);
        List<IdContentVO> removeList = new LinkedList<>();
        if (!CollectionUtils.isEmpty(idList)){
            for (IdContentVO idContentVO : idList) {
                String activityIdJson = RedisUtil.get(idContentVO.getActivityId());
                if (StringUtils.isEmpty(activityIdJson)){
                    removeList.add(idContentVO);
                }
            }
            idList.removeAll(removeList);
            RedisUtil.put(openId,JSONObject.toJSONString(idList));
        }
        return ResponseEntity.ok(idList);
    }
}
