package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PaddingMethod, String> {

    public PaddingMethodJsonDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, String.class, value -> new PaddingMethod(PaddingMethod.fromName(value)));
    }
}