package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ExtensionType, Integer> {

    public ExtensionTypeJsonSerializer() {
        super(ExtensionType::getValue);
    }
}