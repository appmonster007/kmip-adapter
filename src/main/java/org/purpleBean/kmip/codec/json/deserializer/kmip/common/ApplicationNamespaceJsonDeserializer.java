package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceJsonDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}