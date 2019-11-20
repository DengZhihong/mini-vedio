package constants;

public interface ResultCode {

    Integer SUCESS = 200;//表示成功
    Integer INTERNAL_EXCEPTION = 500;//表示错误，错误信息在msg字段中
    Integer BEAN_VALIDATE_EXCEPTION= 501;//bean验证错误，不管多少个错误都以map形式返回
    Integer TOKEN_EXCEPTION = 502;//拦截器拦截到用户token出错
    Integer RUNTIME_EXCEPTION = 555;//异常抛出信息
}
