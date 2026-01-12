package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonSerializer extends AbstractKmipJsonSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameJsonSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}