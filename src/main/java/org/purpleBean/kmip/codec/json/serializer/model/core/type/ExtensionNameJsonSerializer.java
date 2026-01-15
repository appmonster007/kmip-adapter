package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ExtensionName, String> {

    public ExtensionNameJsonSerializer() {
        super(ExtensionName::getValue);
    }
}