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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator {
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo){
        String fieldValueStr = fieldValue.toString();
        String err = errInfo + "error with email not valid";
        if(fieldValue instanceof String) {
            String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fieldValueStr);
            Preconditions.checkArgument(matcher.matches() , err);
        }
    }

    @Override
    public String toString() {
        return "EmailValidator";
    }
}
