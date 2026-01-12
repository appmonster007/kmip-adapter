package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvSerializer extends AbstractKmipTtlvSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorTtlvSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}