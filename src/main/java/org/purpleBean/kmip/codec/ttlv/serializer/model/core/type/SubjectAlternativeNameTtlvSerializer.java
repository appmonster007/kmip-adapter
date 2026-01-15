package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameTtlvSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}