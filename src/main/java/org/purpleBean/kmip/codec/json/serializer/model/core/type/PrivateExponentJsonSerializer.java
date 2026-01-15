package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentJsonSerializer() {
        super(PrivateExponent::getValue);
    }
}