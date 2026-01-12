package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagTtlvSerializer extends AbstractKmipTtlvSerializer<ExtensionTag, Integer> {

    public ExtensionTagTtlvSerializer() {
        super(ExtensionTag::getValue);
    }
}