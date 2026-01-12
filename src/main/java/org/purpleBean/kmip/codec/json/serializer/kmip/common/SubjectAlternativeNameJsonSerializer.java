package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonSerializer extends AbstractKmipJsonSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameJsonSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}