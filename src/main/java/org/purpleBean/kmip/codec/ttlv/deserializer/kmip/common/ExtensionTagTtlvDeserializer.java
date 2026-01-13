package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExtensionTag, Integer> {

    public ExtensionTagTtlvDeserializer() {
        super(ExtensionTag.kmipTag, ExtensionTag.encodingType, Integer.class, value -> ExtensionTag.builder().value(value).build());
    }
}