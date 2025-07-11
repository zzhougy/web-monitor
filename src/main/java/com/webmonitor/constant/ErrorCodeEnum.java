package com.webmonitor.constant;


public enum ErrorCodeEnum {
  VALIDATE_ERROR(400, "数据校验不通过"),
  SYS_ERROR(500, "系统异常，请联系管理员"),
  SYS_PERMISSION(403, "无权限"),
  USER_ACCOUNT_NOT_FOUND(20020, "账号不存在"),
  PASSWORD_DEFINED(20010, "密码错误"),
  ;


  private Integer code;
  private String msg;

  ErrorCodeEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public static ErrorCodeEnum getError(Integer code) {
    for (ErrorCodeEnum error : ErrorCodeEnum.values()) {
      if (code == error.getCode()) {
        return error;
      }
    }
    return null;
  }
}
