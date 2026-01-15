package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceJsonSerializer() {
        super(ApplicationNamespace::getValue);
    }
}