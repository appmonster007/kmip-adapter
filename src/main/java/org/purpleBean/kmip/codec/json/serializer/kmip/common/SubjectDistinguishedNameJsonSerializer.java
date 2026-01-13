package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameJsonSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}