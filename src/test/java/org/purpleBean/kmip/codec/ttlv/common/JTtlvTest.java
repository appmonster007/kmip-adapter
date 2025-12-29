package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.J;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.math.BigInteger;

@DisplayName("J TTLV Serialization Tests")
class JTtlvTest extends AbstractTtlvSerializationSuite<J> {

    @Override
    protected Class<J> type() {
        return J.class;
    }

    @Override
    protected J createDefault() {
        return J.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected J createVariant() {
        return J.builder().value(BigInteger.TEN).build();
    }
}