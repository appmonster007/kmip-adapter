package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyParts, Integer> {

    public SplitKeyPartsJsonDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType, Integer.class, value -> SplitKeyParts.builder().value(value).build());
    }
}