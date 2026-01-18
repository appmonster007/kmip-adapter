package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceJsonDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}