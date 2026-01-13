package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ExtensionName, String> {

    public ExtensionNameJsonSerializer() {
        super(ExtensionName::getValue);
    }
}