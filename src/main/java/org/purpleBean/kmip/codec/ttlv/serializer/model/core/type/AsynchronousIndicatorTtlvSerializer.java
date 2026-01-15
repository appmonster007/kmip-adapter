package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorTtlvSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}