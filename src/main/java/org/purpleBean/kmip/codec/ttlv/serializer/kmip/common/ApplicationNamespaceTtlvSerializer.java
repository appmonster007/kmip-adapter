package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceTtlvSerializer() {
        super(ApplicationNamespace::getValue);
    }
}