package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorJsonSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}