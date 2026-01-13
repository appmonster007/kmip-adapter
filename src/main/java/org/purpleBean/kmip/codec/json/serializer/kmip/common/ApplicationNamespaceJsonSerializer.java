package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceJsonSerializer() {
        super(ApplicationNamespace::getValue);
    }
}