package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<ExtensionType, Integer> {

    public ExtensionTypeTtlvDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType, Integer.class, value -> ExtensionType.builder().value(value).build());
    }
}