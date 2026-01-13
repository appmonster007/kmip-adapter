package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DJsonSerializer extends AbstractKmipDataTypeJsonSerializer<D, BigInteger> {

    public DJsonSerializer() {
        super(D::getValue);
    }
}