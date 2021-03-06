/*
 * Copyright 2014-2015 CyberVision, Inc.
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

package org.kaaproject.avro.ui.shared;

import java.util.ArrayList;
import java.util.List;

public class EnumField extends FqnField {

    private static final long serialVersionUID = 149481447537169849L;

    private FormEnum defaultValue;
    
    private FormEnum value;
    
    private List<FormEnum> enumValues;
    
    public EnumField() {
        super();
        enumValues = new ArrayList<>();
    }
    
    public EnumField(String fieldName, 
            String displayName, 
            String schema,
            boolean optional) {
        super(fieldName, displayName, schema, optional);
    }
    
    public FormEnum getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(FormEnum defaultValue) {
        this.defaultValue = defaultValue;
    }

    public FormEnum getValue() {
        return value;
    }

    public void setValue(FormEnum value) {
        this.value = value;
        fireChanged();
    }

    public List<FormEnum> getEnumValues() {
        return enumValues;
    }
    
    public void setEnumValues(List<FormEnum> enumValues) {
        this.enumValues = enumValues;
    }
    
    public void setValueFromSymbol(String enumSymbol) {
        this.setValue(fromEnumSymbol(enumSymbol));
    }
    
    public void setDefaultValueFromSymbol(String enumSymbol) {
        this.setDefaultValue(fromEnumSymbol(enumSymbol));
    }
    
    private FormEnum fromEnumSymbol(String enumSymbol) {
        if (!strIsEmpty(enumSymbol)) {
            if (this.enumValues != null) {
                for (FormEnum existingEnumValue : this.enumValues) {
                    if (existingEnumValue.getEnumSymbol().equals(enumSymbol)) {
                        return existingEnumValue;
                    }
                }
            }
            return new FormEnum(enumSymbol, enumSymbol);
        }
        return null;
    }
    
    @Override
    public String getDisplayString() {
        return super.getDisplayString() + ": " + valueToDisplayString(value);
    }
    
    @Override
    public FieldType getFieldType() {
        return FieldType.ENUM;
    }
    
    @Override
    public boolean isNull() {
        return value == null;
    }

    @Override
    protected FormField createInstance() {
        return new EnumField();
    }
    
    @Override
    protected void copyFields(FormField cloned) {
        super.copyFields(cloned);
        EnumField clonedEnumField = (EnumField)cloned;
        clonedEnumField.enumValues.addAll(enumValues);
        clonedEnumField.defaultValue = defaultValue;
        clonedEnumField.value = value;
    }

    @Override
    protected boolean valid() {
        return value != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((defaultValue == null) ? 0 : defaultValue.hashCode());
        result = prime * result
                + ((enumValues == null) ? 0 : enumValues.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnumField other = (EnumField) obj;
        if (defaultValue == null) {
            if (other.defaultValue != null)
                return false;
        } else if (!defaultValue.equals(other.defaultValue))
            return false;
        if (enumValues == null) {
            if (other.enumValues != null)
                return false;
        } else if (!enumValues.equals(other.enumValues))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
    
}
