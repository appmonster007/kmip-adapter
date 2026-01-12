package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorJsonSerializer extends AbstractKmipJsonSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorJsonSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}