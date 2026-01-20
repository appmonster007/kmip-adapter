package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorJsonDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, String.class, value -> AsynchronousIndicator.fromName(value).inst());
    }
}