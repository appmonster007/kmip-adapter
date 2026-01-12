package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JJsonSerializer extends AbstractKmipJsonSerializer<J, BigInteger> {

    public JJsonSerializer() {
        super(J::getValue);
    }
}