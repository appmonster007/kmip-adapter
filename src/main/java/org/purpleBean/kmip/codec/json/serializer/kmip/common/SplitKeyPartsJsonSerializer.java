package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsJsonSerializer extends AbstractKmipJsonSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsJsonSerializer() {
        super(SplitKeyParts::getValue);
    }
}