package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Fresh, Boolean> {

    public FreshTtlvSerializer() {
        super(Fresh::getValue);
    }
}