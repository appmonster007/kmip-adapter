package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyParts, Integer> {

    public SplitKeyPartsTtlvDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType, Integer.class, value -> SplitKeyParts.builder().value(value).build());
    }
}