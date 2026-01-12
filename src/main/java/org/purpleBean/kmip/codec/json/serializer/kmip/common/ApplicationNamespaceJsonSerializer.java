package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceJsonSerializer extends AbstractKmipJsonSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceJsonSerializer() {
        super(ApplicationNamespace::getValue);
    }
}