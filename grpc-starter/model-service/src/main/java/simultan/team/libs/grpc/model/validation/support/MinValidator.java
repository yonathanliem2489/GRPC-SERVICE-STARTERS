/*
 * Copyright 2017-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package simultan.team.libs.grpc.model.validation.support;

import com.google.common.base.Preconditions;
import simultan.team.libs.grpc.model.validation.AbstractValidator;

public class MinValidator extends AbstractValidator {
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo) {
        String extensionValueStr = extensionValue.toString();
        String fieldValueStr = fieldValue.toString();
        String err = errInfo + "error with Min";

        if (fieldValue instanceof String) {
            Preconditions
                .checkArgument(Long.parseLong(extensionValueStr) <= fieldValueStr.length(), err);
        }
        if (fieldValue instanceof Long) {
            Preconditions.checkArgument(Long.valueOf(extensionValueStr) <= Long.valueOf(fieldValueStr), err);
        }
        if (fieldValue instanceof Integer) {
            Preconditions.checkArgument(Integer.valueOf(extensionValueStr) <= Integer.valueOf(fieldValueStr), err);
        }
        if (fieldValue instanceof Float) {
            Preconditions.checkArgument(Float.valueOf(extensionValueStr) <= Float.valueOf(fieldValueStr), err);
        }
        if (fieldValue instanceof Double) {
            Preconditions.checkArgument(Double.valueOf(extensionValueStr) <= Double.valueOf(fieldValueStr), err);
        }
    }

    @Override
    public String toString() {
        return "MinValidator";
    }
}
