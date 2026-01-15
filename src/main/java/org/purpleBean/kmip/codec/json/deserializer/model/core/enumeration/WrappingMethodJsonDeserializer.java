package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<WrappingMethod, String> {

    public WrappingMethodJsonDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType, String.class, value -> new WrappingMethod(WrappingMethod.fromName(value)));
    }
}