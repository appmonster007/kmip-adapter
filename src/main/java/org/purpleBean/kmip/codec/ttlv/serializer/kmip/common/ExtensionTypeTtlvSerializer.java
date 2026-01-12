package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeTtlvSerializer extends AbstractKmipTtlvSerializer<ExtensionType, Integer> {

    public ExtensionTypeTtlvSerializer() {
        super(ExtensionType::getValue);
    }
}