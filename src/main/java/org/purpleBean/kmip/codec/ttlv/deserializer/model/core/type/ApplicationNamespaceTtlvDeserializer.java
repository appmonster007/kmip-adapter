package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceTtlvDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}