package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodJsonDeserializer extends AbstractKmipJsonDeserializer<SplitKeyMethod, String> {

    public SplitKeyMethodJsonDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, String.class, value -> new SplitKeyMethod(SplitKeyMethod.fromName(value)));
    }
}