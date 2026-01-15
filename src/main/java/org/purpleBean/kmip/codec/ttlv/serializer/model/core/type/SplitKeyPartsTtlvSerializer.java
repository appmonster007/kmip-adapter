package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsTtlvSerializer() {
        super(SplitKeyParts::getValue);
    }
}