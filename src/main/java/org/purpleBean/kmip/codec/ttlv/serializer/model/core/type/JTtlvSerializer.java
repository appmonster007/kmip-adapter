package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.J;

import java.math.BigInteger;

public class JTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<J, BigInteger> {

    public JTtlvSerializer() {
        super(J::getValue);
    }
}