package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipTtlvDeserializer<AsynchronousIndicator, Integer> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Integer.class, value -> new AsynchronousIndicator(AsynchronousIndicator.fromValue(value)));
    }
}