package com.gcs.api.web.agent.api;

import com.gcs.api.domain.mavlink.service.MavLinkParamService;
import com.gcs.api.web.agent.dto.AgentParamResponse;
import com.gcs.api.web.agent.dto.AgentParamsResponse;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.supporter.mavlink.param.ParameterKeyDto;
import com.gcs.supporter.util.api.ApiUtil;
import com.gcs.supporter.util.api.ApiUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/param")
public class AgentParamApiController {
    private final MavLinkParamService mavLinkParamService;
    private final AgentService agentService;

    @GetMapping("/key")
    public ResponseEntity<ApiResult<List<ParameterKeyDto>>> droneShowParamList(){
        return ResponseEntity.ok(
                ApiUtil.success(
                        mavLinkParamService.droneShowParamKeyList()
                )
        );
    }

    @GetMapping("/{sysid}")
    public ResponseEntity<Void> requestParam(@PathVariable int sysid){
        mavLinkParamService.paramList(sysid);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/ds/{sysid}")
    public ResponseEntity<Void> requestDroneShowParam(@PathVariable int sysid){
        mavLinkParamService.droneShowParamList(sysid);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @GetMapping("/ds/{sysid}")
    public ResponseEntity<ApiResult<AgentParamsResponse>> getParams(@PathVariable int sysid){
        return ResponseEntity
                .ok(ApiUtil.success(
                        new AgentParamsResponse(sysid,
                            agentService.readParameter(sysid).stream()
                                    .map(AgentParamResponse::new)
                                    .collect(Collectors.toList())
                        )
                ));
    }

    @GetMapping
    public ResponseEntity<Void> requestParam(){
        mavLinkParamService.paramList();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


    @PatchMapping("/{sysid}/{paramKey}")
    public ResponseEntity<Void> patchParam(
            @PathVariable int sysid,
            @PathVariable String paramKey,
            @RequestParam String val
    ){
        if(val.matches("\\d+")){
            mavLinkParamService.setParam(sysid, paramKey, Integer.parseInt(val));
        } else if(val.matches("\\d+.\\d+")){
            mavLinkParamService.setParam(sysid, paramKey, Float.parseFloat(val));
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }



    @PatchMapping("/{paramKey}")
    public ResponseEntity<Void> patchParam(
            @PathVariable String paramKey,
            @RequestParam String val
    ){
        if(val.matches("\\d+")){
            mavLinkParamService.setParam(paramKey, Integer.parseInt(val));
        } else if(val.matches("\\d+.\\d+")){
            mavLinkParamService.setParam(paramKey, Float.parseFloat(val));
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


}
