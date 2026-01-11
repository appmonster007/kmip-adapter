package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodJsonDeserializer extends AbstractKmipJsonDeserializer<WrappingMethod, String> {

    public WrappingMethodJsonDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, String.class, value -> new WrappingMethod(WrappingMethod.fromName(value)));
    }
}