package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.D;

import java.math.BigInteger;

public class DTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<D, BigInteger> {

    public DTtlvSerializer() {
        super(D::getValue);
    }
}