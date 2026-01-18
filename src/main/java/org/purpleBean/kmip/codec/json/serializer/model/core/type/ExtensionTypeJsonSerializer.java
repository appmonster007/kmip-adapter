package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ExtensionType, Integer> {

    public ExtensionTypeJsonSerializer() {
        super(ExtensionType::getValue);
    }
}