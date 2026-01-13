package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameJsonSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}