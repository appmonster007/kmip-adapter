package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type.vendor;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

public class TtlvDataTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TtlvDataType, Object> {

    public TtlvDataTypeTtlvSerializer() {
        super(TtlvDataType::getValue);
    }
}