package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionTag;

public class ExtensionTagTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ExtensionTag, Integer> {

    public ExtensionTagTtlvSerializer() {
        super(ExtensionTag::getValue);
    }
}