package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorJsonSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}