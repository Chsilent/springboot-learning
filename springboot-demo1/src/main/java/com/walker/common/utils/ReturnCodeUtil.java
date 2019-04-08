package com.walker.common.utils;

public class ReturnCodeUtil {
    public static final String RETURN_CODE = "status";
    public static final String RETURN_MESSAGE = "msg";// return the value of MSG
    // in enum ReturnCode
    public static final String RETURN_DATA = "data";
    public static final String TOTAL_PAGE_SIZE = "totalpage";// 总页数
    public static final String MODULENAME = "moduleName";
    public static final String TOTAL_PAGE = "TOTAL_PAGE";
    public static final String TOTAL_COUNT = "totalCount";// 返回所有记录总条数
    public static final String DATA_INTEGER = "DATA_INTEGER";
    public static final String SEARCH_KEYWORDS = "search_keywords";
    public static final String CURRENT_PAGE = "current_page";

    public static enum ReturnCode {

        UNDEFINED(0, "UNDEFINED"),

        // -------------------200~299 代表成功-------------------
        /**
         * 请求数据成功
         */
        REQUEST_SUCCESS(200, "OK"),
        /**
         * 请求成功，结果为空
         */
        RESULT_EMPTY(201, "RESULT EMPTY"), RESULT_CONFIRM(202, "CONFI"),
        /**
         * 请求成功,校验不通过
         */
        VALID_FAILD(203, "VALID FAILD"),


        // -------------------300~399 代表页面请求非法-------------
        /**
         * JACKSON序列化对象失败
         */
        JSON_TO_OBJECT_FAIL(301, "JSON FAIL"),
        /**
         * 参数为空
         */
        EMPTY_PARAMS(302, "EMPTY PARAMETERS"),
        /**
         * 参数类型错误
         */
        PARAMETER_TYPE_ERROR(303, "PARAMETER TYPE ERROR"),
        /**  */
        REQUEST_FAILD(304, "FAILD"),

        /**
         * 记录已存在
         */
        RECORD_EXIST(305, "FAILD"),

        /**
         * 拥有子集
         */
        HAS_CHILD(306, "FAILD"),

        /**
         * 被引用
         */
        HAS_QUOTE(307, "FAILD"),
        /**
         * 参数不合法
         */
        INVALID_PARAMTER(308, "INVALID PARAMETER"),

        // -------------------400~499 代表数据问题----------------------
        /**
         * 获取数据错误
         */
        GET_DATA_ERROR(400, "GET DATA ERROR"),
        /**
         * 删除数据错误
         */
        DELETE_DATA_ERROR(401, "DELETE DATA ERROR"),
        /**
         * 更新数据失败
         */
        UPDATE_DATA_ERROR(402, "UPDATE　DATA ERROR"),
        /**
         * 新增数据失败
         */
        INSERT_DATA_ERROR(403, "INSERT DATA ERROR"),
        /**
         * 上传文件丢失
         */
        UPLOAD_FILE_DELETE(404, "UPLOAD FILE CAN NOT FIND"),
        /**
         * 解析异常
         */
        ANALYTICAL_ERROE(405, "ANALYTICAL_ERROE"),
        /**
         * 格式错误
         */
        EXCEL_FORMAT_ERROR(406, "EXCEL_FORMAT_ERROR"),
        /**
         * 文档内容为空
         */
        EMPTY_EXCEL(407, "EMPTY_EXCEL"), DATA_IS_USED(408, "DATA_IS_USED"),
        /**
         * 上传文件尺寸过大
         */
        UPLOAD_FILE_OVERSIZE(409, "UPLOAD_FILE_OVERSIZE"),

        // -------------------500~599 代表服务代码问题----------------------
        /**
         * 后台程序错误
         */
        PROGRAM_ERROR(500, "PROGRAM ERROR"),
        /**
         * 场次还未开始
         **/
        ROUND_IS_NOT_START(511, "the round is not start"),
        ROUND_IS_NOT_IN_CURRENT_DATE(512, "the round is not in current date");

        private Integer status;
        private String msg;

        ReturnCode(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getStatus(int status) {
            for (ReturnCode r : ReturnCode.values()) {
                if (r.getStatus() == status) {
                    return status;
                }
            }
            return -1;
        }

        public String getMsg(int status) {
            for (ReturnCode r : ReturnCode.values()) {
                if (r.getStatus() == status) {
                    return r.getMsg();
                }
            }
            return "undefined";
        }
    }

    /**
     * 序列化失败后的返回
     */
    public static ResultInfo JackSonErrorReturn(ResultInfo resultInfo) {
        resultInfo.setStatus(ReturnCode.PROGRAM_ERROR.getStatus());
        resultInfo.setMsg(ReturnCode.PROGRAM_ERROR.getMsg());
        return resultInfo;
    }
}
