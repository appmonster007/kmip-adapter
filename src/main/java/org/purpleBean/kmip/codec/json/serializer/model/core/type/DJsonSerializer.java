package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.D;

import java.math.BigInteger;

public class DJsonSerializer extends AbstractKmipDataTypeJsonSerializer<D, BigInteger> {

    public DJsonSerializer() {
        super(D::getValue);
    }
}