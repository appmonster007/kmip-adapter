package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeJsonSerializer extends AbstractKmipJsonSerializer<ExtensionType, Integer> {

    public ExtensionTypeJsonSerializer() {
        super(ExtensionType::getValue);
    }
}