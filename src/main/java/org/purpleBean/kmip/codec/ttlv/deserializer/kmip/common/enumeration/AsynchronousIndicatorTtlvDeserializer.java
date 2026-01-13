package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator, Integer> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Integer.class, value -> new AsynchronousIndicator(AsynchronousIndicator.fromValue(value)));
    }
}