package com.mancity.social.user.presentation;

import com.mancity.social.user.application.dto.request.UserPlusRequestDto;
import com.mancity.social.user.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eureka-client-user", url="http://j10c201.p.ssafy.io:8081")
public interface UserFeignClient {

    @GetMapping("/api/user/{id}")
    public UserResponseDto findById(@PathVariable("id") Long id);

    @PostMapping("/api/user/stat/plus")
    public void plus(@RequestBody UserPlusRequestDto dto);

//    TODO : 알람요청 API 작성 필요
//    @PostMapping("/api/user/alarm")
//    public void alarm(@RequestBody AlarmRequestDto dto);
}
