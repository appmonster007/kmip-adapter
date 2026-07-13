package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("LocatedItems Domain Tests")
class LocatedItemsTest extends AbstractKmipDataTypeTestSuite<LocatedItems> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<LocatedItems> type() {
        return LocatedItems.class;
    }

    @Override
    protected LocatedItems createDefault() {
        return LocatedItems.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}