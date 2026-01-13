package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JJsonSerializer extends AbstractKmipDataTypeJsonSerializer<J, BigInteger> {

    public JJsonSerializer() {
        super(J::getValue);
    }
}