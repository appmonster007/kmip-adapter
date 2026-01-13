package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionName, String> {

    public ExtensionNameTtlvSerializer() {
        super(ExtensionName::getValue);
    }
}