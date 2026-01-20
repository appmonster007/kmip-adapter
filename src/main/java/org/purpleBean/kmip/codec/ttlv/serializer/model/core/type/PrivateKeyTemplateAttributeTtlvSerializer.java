package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrivateKeyTemplateAttribute, String> {

    public PrivateKeyTemplateAttributeTtlvSerializer() {
        super(PrivateKeyTemplateAttribute::getValue);
    }
}