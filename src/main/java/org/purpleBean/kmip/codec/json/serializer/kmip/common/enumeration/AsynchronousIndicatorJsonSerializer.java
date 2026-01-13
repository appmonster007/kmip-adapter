package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorJsonSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}