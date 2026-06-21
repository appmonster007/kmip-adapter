package org.purpleBean.kmip.codec.json.model.core.enumeration;

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
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueIdentifier Json Serialization Tests")
class UniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<UniqueIdentifier> {

    @Override
    public Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    public UniqueIdentifier createDefault() {
        return UniqueIdentifier.Standard.values()[0].inst();
    }

    @Override
    public UniqueIdentifier createVariant() {
        return UniqueIdentifier.Standard.values()[1].inst();
    }
}