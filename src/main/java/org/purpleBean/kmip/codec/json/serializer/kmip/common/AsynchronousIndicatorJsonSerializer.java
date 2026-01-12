package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorJsonSerializer extends AbstractKmipJsonSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorJsonSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}