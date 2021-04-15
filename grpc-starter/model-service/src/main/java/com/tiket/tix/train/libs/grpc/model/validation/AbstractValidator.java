package com.tiket.tix.train.libs.grpc.model.validation;

public abstract class AbstractValidator implements Validator {

    @Override
    public void validate(String protoName, String fieldName, Object fieldValue, Object extensionValue) throws IllegalArgumentException {
        String errInfo = String.format("validate error protoName:%s,fieldName:%s,fieldValue:%s,extensionValue:%s,", protoName, fieldName, fieldValue, extensionValue);
        doValidate(fieldValue, extensionValue, errInfo);
    }

    protected abstract void doValidate(Object fieldValue, Object extensionValue, String errInfo) throws IllegalArgumentException;

}