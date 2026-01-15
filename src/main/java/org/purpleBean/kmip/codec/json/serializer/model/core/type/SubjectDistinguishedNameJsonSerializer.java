package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameJsonSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}