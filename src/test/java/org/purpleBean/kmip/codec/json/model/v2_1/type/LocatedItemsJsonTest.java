package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.LocatedItems;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocatedItems Json Serialization Tests")
class LocatedItemsJsonTest extends AbstractJsonSerializationTestSuite<LocatedItems> {

    @Override
    public Class<LocatedItems> type() {
        return LocatedItems.class;
    }

    @Override
    public LocatedItems createDefault() {
        return LocatedItems.of(123);
    }

    @Override
    public LocatedItems createVariant() {
        return LocatedItems.of(456);
    }
}