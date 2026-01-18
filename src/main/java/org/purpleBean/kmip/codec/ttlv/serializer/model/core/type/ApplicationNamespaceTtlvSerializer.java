package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceTtlvSerializer() {
        super(ApplicationNamespace::getValue);
    }
}