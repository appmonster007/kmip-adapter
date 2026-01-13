package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionType, Integer> {

    public ExtensionTypeTtlvSerializer() {
        super(ExtensionType::getValue);
    }
}