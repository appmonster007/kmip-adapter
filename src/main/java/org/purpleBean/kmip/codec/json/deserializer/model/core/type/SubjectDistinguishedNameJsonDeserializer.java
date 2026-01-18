package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameJsonDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}