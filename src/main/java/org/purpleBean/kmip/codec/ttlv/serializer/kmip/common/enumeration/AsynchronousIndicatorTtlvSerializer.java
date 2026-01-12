package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvSerializer extends AbstractKmipTtlvSerializer<AsynchronousIndicator, Integer> {

    public AsynchronousIndicatorTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}