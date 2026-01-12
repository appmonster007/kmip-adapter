package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceTtlvDeserializer extends AbstractKmipTtlvDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceTtlvDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}