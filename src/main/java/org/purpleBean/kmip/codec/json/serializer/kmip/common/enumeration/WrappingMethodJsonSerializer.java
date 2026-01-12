package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodJsonSerializer extends AbstractKmipJsonSerializer<WrappingMethod, String> {

    public WrappingMethodJsonSerializer() {
        super(WrappingMethod::getDescription);
    }
}