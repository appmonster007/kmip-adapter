package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameTtlvSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}