package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Fresh, Boolean> {

    public FreshTtlvSerializer() {
        super(Fresh::getValue);
    }
}