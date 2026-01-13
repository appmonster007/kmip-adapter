package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<J, BigInteger> {

    public JTtlvSerializer() {
        super(J::getValue);
    }
}