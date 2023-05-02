package com.gcs.api.web.agent.api;

import com.gcs.api.domain.mavlink.service.MavLinkParamService;
import com.gcs.api.domain.mavlink.service.MavLinkService;
import com.gcs.supporter.mavlink.param.DroneShow;
import com.gcs.supporter.mavlink.param.ParameterKeyDto;
import com.gcs.supporter.util.api.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/param")
public class AgentParamApiController {
    private final MavLinkParamService mavLinkParamService;

    @GetMapping("/key")
    public ResponseEntity<ApiUtil.ApiResult<List<ParameterKeyDto>>> droneShowParamList(){
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
