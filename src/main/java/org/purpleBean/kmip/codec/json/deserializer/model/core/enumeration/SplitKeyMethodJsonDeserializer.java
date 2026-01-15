package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyMethod, String> {

    public SplitKeyMethodJsonDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, String.class, value -> new SplitKeyMethod(SplitKeyMethod.fromName(value)));
    }
}