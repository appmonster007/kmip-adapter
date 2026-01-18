package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionName, String> {

    public ExtensionNameTtlvSerializer() {
        super(ExtensionName::getValue);
    }
}