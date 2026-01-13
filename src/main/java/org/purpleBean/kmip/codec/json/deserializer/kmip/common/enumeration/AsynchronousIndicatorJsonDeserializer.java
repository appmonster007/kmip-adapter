package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorJsonDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, String.class, value -> new AsynchronousIndicator(AsynchronousIndicator.fromName(value)));
    }
}