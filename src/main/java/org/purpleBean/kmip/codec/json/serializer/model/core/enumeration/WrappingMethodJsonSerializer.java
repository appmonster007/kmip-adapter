package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<WrappingMethod, String> {

    public WrappingMethodJsonSerializer() {
        super(WrappingMethod::getDescription);
    }
}