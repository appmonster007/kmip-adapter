package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorJsonDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Boolean.class, value -> AsynchronousIndicator.builder().value(value).build());
    }
}