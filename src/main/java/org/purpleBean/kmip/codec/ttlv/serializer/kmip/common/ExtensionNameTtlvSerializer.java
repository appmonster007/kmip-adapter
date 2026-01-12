package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameTtlvSerializer extends AbstractKmipTtlvSerializer<ExtensionName, String> {

    public ExtensionNameTtlvSerializer() {
        super(ExtensionName::getValue);
    }
}