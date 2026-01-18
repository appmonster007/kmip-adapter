package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameTtlvSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}