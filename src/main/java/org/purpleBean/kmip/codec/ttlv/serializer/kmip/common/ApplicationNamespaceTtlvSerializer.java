package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceTtlvSerializer extends AbstractKmipTtlvSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceTtlvSerializer() {
        super(ApplicationNamespace::getValue);
    }
}