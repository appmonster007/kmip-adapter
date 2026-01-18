package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AsynchronousIndicator, Integer> {

    public AsynchronousIndicatorTtlvSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}