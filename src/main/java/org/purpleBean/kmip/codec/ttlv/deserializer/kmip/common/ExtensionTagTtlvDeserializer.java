package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagTtlvDeserializer extends AbstractKmipTtlvDeserializer<ExtensionTag, Integer> {

    public ExtensionTagTtlvDeserializer() {
        super(ExtensionTag.kmipTag, ExtensionTag.encodingType, Integer.class, value -> ExtensionTag.builder().value(value).build());
    }
}