package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PaddingMethod, String> {

    public PaddingMethodJsonDeserializer() {
        super(PaddingMethod.kmipTag, PaddingMethod.encodingType, String.class, value -> new PaddingMethod(PaddingMethod.fromName(value)));
    }
}