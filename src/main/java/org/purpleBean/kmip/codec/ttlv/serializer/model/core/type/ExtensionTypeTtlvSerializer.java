package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionType, Integer> {

    public ExtensionTypeTtlvSerializer() {
        super(ExtensionType::getValue);
    }
}