package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorTtlvSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}