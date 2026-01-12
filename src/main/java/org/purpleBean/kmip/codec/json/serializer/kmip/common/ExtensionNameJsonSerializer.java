package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameJsonSerializer extends AbstractKmipJsonSerializer<ExtensionName, String> {

    public ExtensionNameJsonSerializer() {
        super(ExtensionName::getValue);
    }
}