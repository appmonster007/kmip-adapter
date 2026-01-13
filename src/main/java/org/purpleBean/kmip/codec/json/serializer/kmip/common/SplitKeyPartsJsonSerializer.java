package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsJsonSerializer() {
        super(SplitKeyParts::getValue);
    }
}