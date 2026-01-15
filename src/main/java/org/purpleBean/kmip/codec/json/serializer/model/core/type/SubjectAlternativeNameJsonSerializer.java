package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameJsonSerializer() {
        super(SubjectAlternativeName::getValue);
    }
}