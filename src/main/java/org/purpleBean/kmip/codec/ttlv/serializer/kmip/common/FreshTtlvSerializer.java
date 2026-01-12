package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshTtlvSerializer extends AbstractKmipTtlvSerializer<Fresh, Boolean> {

    public FreshTtlvSerializer() {
        super(Fresh::getValue);
    }
}