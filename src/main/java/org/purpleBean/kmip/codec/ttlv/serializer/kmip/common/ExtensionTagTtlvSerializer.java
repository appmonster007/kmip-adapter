package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionTag, Integer> {

    public ExtensionTagTtlvSerializer() {
        super(ExtensionTag::getValue);
    }
}