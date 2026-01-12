package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameTtlvSerializer extends AbstractKmipTtlvSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameTtlvSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}