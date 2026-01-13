package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<WrappingMethod, String> {

    public WrappingMethodJsonSerializer() {
        super(WrappingMethod::getDescription);
    }
}