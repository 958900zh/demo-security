package com.demo.core.validate.code;

/**
 * Created by zxdong on 2018/2/2.
 */
public enum ValidateCodeType {

    SMS {
        @Override
        public String getParamNameOnValidate() {
            return null;
        }
    },

    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return null;
        }
    };

    public abstract String getParamNameOnValidate();
}
