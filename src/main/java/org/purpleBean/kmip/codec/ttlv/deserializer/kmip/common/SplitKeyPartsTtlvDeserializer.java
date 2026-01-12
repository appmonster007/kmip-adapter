package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsTtlvDeserializer extends AbstractKmipTtlvDeserializer<SplitKeyParts, Integer> {

    public SplitKeyPartsTtlvDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType, Integer.class, value -> SplitKeyParts.builder().value(value).build());
    }
}