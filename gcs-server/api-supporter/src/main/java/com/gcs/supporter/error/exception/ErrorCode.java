package com.gcs.supporter.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    CONFLICT_PASSWORD_ERROR(400, "비밀번호를 다시 확인해주세요."),
    CONFLICT_USERNAME(400, "이미 사용중인 아이디입니다."),
    INVALID_PASSWORD(400, "비밀번호가 잘못되었습니다."),
    ID_OR_PASSWORD_INVALID(400, "아이디 혹은 패스워드가 일치하지 않습니다."),
    BEARER_TOKEN_INVALID(400, "토큰이 비정상적입니다."),
    BEARER_TOKEN_EXPIRED(400, "토큰이 만료되었습니다."),
    OAUTH_KIND_ERROR(400, "허가되지 않는 OAuth 인증 타입으로 인증을 시도했습니다."),
    CONNECTION_PREFIX_ERROR(400, "ws 연결 시 정의되지 않은 prefix로 접근을 시도했습니다."),
    CONNECTION_RESOURCE_ERROR(400, "ws 연결 시 올바르지 못한 familyId 로 접근을 시도했습니다."),
    ALREADY_JOINED(400, "이미 사용중인 아이디입니다."),
    NO_AUTHENTICATION_ACCESS(401, "인증이 필요합니다."),
    USER_NOT_FOUND(400, "없는 회원에 대한 접근입니다."),
    NO_FAMILY_IN_HEADER(400, "realtime 요청 시 familyId 가 header 에 존재하지 않습니다."),

    NOT_VALID_CODE(403, "유효하지 않는 코드의 접근입니다."),
    NO_RUNNING_CONTEXT_CONF(400, "현재 운영되고 있는 세팅이 없습니다."),
    NO_AGENT_FROM_CONTEXT_CONF(400, "운영되고 있는 세팅에 해당 에이전트는 존재하지 않습니다."),
    INVALID_CONTEXT_CONF(400, "configuration 파일 구성이 잘 못 되었습니다."),
    FILE_NOT_FOUND(404, "없는 파일 리소스에 대한 접근입니다."),
    FILE_NOT_STORED(400, "파일을 저장하는데 실패하였습니다."),
    ALBUM_NOT_FOUND(404, "없는 앨범 리소스에 대한 접근입니다."),
    COMMENT_NOT_FOUND(404, "없는 댓글 리소스에 대한 접근입니다."),
    APPOINTMENT_NOT_FOUND(404, "없는 일정 리소스에 대한 접근입니다."),
    FAMILY_NOT_FOUND(404, "없는 가족 리소스에 대한 접근입니다."),
    TOKEN_IS_BLANK(404, "헤더는 존재하나, 토큰이 들어있지 않습니다."),
    ALREADY_JOINED_USER(409, "이미 등록이 완료된 회원입니다."),
    CONSUMED_MESSAGE_EMPTY(500, "실시간 consume 한 데이터에 문제가 발생했습니다."),
    BEARER_INTERNAL_ERROR(500, "토큰 검증과정에서 에러가 발생했습니다."),
    FILE_IO_ERROR(500, "파일 업로드에 문제가 발생했습니다."),
    OAUTH_ERROR(500, "OAuth 인증과정에서 문제가 발생했습니다. (유효하지 않는 accessToken)"),
    REALTIME_INTERNAL_ERROR(500, "realtime 에서 session 에 대한 key 입력 과정에서 에러가 발생했습니다."),
    SESSION_REMOVE_ERROR(500, "realtime 에서 session 을 지우는 과정에서 에러가 발생했습니다."),
    THREAD_SLEEP(500, "Thread sleep에서 에러가 발생했습니다."),
    ENTITY_PARAMETER_NOT_ACCEPT(500, "엔티티생성 과정에서 값이 누락되었습니다."),
    NOT_FOUND_RESOURCE_ERROR(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 리소스입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"서버 내부 에러입니다."),
    INVALID_INPUT_ERROR(HttpStatus.BAD_REQUEST.value(), "적절한 입력값이 아닙니다."),
    CONFLICT_VALUE_ERROR(HttpStatus.CONFLICT.value(), "중복된 값입니다"),
    NOT_PERMITTED_RESOURCE_ERROR(HttpStatus.UNAUTHORIZED.value(), "접근할 수 없는 리소스입니다."),
    NOT_VALID_REQUEST_ERROR(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    UNAUTHORIZED_ERROR(HttpStatus.FORBIDDEN.value(), "허용되지 않은 접근입니다."),
    NOT_FOUND_CONTEXT_CONF(HttpStatus.NOT_FOUND.value(), "해당 컨텍스트를 찾을 수 없습니다.");

    private final int status;
    private final String detail;
    private String message;


    ErrorCode(int status, String detail){
        this.status = status;
        this.detail = detail;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
