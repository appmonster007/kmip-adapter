package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsTtlvSerializer extends AbstractKmipTtlvSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsTtlvSerializer() {
        super(SplitKeyParts::getValue);
    }
}