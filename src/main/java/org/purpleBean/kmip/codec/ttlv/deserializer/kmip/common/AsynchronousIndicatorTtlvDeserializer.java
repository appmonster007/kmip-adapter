package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Boolean.class, value -> AsynchronousIndicator.builder().value(value).build());
    }
}