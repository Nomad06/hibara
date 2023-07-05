package com.bubalex.hibara;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class OrderNumberValueBridge implements ValueBridge<Integer, String> {

    @Override
    public String toIndexedValue(Integer value, ValueBridgeToIndexedValueContext context) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public Integer fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        return Integer.parseInt(value);
    }

    @Override
    public String parse(String value) {
        return value;
    }

    @Override
    public boolean isCompatibleWith(ValueBridge<?, ?> other) {
        return true;
    }

}
