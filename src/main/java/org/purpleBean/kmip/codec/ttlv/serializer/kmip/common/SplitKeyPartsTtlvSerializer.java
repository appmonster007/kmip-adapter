package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsTtlvSerializer() {
        super(SplitKeyParts::getValue);
    }
}