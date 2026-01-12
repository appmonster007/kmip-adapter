package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvSerializer extends AbstractKmipTtlvSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameTtlvSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}