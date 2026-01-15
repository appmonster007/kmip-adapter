package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator, Integer> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Integer.class, value -> new AsynchronousIndicator(AsynchronousIndicator.fromValue(value)));
    }
}